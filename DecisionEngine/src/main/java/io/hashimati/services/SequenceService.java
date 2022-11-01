package io.hashimati.services;


import io.micronaut.context.event.StartupEvent;
import io.micronaut.runtime.event.annotation.EventListener;
import jakarta.inject.Inject;
import io.micronaut.http.multipart.CompletedFileUpload;
import jakarta.inject.Singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.micrometer.core.annotation.Timed;

import io.hashimati.domains.Sequence;
import io.hashimati.repositories.SequenceRepository;









@Singleton
public class SequenceService {

    private static final Logger log = LoggerFactory.getLogger(SequenceService.class);
    @Inject private SequenceRepository sequenceRepository;
    

    @Timed(value = "io.hashimati.services.sequenceService.save", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for saving sequence object")
    public Sequence save(Sequence sequence ){
        log.info("Saving  Sequence : {}", sequence);
        //TODO insert your logic here!
        //saving Object
        return sequenceRepository.save(sequence);

    }

    
    @Timed(value = "io.hashimati.services.sequenceService.findById", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for finding a sequence object by id")
    public Sequence findById(String id ){
        log.info("Finding Sequence By Id: {}", id);
        return sequenceRepository.findById(id).orElse(null);
    }

    @Timed(value = "io.hashimati.services.sequenceService.deleteById", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for deleting a sequence object by id")
    public boolean deleteById(String id ){
        log.info("Deleting Sequence by Id: {}", id);
        try{
            sequenceRepository.deleteById(id);
            log.info("Deleted Sequence by Id: {}", id);
            return true;
        }
        catch(Exception ex)
        {
            log.info("Failed to delete Sequence by Id: {}", id);
            ex.printStackTrace();
            return false;
        }
    }

    @Timed(value = "io.hashimati.services.sequenceService.findAll", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for finding all sequence objects")
    public Iterable<Sequence> findAll( ) {
        log.info("Find All");
      return  sequenceRepository.findAll();
    }

    public boolean existsById(String id )
    {
        log.info("Check if id exists: {}", id);
        return  sequenceRepository.existsById(id);

    }

    @Timed(value = "io.hashimati.services.sequenceService.update", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for update a sequence object")
    public Sequence update(Sequence sequence )
    {
        log.info("update {}", sequence);
        return sequenceRepository.update(sequence);

    }
    
    @Timed(value = "io.hashimati.services.sequenceService.findByName", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for finding a sequence object by id")
    public Sequence findByName(String query ){
          log.info("Finding Sequence By Name: {}", query);
          return sequenceRepository.findByName(query).orElse(null);
    }




    @Timed(value = "io.hashimati.services.sequenceService.updateby.Name", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for update a sequence object")
    public Long updateByName(String query,  String name, int counter )
    {
        log.info("update by {}", query);
        return sequenceRepository.updateByName(query, name, counter);
    }




}

