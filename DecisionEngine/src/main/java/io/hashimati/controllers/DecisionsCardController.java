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



import io.hashimati.domains.DecisionsCard;
import io.hashimati.services.DecisionsCardService;


@Tag(name = "DecisionsCard")
@Controller("/api/v1/decisionsCard")
@CircuitBreaker(attempts = "5", maxDelay = "3s", reset = "30")
public class DecisionsCardController {

    private static final Logger log = LoggerFactory.getLogger(DecisionsCardController.class);

    @Inject private DecisionsCardService decisionsCardService;


    @NewSpan("DecisionsCard-service")
    @Timed(value = "io.hashimati.controllers.decisionsCardController.save", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for saving decisionsCard object")
    
    @Post("/save")
    @Version("1")
    @Operation(summary = "Creating a decisionsCard and Storing in the database",
            description = "A REST service, which saves DecisionsCard objects to the database.",
            operationId = "SaveDecisionsCard"
    )
    @ApiResponse(
            content = @Content(mediaType = "application/json")
    )
    @ApiResponse(responseCode = "400", description = "Invalid Object Supplied")
    @ApiResponse(responseCode = "404", description = "DecisionsCard not stored")
    public DecisionsCard save(@SpanTag("save.decisionsCard") @NonNull @Body DecisionsCard decisionsCard  ){
        log.info("Saving  DecisionsCard : {}", decisionsCard);
        //TODO insert your logic here!

        //saving Object
        return decisionsCardService.save(decisionsCard );
    }


    @NewSpan("DecisionsCard-service")
    @Timed(value = "io.hashimati.controllers.decisionsCardController.findById", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for finding a decisionsCard object by id")
    
    @Get("/get")
    @Version("1")
    @Operation(summary = "Getting a decisionsCard by Id",
        description = "A REST service, which retrieves a DecisionsCard object by Id.",
        operationId = "FindByIdDecisionsCard"
    )
    @ApiResponse(
        content = @Content(mediaType = "application/json")
    )
    @ApiResponse(responseCode = "400", description = "Invalid Id Supplied")
    @ApiResponse(responseCode = "404", description = "DecisionsCard not found")
    public DecisionsCard findById(@SpanTag("findById.id") @Parameter("id") String id ){
        return decisionsCardService.findById(id );
    }

    @NewSpan("DecisionsCard-service")
    @Timed(value = "io.hashimati.controllers.decisionsCardController.deleteById", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for deleting a decisionsCard object by id")
    
    @Delete("/delete/{id}")
    @Version("1")
    @Operation(summary = "Deleting a decisionsCard by ID",
            description = "A REST service, which deletes DecisionsCard object from the database.",
            operationId = "DeleteByIdDecisionsCard"
    )
    @ApiResponse(
            content = @Content(mediaType = "boolean")
    )
    @ApiResponse(responseCode = "400", description = "Invalid Id Supplied")
    @ApiResponse(responseCode = "404", description = "DecisionsCard not found")
    public boolean deleteById(@SpanTag("deleteById.id") @PathVariable("id") String id ){
        log.info("Deleting DecisionsCard by Id: {}", id);
        return  decisionsCardService.deleteById(id );
    }

    @NewSpan("DecisionsCard-service")
    @Timed(value = "io.hashimati.controllers.decisionsCardController.findAll", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for finding all decisionsCard objects")
    
    @Get("/findAll")
    @Version("1")
    @Operation(summary = "Retrieving all decisionsCard objects as Json",
            description = "A REST service, which returns all DecisionsCard objects from the database.",
            operationId = "FindAllDecisionsCard"
    )
    @ApiResponse(
            content = @Content(mediaType = "application/json")
    )
    public Iterable<DecisionsCard> findAll( ){
        log.info("find All");
        return decisionsCardService.findAll( );
    }

    @NewSpan("DecisionsCard-service")
    @Timed(value = "io.hashimati.controllers.decisionsCardController.update", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for update a decisionsCard object")
    
    @Put("/update")
    @Version("1")
    @Operation(summary = "Updating a decisionsCard.",
            description = "A REST service, which update a DecisionsCard objects to the database.",
            operationId = "UpdateDecisionsCard"
    )
    @ApiResponse(
            content = @Content(mediaType = "application/json")
    )
    @ApiResponse(responseCode = "404", description = "DecisionsCard not found")
    public DecisionsCard update(@SpanTag("update.decisionsCard") @NonNull @Body DecisionsCard decisionsCard )
    {
        log.info("update {}", decisionsCard);
        return decisionsCardService.update(decisionsCard );

    }

    
    @Get("/findByName")
    @NewSpan("DecisionsCard-service")
    @Timed(value = "io.hashimati.controllers.decisionsCardController.findByName", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for finding a decisionsCard object by Name")
    @Operation(summary = "Find an entity by Name",
    description = "A REST service, which retrieves a DecisionsCard object by Name."
    )
    @ApiResponse(
    content = @Content(mediaType = "application/json")
    )
    @ApiResponse(responseCode = "400", description = "Invalid Id Supplied")
    @ApiResponse(responseCode = "404", description = "DecisionsCard not found")
    public DecisionsCard findByName(String query ){
          log.info("Finding DecisionsCard By Name: {}", query);
          return decisionsCardService.findByName(query );
    }


    @NewSpan("DecisionsCard-service")
    @Timed(value = "io.hashimati.controllers.decisionsCardController.findAllByContext", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for finding a decisionsCard object by Context")
    
    @Get("/findAllByContext")
    @Operation(summary = "Getting all entity by Context",
       description = "A REST service, which retrieves a DecisionsCard objects by Context."
    )
    @ApiResponse(
       content = @Content(mediaType = "application/json")
    )
    @ApiResponse(responseCode = "400", description = "Invalid Id Supplied")
    @ApiResponse(responseCode = "404", description = "DecisionsCard not found")
    public Iterable<DecisionsCard> findAllByContext(String query ){
          log.info("Finding all DecisionsCard By Context: {}", query);
          return decisionsCardService.findAllByContext(query );
    }



}


