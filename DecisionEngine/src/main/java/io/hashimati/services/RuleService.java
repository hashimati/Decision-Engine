package io.hashimati.services;


import jakarta.inject.Inject;
import io.micronaut.http.multipart.CompletedFileUpload;
import jakarta.inject.Singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.micrometer.core.annotation.Timed;

import io.hashimati.domains.Rule;
import io.hashimati.repositories.RuleRepository;









@Singleton
public class RuleService {

    private static final Logger log = LoggerFactory.getLogger(RuleService.class);
    @Inject private RuleRepository ruleRepository;
    

    @Timed(value = "io.hashimati.services.ruleService.save", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for saving rule object")
    public Rule save(Rule rule ){
        log.info("Saving  Rule : {}", rule);
        //TODO insert your logic here!
        //saving Object
        return ruleRepository.save(rule);

    }

    
    @Timed(value = "io.hashimati.services.ruleService.findById", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for finding a rule object by id")
    public Rule findById(String id ){
        log.info("Finding Rule By Id: {}", id);
        return ruleRepository.findById(id).orElse(null);
    }

    @Timed(value = "io.hashimati.services.ruleService.deleteById", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for deleting a rule object by id")
    public boolean deleteById(String id ){
        log.info("Deleting Rule by Id: {}", id);
        try{
            ruleRepository.deleteById(id);
            log.info("Deleted Rule by Id: {}", id);
            return true;
        }
        catch(Exception ex)
        {
            log.info("Failed to delete Rule by Id: {}", id);
            ex.printStackTrace();
            return false;
        }
    }

    @Timed(value = "io.hashimati.services.ruleService.findAll", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for finding all rule objects")
    public Iterable<Rule> findAll( ) {
        log.info("Find All");
      return  ruleRepository.findAll();
    }

    public boolean existsById(String id )
    {
        log.info("Check if id exists: {}", id);
        return  ruleRepository.existsById(id);

    }

    @Timed(value = "io.hashimati.services.ruleService.update", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for update a rule object")
    public Rule update(Rule rule )
    {
        log.info("update {}", rule);
        return ruleRepository.update(rule);

    }

}

