package io.hashimati.clients;


import io.micronaut.context.annotation.Parameter;
import io.micronaut.http.annotation.*;
import io.micronaut.http.client.annotation.Client;
import jakarta.inject.Inject;
import io.hashimati.domains.Rule;

import io.micronaut.http.MediaType;
import io.micronaut.http.multipart.CompletedFileUpload;





@Client("/api/v1/rule")
public interface RuleClient {

    @Post("/save")
    public Rule save(Rule rule);

    @Get("/get")
    public Rule findById(@Parameter("id") String id);

    @Delete("/delete/{id}")
    public boolean deleteById(@PathVariable("id") String id);

    @Get("/findAll")
    public Iterable<Rule> findAll( );

    @Put("/update")
    public Rule update(@Body Rule rule);


}


