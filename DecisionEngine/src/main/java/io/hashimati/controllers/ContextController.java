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



import io.hashimati.domains.Context;
import io.hashimati.services.ContextService;

import java.util.HashSet;


@Tag(name = "Context")
@Controller("/api/v1/context")
@CircuitBreaker(attempts = "5", maxDelay = "3s", reset = "30")
public class ContextController {

    private static final Logger log = LoggerFactory.getLogger(ContextController.class);

    @Inject private ContextService contextService;


    @NewSpan("Context-service")
    @Timed(value = "io.hashimati.controllers.contextController.save", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for saving context object")
    
    @Post("/save")
    @Version("1")
    @Operation(summary = "Creating a context and Storing in the database",
            description = "A REST service, which saves Context objects to the database.",
            operationId = "SaveContext"
    )
    @ApiResponse(
            content = @Content(mediaType = "application/json")
    )
    @ApiResponse(responseCode = "400", description = "Invalid Object Supplied")
    @ApiResponse(responseCode = "404", description = "Context not stored")
    public Context save(@SpanTag("save.context") @NonNull @Body Context context  ){
        log.info("Saving  Context : {}", context);
        //TODO insert your logic here!

        //saving Object
        return contextService.save(context );
    }


    @NewSpan("Context-service")
    @Timed(value = "io.hashimati.controllers.contextController.findById", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for finding a context object by id")
    
    @Get("/get")
    @Version("1")
    @Operation(summary = "Getting a context by Id",
        description = "A REST service, which retrieves a Context object by Id.",
        operationId = "FindByIdContext"
    )
    @ApiResponse(
        content = @Content(mediaType = "application/json")
    )
    @ApiResponse(responseCode = "400", description = "Invalid Id Supplied")
    @ApiResponse(responseCode = "404", description = "Context not found")
    public Context findById(@SpanTag("findById.id") @Parameter("id") String id ){
        log.info("Finding Context by Id: {}", id);
        return contextService.findById(id );
    }

    @NewSpan("Context-service")
    @Timed(value = "io.hashimati.controllers.contextController.deleteById", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for deleting a context object by id")
    
    @Delete("/delete/{id}")
    @Version("1")
    @Operation(summary = "Deleting a context by ID",
            description = "A REST service, which deletes Context object from the database.",
            operationId = "DeleteByIdContext"
    )
    @ApiResponse(
            content = @Content(mediaType = "boolean")
    )
    @ApiResponse(responseCode = "400", description = "Invalid Id Supplied")
    @ApiResponse(responseCode = "404", description = "Context not found")
    public boolean deleteById(@SpanTag("deleteById.id") @PathVariable("id") String id ){
        log.info("Deleting Context by Id: {}", id);
        return  contextService.deleteById(id );
    }

    @NewSpan("Context-service")
    @Timed(value = "io.hashimati.controllers.contextController.findAll", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for finding all context objects")

    @Get("/findAll")
    @Version("1")
    @Operation(summary = "Retrieving all context objects as Json",
            description = "A REST service, which returns all Context objects from the database.",
            operationId = "FindAllContext"
    )
    @ApiResponse(
            content = @Content(mediaType = "application/json")
    )
    public Iterable<Context> findAll( ){
        log.info("find All");
        return contextService.findAll( );
    }

    @Get("/findAllNames")
    @Version("1")
    @Operation(summary = "Retrieving all context names as list",
            description = "A REST service, which returns all Context objects from the database.",
            operationId = "FindAllContext"
    )
    @ApiResponse(
            content = @Content(mediaType = "application/json")
    )
    public HashSet<String> findAllNames( ){
        log.info("getAll Names");
        return contextService.getAllNames( );
    }
    @NewSpan("Context-service")
    @Timed(value = "io.hashimati.controllers.contextController.update", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for update a context object")
    
    @Put("/update")
    @Version("1")
    @Operation(summary = "Updating a context.",
            description = "A REST service, which update a Context objects to the database.",
            operationId = "UpdateContext"
    )
    @ApiResponse(
            content = @Content(mediaType = "application/json")
    )
    @ApiResponse(responseCode = "404", description = "Context not found")
    public Context update(@SpanTag("update.context") @NonNull @Body Context context )
    {
        log.info("update {}", context);
        return contextService.update(context );

    }

    
    @Get("/findByName")
    @NewSpan("Context-service")
    @Timed(value = "io.hashimati.controllers.contextController.findByName", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for finding a context object by Name")
    @Operation(summary = "Find an entity by Name",
    description = "A REST service, which retrieves a Context object by Name."
    )
    @ApiResponse(
    content = @Content(mediaType = "application/json")
    )
    @ApiResponse(responseCode = "400", description = "Invalid Id Supplied")
    @ApiResponse(responseCode = "404", description = "Context not found")
    public Context findByName(String query ){
          log.info("Finding Context By Name: {}", query);
          return contextService.findByName(query );
    }





    @NewSpan("Context-service")
    @Timed(value = "io.hashimati.controllers.contextController.updateby.Name", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for update a context object")
    
    @Put("/updateByName")
    @Version("1")
    @Operation(summary = "Updating a context.",
            description = "A REST service, which updates a Context objects to the database by Name, This service will only update these attributes {name, description }.",
            operationId = "UpdateContextbyName"
    )
    @ApiResponse(
            content = @Content(mediaType = "application/json")
    )
    @ApiResponse(responseCode = "404", description = "Context not found")
    public Long updateName(@SpanTag("updateupdateby.Name.context") @QueryValue("query") String query,  @Body Context body )
    {
        log.info("update {}", query);
        return contextService.updateByName(query, body.getName(), body.getDescription () );

    }

}


