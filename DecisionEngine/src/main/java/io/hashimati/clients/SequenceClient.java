//package io.hashimati.clients;
//
//
//import io.micronaut.context.annotation.Parameter;
//import io.micronaut.http.annotation.*;
//import io.micronaut.http.client.annotation.Client;
//import jakarta.inject.Inject;
//import io.hashimati.domains.Sequence;
//
//import io.micronaut.http.MediaType;
//import io.micronaut.http.multipart.CompletedFileUpload;
//
//
//
//
//
//@Client("/api/v1/sequence")
//public interface SequenceClient {
//
//    @Post("/save")
//    public Sequence save(Sequence sequence);
//
//    @Get("/get")
//    public Sequence findById(@Parameter("id") String id);
//
//    @Delete("/delete/{id}")
//    public boolean deleteById(@PathVariable("id") String id);
//
//    @Get("/findAll")
//    public Iterable<Sequence> findAll( );
//
//    @Put("/update")
//    public Sequence update(@Body Sequence sequence);
//
//
//    @Get("/findAllByName")
//    public Sequence findByName(String query);
//
//
//    @Put("/updateByName")
//    public Long updateName(@QueryValue("query") String query,  @Body Sequence body);
//
//}
//
//
