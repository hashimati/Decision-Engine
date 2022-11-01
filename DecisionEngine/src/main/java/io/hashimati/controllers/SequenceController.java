package io.hashimati.controllers;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import io.micronaut.http.MediaType;
//import io.micronaut.http.multipart.CompletedFileUpload;
//import io.micronaut.core.annotation.NonNull;
//
//
//
//
//import io.micronaut.retry.annotation.CircuitBreaker;
//import io.micronaut.context.annotation.Parameter;
//import io.micronaut.core.version.annotation.Version;
//import io.micronaut.http.annotation.*;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.media.Content;
//import io.swagger.v3.oas.annotations.responses.ApiResponse;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//
//
//import io.micrometer.core.annotation.Timed;
//import io.micronaut.tracing.annotation.NewSpan;
//import io.micronaut.tracing.annotation.SpanTag;
//import jakarta.inject.Inject;
//
//
//
//import io.hashimati.domains.Sequence;
//import io.hashimati.services.SequenceService;
//
//
//@Tag(name = "Sequence")
//@Controller("/api/v1/sequence")
//@CircuitBreaker(attempts = "5", maxDelay = "3s", reset = "30")
//public class SequenceController {
//
//    private static final Logger log = LoggerFactory.getLogger(SequenceController.class);
//
//    @Inject private SequenceService sequenceService;
//
//
//    @NewSpan("Sequence-service")
//    @Timed(value = "io.hashimati.controllers.sequenceController.save", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for saving sequence object")
//
//    @Post("/save")
//    @Version("1")
//    @Operation(summary = "Creating a sequence and Storing in the database",
//            description = "A REST service, which saves Sequence objects to the database.",
//            operationId = "SaveSequence"
//    )
//    @ApiResponse(
//            content = @Content(mediaType = "application/json")
//    )
//    @ApiResponse(responseCode = "400", description = "Invalid Object Supplied")
//    @ApiResponse(responseCode = "404", description = "Sequence not stored")
//    public Sequence save(@SpanTag("save.sequence") @NonNull @Body Sequence sequence  ){
//        log.info("Saving  Sequence : {}", sequence);
//        //TODO insert your logic here!
//
//        //saving Object
//        return sequenceService.save(sequence );
//    }
//
//
//    @NewSpan("Sequence-service")
//    @Timed(value = "io.hashimati.controllers.sequenceController.findById", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for finding a sequence object by id")
//
//    @Get("/get")
//    @Version("1")
//    @Operation(summary = "Getting a sequence by Id",
//        description = "A REST service, which retrieves a Sequence object by Id.",
//        operationId = "FindByIdSequence"
//    )
//    @ApiResponse(
//        content = @Content(mediaType = "application/json")
//    )
//    @ApiResponse(responseCode = "400", description = "Invalid Id Supplied")
//    @ApiResponse(responseCode = "404", description = "Sequence not found")
//    public Sequence findById(@SpanTag("findById.id") @Parameter("id") String id ){
//        return sequenceService.findById(id );
//    }
//
//    @NewSpan("Sequence-service")
//    @Timed(value = "io.hashimati.controllers.sequenceController.deleteById", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for deleting a sequence object by id")
//
//    @Delete("/delete/{id}")
//    @Version("1")
//    @Operation(summary = "Deleting a sequence by ID",
//            description = "A REST service, which deletes Sequence object from the database.",
//            operationId = "DeleteByIdSequence"
//    )
//    @ApiResponse(
//            content = @Content(mediaType = "boolean")
//    )
//    @ApiResponse(responseCode = "400", description = "Invalid Id Supplied")
//    @ApiResponse(responseCode = "404", description = "Sequence not found")
//    public boolean deleteById(@SpanTag("deleteById.id") @PathVariable("id") String id ){
//        log.info("Deleting Sequence by Id: {}", id);
//        return  sequenceService.deleteById(id );
//    }
//
//    @NewSpan("Sequence-service")
//    @Timed(value = "io.hashimati.controllers.sequenceController.findAll", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for finding all sequence objects")
//
//    @Get("/findAll")
//    @Version("1")
//    @Operation(summary = "Retrieving all sequence objects as Json",
//            description = "A REST service, which returns all Sequence objects from the database.",
//            operationId = "FindAllSequence"
//    )
//    @ApiResponse(
//            content = @Content(mediaType = "application/json")
//    )
//    public Iterable<Sequence> findAll( ){
//        log.info("find All");
//        return sequenceService.findAll( );
//    }
//
//    @NewSpan("Sequence-service")
//    @Timed(value = "io.hashimati.controllers.sequenceController.update", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for update a sequence object")
//
//    @Put("/update")
//    @Version("1")
//    @Operation(summary = "Updating a sequence.",
//            description = "A REST service, which update a Sequence objects to the database.",
//            operationId = "UpdateSequence"
//    )
//    @ApiResponse(
//            content = @Content(mediaType = "application/json")
//    )
//    @ApiResponse(responseCode = "404", description = "Sequence not found")
//    public Sequence update(@SpanTag("update.sequence") @NonNull @Body Sequence sequence )
//    {
//        log.info("update {}", sequence);
//        return sequenceService.update(sequence );
//
//    }
//
//
//    @Get("/findByName")
//    @NewSpan("Sequence-service")
//    @Timed(value = "io.hashimati.controllers.sequenceController.findByName", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for finding a sequence object by Name")
//    @Operation(summary = "Find an entity by Name",
//    description = "A REST service, which retrieves a Sequence object by Name."
//    )
//    @ApiResponse(
//    content = @Content(mediaType = "application/json")
//    )
//    @ApiResponse(responseCode = "400", description = "Invalid Id Supplied")
//    @ApiResponse(responseCode = "404", description = "Sequence not found")
//    public Sequence findByName(String query ){
//          log.info("Finding Sequence By Name: {}", query);
//          return sequenceService.findByName(query );
//    }
//
//
//
//
//
//    @NewSpan("Sequence-service")
//    @Timed(value = "io.hashimati.controllers.sequenceController.updateby.Name", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for update a sequence object")
//
//    @Put("/updateByName")
//    @Version("1")
//    @Operation(summary = "Updating a sequence.",
//            description = "A REST service, which updates a Sequence objects to the database by Name, This service will only update these attributes {name, counter}.",
//            operationId = "UpdateSequencebyName"
//    )
//    @ApiResponse(
//            content = @Content(mediaType = "application/json")
//    )
//    @ApiResponse(responseCode = "404", description = "Sequence not found")
//    public Long updateName(@SpanTag("updateupdateby.Name.sequence") @QueryValue("query") String query,  @Body Sequence body )
//    {
//        log.info("update {}", query);
//        return sequenceService.updateByName(query, body.getName(), body.getCounter() );
//
//    }
//
//}
//
//
