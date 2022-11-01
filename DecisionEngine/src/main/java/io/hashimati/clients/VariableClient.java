package io.hashimati.clients;


import io.micronaut.context.annotation.Parameter;
import io.micronaut.http.annotation.*;
import io.micronaut.http.client.annotation.Client;
import io.hashimati.domains.Variable;


@Client("/api/v1/variable")
public interface VariableClient {

    @Post("/save")
    public Variable save(Variable variable);

    @Get("/get")
    public Variable findById(@Parameter("id") String id);

    @Delete("/delete/{id}")
    public boolean deleteById(@PathVariable("id") String id);

    @Get("/findAll")
    public Iterable<Variable> findAll( );

    @Put("/update")
    public Variable update(@Body Variable variable);


    @Get("/findAllByName")
    public Variable findByName(String query);

}


