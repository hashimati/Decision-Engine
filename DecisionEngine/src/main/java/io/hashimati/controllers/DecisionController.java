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



import io.hashimati.domains.Decision;
import io.hashimati.services.DecisionService;


@Tag(name = "Decision")
@Controller("/api/v1/decision")
@CircuitBreaker(attempts = "5", maxDelay = "3s", reset = "30")
public class DecisionController {

    private static final Logger log = LoggerFactory.getLogger(DecisionController.class);

    @Inject private DecisionService decisionService;


    @NewSpan("Decision-service")
    @Timed(value = "io.hashimati.controllers.decisionController.save", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for saving decision object")
    
    @Post("/save")
    @Version("1")
    @Operation(summary = "Creating a decision and Storing in the database",
            description = "A REST service, which saves Decision objects to the database.",
            operationId = "SaveDecision"
    )
    @ApiResponse(
            content = @Content(mediaType = "application/json")
    )
    @ApiResponse(responseCode = "400", description = "Invalid Object Supplied")
    @ApiResponse(responseCode = "404", description = "Decision not stored")
    public Decision save(@SpanTag("save.decision") @NonNull @Body Decision decision  ){
        log.info("Saving  Decision : {}", decision);
        //TODO insert your logic here!

        //saving Object
        return decisionService.save(decision );
    }


    @NewSpan("Decision-service")
    @Timed(value = "io.hashimati.controllers.decisionController.findById", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for finding a decision object by id")
    
    @Get("/get")
    @Version("1")
    @Operation(summary = "Getting a decision by Id",
        description = "A REST service, which retrieves a Decision object by Id.",
        operationId = "FindByIdDecision"
    )
    @ApiResponse(
        content = @Content(mediaType = "application/json")
    )
    @ApiResponse(responseCode = "400", description = "Invalid Id Supplied")
    @ApiResponse(responseCode = "404", description = "Decision not found")
    public Decision findById(@SpanTag("findById.id") @Parameter("id") String id ){
        return decisionService.findById(id );
    }

    @NewSpan("Decision-service")
    @Timed(value = "io.hashimati.controllers.decisionController.deleteById", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for deleting a decision object by id")
    
    @Delete("/delete/{id}")
    @Version("1")
    @Operation(summary = "Deleting a decision by ID",
            description = "A REST service, which deletes Decision object from the database.",
            operationId = "DeleteByIdDecision"
    )
    @ApiResponse(
            content = @Content(mediaType = "boolean")
    )
    @ApiResponse(responseCode = "400", description = "Invalid Id Supplied")
    @ApiResponse(responseCode = "404", description = "Decision not found")
    public boolean deleteById(@SpanTag("deleteById.id") @PathVariable("id") String id ){
        log.info("Deleting Decision by Id: {}", id);
        return  decisionService.deleteById(id );
    }

    @NewSpan("Decision-service")
    @Timed(value = "io.hashimati.controllers.decisionController.findAll", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for finding all decision objects")
    
    @Get("/findAll")
    @Version("1")
    @Operation(summary = "Retrieving all decision objects as Json",
            description = "A REST service, which returns all Decision objects from the database.",
            operationId = "FindAllDecision"
    )
    @ApiResponse(
            content = @Content(mediaType = "application/json")
    )
    public Iterable<Decision> findAll( ){
        log.info("find All");
        return decisionService.findAll( );
    }

    @NewSpan("Decision-service")
    @Timed(value = "io.hashimati.controllers.decisionController.update", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for update a decision object")
    
    @Put("/update")
    @Version("1")
    @Operation(summary = "Updating a decision.",
            description = "A REST service, which update a Decision objects to the database.",
            operationId = "UpdateDecision"
    )
    @ApiResponse(
            content = @Content(mediaType = "application/json")
    )
    @ApiResponse(responseCode = "404", description = "Decision not found")
    public Decision update(@SpanTag("update.decision") @NonNull @Body Decision decision )
    {
        log.info("update {}", decision);
        return decisionService.update(decision );

    }

    
    @Get("/findByName")
    @NewSpan("Decision-service")
    @Timed(value = "io.hashimati.controllers.decisionController.findByName", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for finding a decision object by Name")
    @Operation(summary = "Find an entity by Name",
    description = "A REST service, which retrieves a Decision object by Name."
    )
    @ApiResponse(
    content = @Content(mediaType = "application/json")
    )
    @ApiResponse(responseCode = "400", description = "Invalid Id Supplied")
    @ApiResponse(responseCode = "404", description = "Decision not found")
    public Decision findByName(String query ){
          log.info("Finding Decision By Name: {}", query);
          return decisionService.findByName(query );
    }


    @NewSpan("Decision-service")
    @Timed(value = "io.hashimati.controllers.decisionController.findAllByContext", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for finding a decision object by Context")
    
    @Get("/findAllByContext")
    @Operation(summary = "Getting all entity by Context",
       description = "A REST service, which retrieves a Decision objects by Context."
    )
    @ApiResponse(
       content = @Content(mediaType = "application/json")
    )
    @ApiResponse(responseCode = "400", description = "Invalid Id Supplied")
    @ApiResponse(responseCode = "404", description = "Decision not found")
    public Iterable<Decision> findAllByContext(String query ){
          log.info("Finding all Decision By Context: {}", query);
          return decisionService.findAllByContext(query );
    }





    @NewSpan("Decision-service")
    @Timed(value = "io.hashimati.controllers.decisionController.updateby.Name", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for update a decision object")
    
    @Put("/updateByName")
    @Version("1")
    @Operation(summary = "Updating a decision.",
            description = "A REST service, which updates a Decision objects to the database by Name, This service will only update these attributes {name}.",
            operationId = "UpdateDecisionbyName"
    )
    @ApiResponse(
            content = @Content(mediaType = "application/json")
    )
    @ApiResponse(responseCode = "404", description = "Decision not found")
    public Long updateName(@SpanTag("updateupdateby.Name.decision") @QueryValue("query") String query,  @Body Decision body )
    {
        log.info("update {}", query);
        return decisionService.updateByName(query, body.getName() );

    }

}


