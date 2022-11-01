package io.hashimati.clients;


import io.micronaut.context.annotation.Parameter;
import io.micronaut.http.annotation.*;
import io.micronaut.http.client.annotation.Client;
import jakarta.inject.Inject;
import io.hashimati.domains.Context;

import io.micronaut.http.MediaType;
import io.micronaut.http.multipart.CompletedFileUpload;





@Client("/api/v1/context")
public interface ContextClient {

    @Post("/save")
    public Context save(Context context);

    @Get("/get")
    public Context findById(@Parameter("id") String id);

    @Delete("/delete/{id}")
    public boolean deleteById(@PathVariable("id") String id);

    @Get("/findAll")
    public Iterable<Context> findAll( );

    @Put("/update")
    public Context update(@Body Context context);


    @Get("/findAllByName")
    public Context findByName(String query);


    @Put("/updateByName")
    public Long updateName(@QueryValue("query") String query,  @Body Context body);

}


