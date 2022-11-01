package io.hashimati.clients;


import io.micronaut.context.annotation.Parameter;
import io.micronaut.http.annotation.*;
import io.micronaut.http.client.annotation.Client;
import jakarta.inject.Inject;
import io.hashimati.domains.DecisionsCard;

import io.micronaut.http.MediaType;
import io.micronaut.http.multipart.CompletedFileUpload;





@Client("/api/v1/decisionsCard")
public interface DecisionsCardClient {

    @Post("/save")
    public DecisionsCard save(DecisionsCard decisionsCard);

    @Get("/get")
    public DecisionsCard findById(@Parameter("id") String id);

    @Delete("/delete/{id}")
    public boolean deleteById(@PathVariable("id") String id);

    @Get("/findAll")
    public Iterable<DecisionsCard> findAll( );

    @Put("/update")
    public DecisionsCard update(@Body DecisionsCard decisionsCard);


    @Get("/findAllByName")
    public DecisionsCard findByName(String query);

    @Get("/findAllByContext")
    public Iterable<DecisionsCard> findAllByContext(String query);

}


