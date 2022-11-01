package io.hashimati.clients;


import io.micronaut.context.annotation.Parameter;
import io.micronaut.http.annotation.*;
import io.micronaut.http.client.annotation.Client;
import jakarta.inject.Inject;
import io.hashimati.domains.Decision;

import io.micronaut.http.MediaType;
import io.micronaut.http.multipart.CompletedFileUpload;





@Client("/api/v1/decision")
public interface DecisionClient {

    @Post("/save")
    public Decision save(Decision decision);

    @Get("/get")
    public Decision findById(@Parameter("id") String id);

    @Delete("/delete/{id}")
    public boolean deleteById(@PathVariable("id") String id);

    @Get("/findAll")
    public Iterable<Decision> findAll( );

    @Put("/update")
    public Decision update(@Body Decision decision);


    @Get("/findAllByName")
    public Decision findByName(String query);

    @Get("/findAllByContext")
    public Iterable<Decision> findAllByContext(String query);


    @Put("/updateByName")
    public Long updateName(@QueryValue("query") String query,  @Body Decision body);

}


