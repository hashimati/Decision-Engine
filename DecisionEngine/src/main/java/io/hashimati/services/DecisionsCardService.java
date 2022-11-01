package io.hashimati.services;


import jakarta.inject.Inject;
import io.micronaut.http.multipart.CompletedFileUpload;
import jakarta.inject.Singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.micrometer.core.annotation.Timed;

import io.hashimati.domains.DecisionsCard;
import io.hashimati.repositories.DecisionsCardRepository;









@Singleton
public class DecisionsCardService {

    private static final Logger log = LoggerFactory.getLogger(DecisionsCardService.class);
    @Inject private DecisionsCardRepository decisionsCardRepository;
    

    @Timed(value = "io.hashimati.services.decisionsCardService.save", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for saving decisionsCard object")
    public DecisionsCard save(DecisionsCard decisionsCard ){
        log.info("Saving  DecisionsCard : {}", decisionsCard);
        //TODO insert your logic here!
        //saving Object
        return decisionsCardRepository.save(decisionsCard);

    }

    
    @Timed(value = "io.hashimati.services.decisionsCardService.findById", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for finding a decisionsCard object by id")
    public DecisionsCard findById(String id ){
        log.info("Finding DecisionsCard By Id: {}", id);
        return decisionsCardRepository.findById(id).orElse(null);
    }

    @Timed(value = "io.hashimati.services.decisionsCardService.deleteById", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for deleting a decisionsCard object by id")
    public boolean deleteById(String id ){
        log.info("Deleting DecisionsCard by Id: {}", id);
        try{
            decisionsCardRepository.deleteById(id);
            log.info("Deleted DecisionsCard by Id: {}", id);
            return true;
        }
        catch(Exception ex)
        {
            log.info("Failed to delete DecisionsCard by Id: {}", id);
            ex.printStackTrace();
            return false;
        }
    }

    @Timed(value = "io.hashimati.services.decisionsCardService.findAll", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for finding all decisionsCard objects")
    public Iterable<DecisionsCard> findAll( ) {
        log.info("Find All");
      return  decisionsCardRepository.findAll();
    }

    public boolean existsById(String id )
    {
        log.info("Check if id exists: {}", id);
        return  decisionsCardRepository.existsById(id);

    }

    @Timed(value = "io.hashimati.services.decisionsCardService.update", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for update a decisionsCard object")
    public DecisionsCard update(DecisionsCard decisionsCard )
    {
        log.info("update {}", decisionsCard);
        return decisionsCardRepository.update(decisionsCard);

    }
    
    @Timed(value = "io.hashimati.services.decisionsCardService.findByName", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for finding a decisionsCard object by id")
    public DecisionsCard findByName(String query ){
          log.info("Finding DecisionsCard By Name: {}", query);
          return decisionsCardRepository.findByName(query).orElse(null);
    }


    
    @Timed(value = "io.hashimati.services.decisionsCardService.findAllByContext", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for finding a decisionsCard object by Context")
    public Iterable<DecisionsCard> findAllByContext(String query ){
          log.info("Finding DecisionsCard By Context: {}", query);
          return decisionsCardRepository.findAllByContext(query);
    }


}

