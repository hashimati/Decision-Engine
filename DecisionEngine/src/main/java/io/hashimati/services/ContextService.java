package io.hashimati.services;


import graphql.com.google.common.collect.Lists;
import io.hashimati.domains.Sequence;
import jakarta.inject.Inject;
import io.micronaut.http.multipart.CompletedFileUpload;
import jakarta.inject.Singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.micrometer.core.annotation.Timed;

import io.hashimati.domains.Context;
import io.hashimati.repositories.ContextRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.stream.Collectors;


@Singleton
public class ContextService {

    private static final Logger log = LoggerFactory.getLogger(ContextService.class);
    @Inject private ContextRepository contextRepository;
    
    @Inject private SequenceService sequenceService;

    @Timed(value = "io.hashimati.services.contextService.save", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for saving context object")
    public Context save(Context context ){
        log.info("Saving  Context : {}", context);
        //TODO insert your logic here!
        //saving Object
        var sequence = sequenceService.findById("context");
        sequence.setCounter(sequence.getCounter()+1);
        sequenceService.update(sequence);
        context.setId(sequence.getCounter()+"");

        return contextRepository.save(context);
    }

    
    @Timed(value = "io.hashimati.services.contextService.findById", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for finding a context object by id")
    public Context findById(String id ){
        log.info("Finding Context By Id: {}", id);
        return contextRepository.findById(id).orElse(null);
    }

    @Timed(value = "io.hashimati.services.contextService.deleteById", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for deleting a context object by id")
    public boolean deleteById(String id ){
        log.info("Deleting Context by Id: {}", id);
        try{
            contextRepository.deleteById(id);
            log.info("Deleted Context by Id: {}", id);
            return true;
        }
        catch(Exception ex)
        {
            log.info("Failed to delete Context by Id: {}", id);
            ex.printStackTrace();
            return false;
        }
    }

    @Timed(value = "io.hashimati.services.contextService.findAll", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for finding all context objects")
    public Iterable<Context> findAll( ) {
        log.info("Find All");
      return  contextRepository.findAll();
    }

    public HashSet<String> getAllNames()
    {
        log.info("Get All Context Names");
        HashSet<String> contexts = new HashSet<>();
        findAll().forEach(x->contexts.add(x.getName()));
        return contexts;
    }
    public boolean existsById(String id )
    {
        log.info("Check if id exists: {}", id);
        return  contextRepository.existsById(id);

    }

    @Timed(value = "io.hashimati.services.contextService.update", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for update a context object")
    public Context update(Context context )
    {
        log.info("update {}", context);
        return contextRepository.update(context);

    }
    
    @Timed(value = "io.hashimati.services.contextService.findByName", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for finding a context object by id")
    public Context findByName(String query ){
          log.info("Finding Context By Name: {}", query);
          return contextRepository.findByName(query).orElse(null);
    }




    @Timed(value = "io.hashimati.services.contextService.updateby.Name", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for update a context object")
    public Long updateByName(String query,  String name, String description )
    {
        log.info("update by {}", query);
        return contextRepository.updateByName(query, name, description );
    }

}

