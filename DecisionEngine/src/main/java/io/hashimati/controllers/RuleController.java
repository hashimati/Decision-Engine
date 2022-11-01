package io.hashimati.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.micronaut.http.MediaType;
import io.micronaut.http.multipart.CompletedFileUpload;
import io.micronaut.core.annotation.NonNull;




import io.micronaut.retry.annotation.CircuitBreaker;
import io.micronaut.context.annotation.Parameter;
import io.micronaut.core.version.annotation.Version;
import io.micronaut.http.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



import io.micrometer.core.annotation.Timed;
import io.micronaut.tracing.annotation.NewSpan;
import io.micronaut.tracing.annotation.SpanTag;
import jakarta.inject.Inject;



import io.hashimati.domains.Rule;
import io.hashimati.services.RuleService;


@Tag(name = "Rule")
@Controller("/api/v1/rule")
@CircuitBreaker(attempts = "5", maxDelay = "3s", reset = "30")
public class RuleController {
    private static final Logger log = LoggerFactory.getLogger(RuleController.class);

    @Inject private RuleService ruleService;


    @NewSpan("Rule-service")
    @Timed(value = "io.hashimati.controllers.ruleController.save", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for saving rule object")
    @Post("/save")
    @Version("1")
    @Operation(summary = "Creating a rule and Storing in the database",
            description = "A REST service, which saves Rule objects to the database.",
            operationId = "SaveRule"
    )
    @ApiResponse(
            content = @Content(mediaType = "application/json")
    )
    @ApiResponse(responseCode = "400", description = "Invalid Object Supplied")
    @ApiResponse(responseCode = "404", description = "Rule not stored")
    public Rule save(@SpanTag("save.rule") @NonNull @Body Rule rule  ){
        log.info("Saving  Rule : {}", rule);
        //TODO insert your logic here!

        //saving Object
        return ruleService.save(rule );
    }


    @NewSpan("Rule-service")
    @Timed(value = "io.hashimati.controllers.ruleController.findById", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for finding a rule object by id")
    
    @Get("/get")
    @Version("1")
    @Operation(summary = "Getting a rule by Id",
        description = "A REST service, which retrieves a Rule object by Id.",
        operationId = "FindByIdRule"
    )
    @ApiResponse(
        content = @Content(mediaType = "application/json")
    )
    @ApiResponse(responseCode = "400", description = "Invalid Id Supplied")
    @ApiResponse(responseCode = "404", description = "Rule not found")
    public Rule findById(@SpanTag("findById.id") @Parameter("id") String id ){
        return ruleService.findById(id );
    }

    @NewSpan("Rule-service")
    @Timed(value = "io.hashimati.controllers.ruleController.deleteById", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for deleting a rule object by id")
    
    @Delete("/delete/{id}")
    @Version("1")
    @Operation(summary = "Deleting a rule by ID",
            description = "A REST service, which deletes Rule object from the database.",
            operationId = "DeleteByIdRule"
    )
    @ApiResponse(
            content = @Content(mediaType = "boolean")
    )
    @ApiResponse(responseCode = "400", description = "Invalid Id Supplied")
    @ApiResponse(responseCode = "404", description = "Rule not found")
    public boolean deleteById(@SpanTag("deleteById.id") @PathVariable("id") String id ){
        log.info("Deleting Rule by Id: {}", id);
        return  ruleService.deleteById(id );
    }

    @NewSpan("Rule-service")
    @Timed(value = "io.hashimati.controllers.ruleController.findAll", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for finding all rule objects")
    
    @Get("/findAll")
    @Version("1")
    @Operation(summary = "Retrieving all rule objects as Json",
            description = "A REST service, which returns all Rule objects from the database.",
            operationId = "FindAllRule"
    )
    @ApiResponse(
            content = @Content(mediaType = "application/json")
    )
    public Iterable<Rule> findAll( ){
        log.info("find All");
        return ruleService.findAll( );
    }

    @NewSpan("Rule-service")
    @Timed(value = "io.hashimati.controllers.ruleController.update", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for update a rule object")
    
    @Put("/update")
    @Version("1")
    @Operation(summary = "Updating a rule.",
            description = "A REST service, which update a Rule objects to the database.",
            operationId = "UpdateRule"
    )
    @ApiResponse(
            content = @Content(mediaType = "application/json")
    )
    @ApiResponse(responseCode = "404", description = "Rule not found")
    public Rule update(@SpanTag("update.rule") @NonNull @Body Rule rule )
    {
        log.info("update {}", rule);
        return ruleService.update(rule );

    }


}


