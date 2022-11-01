package io.hashimati.controllers;

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



import io.hashimati.domains.Variable;
import io.hashimati.services.VariableService;


@Tag(name = "Variable")
@Controller("/api/v1/variable")
@CircuitBreaker(attempts = "5", maxDelay = "3s", reset = "30")
public class VariableController {

    private static final Logger log = LoggerFactory.getLogger(VariableController.class);

    @Inject private VariableService variableService;


    @NewSpan("Variable-service")
    @Timed(value = "io.hashimati.controllers.variableController.save", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for saving variable object")
    
    @Post("/save")
    @Version("1")
    @Operation(summary = "Creating a variable and Storing in the database",
            description = "A REST service, which saves Variable objects to the database.",
            operationId = "SaveVariable"
    )
    @ApiResponse(
            content = @Content(mediaType = "application/json")
    )
    @ApiResponse(responseCode = "400", description = "Invalid Object Supplied")
    @ApiResponse(responseCode = "404", description = "Variable not stored")
    public Variable save(@SpanTag("save.variable") @NonNull @Body Variable variable  ){
        log.info("Saving  Variable : {}", variable);
        //TODO insert your logic here!

        //saving Object
        return variableService.save(variable );
    }


    @NewSpan("Variable-service")
    @Timed(value = "io.hashimati.controllers.variableController.findById", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for finding a variable object by id")
    
    @Get("/get")
    @Version("1")
    @Operation(summary = "Getting a variable by Id",
        description = "A REST service, which retrieves a Variable object by Id.",
        operationId = "FindByIdVariable"
    )
    @ApiResponse(
        content = @Content(mediaType = "application/json")
    )
    @ApiResponse(responseCode = "400", description = "Invalid Id Supplied")
    @ApiResponse(responseCode = "404", description = "Variable not found")
    public Variable findById(@SpanTag("findById.id") @Parameter("id") String id ){
        return variableService.findById(id );
    }

    @NewSpan("Variable-service")
    @Timed(value = "io.hashimati.controllers.variableController.deleteById", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for deleting a variable object by id")
    
    @Delete("/delete/{id}")
    @Version("1")
    @Operation(summary = "Deleting a variable by ID",
            description = "A REST service, which deletes Variable object from the database.",
            operationId = "DeleteByIdVariable"
    )
    @ApiResponse(
            content = @Content(mediaType = "boolean")
    )
    @ApiResponse(responseCode = "400", description = "Invalid Id Supplied")
    @ApiResponse(responseCode = "404", description = "Variable not found")
    public boolean deleteById(@SpanTag("deleteById.id") @PathVariable("id") String id ){
        log.info("Deleting Variable by Id: {}", id);
        return  variableService.deleteById(id );
    }

    @NewSpan("Variable-service")
    @Timed(value = "io.hashimati.controllers.variableController.findAll", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for finding all variable objects")
    
    @Get("/findAll")
    @Version("1")
    @Operation(summary = "Retrieving all variable objects as Json",
            description = "A REST service, which returns all Variable objects from the database.",
            operationId = "FindAllVariable"
    )
    @ApiResponse(
            content = @Content(mediaType = "application/json")
    )
    public Iterable<Variable> findAll( ){
        log.info("find All");
        return variableService.findAll( );
    }

    @NewSpan("Variable-service")
    @Timed(value = "io.hashimati.controllers.variableController.update", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for update a variable object")
    
    @Put("/update")
    @Version("1")
    @Operation(summary = "Updating a variable.",
            description = "A REST service, which update a Variable objects to the database.",
            operationId = "UpdateVariable"
    )
    @ApiResponse(
            content = @Content(mediaType = "application/json")
    )
    @ApiResponse(responseCode = "404", description = "Variable not found")
    public Variable update(@SpanTag("update.variable") @NonNull @Body Variable variable )
    {
        log.info("update {}", variable);
        return variableService.update(variable );

    }

    
    @Get("/findByName")
    @NewSpan("Variable-service")
    @Timed(value = "io.hashimati.controllers.variableController.findByName", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for finding a variable object by Name")
    @Operation(summary = "Find an entity by Name",
    description = "A REST service, which retrieves a Variable object by Name."
    )
    @ApiResponse(
    content = @Content(mediaType = "application/json")
    )
    @ApiResponse(responseCode = "400", description = "Invalid Id Supplied")
    @ApiResponse(responseCode = "404", description = "Variable not found")
    public Variable findByName(String query ){
          log.info("Finding Variable By Name: {}", query);
          return variableService.findByName(query );
    }

    @Get("/findAllByContext")
    @NewSpan("Variable-service")
    @Timed(value = "io.hashimati.controllers.variableController.findAllByContext", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for finding a variable object by Name")
    @Operation(summary = "Find an entity by Name",
            description = "A REST service, which retrieves all Variable objects by Context."
    )
    @ApiResponse(
            content = @Content(mediaType = "application/json")
    )
    @ApiResponse(responseCode = "400", description = "Invalid Id Supplied")
    @ApiResponse(responseCode = "404", description = "Variable not found")
    public Iterable<Variable> findAllByContext(String query ){
        log.info("Finding Variables By context: {}", query);
        return variableService.findAllByContext(query );
    }
}


