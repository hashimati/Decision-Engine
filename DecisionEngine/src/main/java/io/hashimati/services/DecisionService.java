package io.hashimati.services;


import jakarta.inject.Inject;
import io.micronaut.http.multipart.CompletedFileUpload;
import jakarta.inject.Singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.micrometer.core.annotation.Timed;

import io.hashimati.domains.Decision;
import io.hashimati.repositories.DecisionRepository;









@Singleton
public class DecisionService {

    private static final Logger log = LoggerFactory.getLogger(DecisionService.class);
    @Inject private DecisionRepository decisionRepository;
    @Inject private SequenceService sequenceService;

    @Timed(value = "io.hashimati.services.decisionService.save", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for saving decision object")
    public Decision save(Decision decision ){
        log.info("Saving  Decision : {}", decision);
        //TODO insert your logic here!
        var sequence = sequenceService.findById("decision");
        sequence.setCounter(sequence.getCounter()+1);
        sequenceService.update(sequence);
        decision.setId(sequence.getCounter()+"");
        //saving Object
        return decisionRepository.save(decision);

    }

    
    @Timed(value = "io.hashimati.services.decisionService.findById", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for finding a decision object by id")
    public Decision findById(String id ){
        log.info("Finding Decision By Id: {}", id);
        return decisionRepository.findById(id).orElse(null);
    }

    @Timed(value = "io.hashimati.services.decisionService.deleteById", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for deleting a decision object by id")
    public boolean deleteById(String id ){
        log.info("Deleting Decision by Id: {}", id);
        try{
            decisionRepository.deleteById(id);
            log.info("Deleted Decision by Id: {}", id);
            return true;
        }
        catch(Exception ex)
        {
            log.info("Failed to delete Decision by Id: {}", id);
            ex.printStackTrace();
            return false;
        }
    }

    @Timed(value = "io.hashimati.services.decisionService.findAll", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for finding all decision objects")
    public Iterable<Decision> findAll( ) {
        log.info("Find All");
      return  decisionRepository.findAll();
    }

    public boolean existsById(String id )
    {
        log.info("Check if id exists: {}", id);
        return  decisionRepository.existsById(id);

    }

    @Timed(value = "io.hashimati.services.decisionService.update", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for update a decision object")
    public Decision update(Decision decision )
    {
        log.info("update {}", decision);
        return decisionRepository.update(decision);

    }
    
    @Timed(value = "io.hashimati.services.decisionService.findByName", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for finding a decision object by id")
    public Decision findByName(String query ){
          log.info("Finding Decision By Name: {}", query);
          return decisionRepository.findByName(query).orElse(null);
    }


    
    @Timed(value = "io.hashimati.services.decisionService.findAllByContext", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for finding a decision object by Context")
    public Iterable<Decision> findAllByContext(String query ){
          log.info("Finding Decision By Context: {}", query);
          return decisionRepository.findAllByContext(query);
    }



    @Timed(value = "io.hashimati.services.decisionService.updateby.Name", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for update a decision object")
    public Long updateByName(String query,  String name )
    {
        log.info("update by {}", query);
        return decisionRepository.updateByName(query, name);
    }

}

