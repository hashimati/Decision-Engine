package io.hashimati.services;


import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.micrometer.core.annotation.Timed;

import io.hashimati.domains.Variable;
import io.hashimati.repositories.VariableRepository;









@Singleton
public class VariableService {

    private static final Logger log = LoggerFactory.getLogger(VariableService.class);
    @Inject private VariableRepository variableRepository;
    @Inject private SequenceService sequenceService;

    @Timed(value = "io.hashimati.services.variableService.save", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for saving variable object")
    public Variable save(Variable variable ){
        log.info("Saving  Variable : {}", variable);
        //TODO insert your logic here!
        //saving Object

        var sequence = sequenceService.findById("variable");
        sequence.setCounter(sequence.getCounter()+1);
        sequenceService.update(sequence);
        variable.setId(sequence.getCounter()+"");

        return variableRepository.save(variable);

    }

    
    @Timed(value = "io.hashimati.services.variableService.findById", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for finding a variable object by id")
    public Variable findById(String id ){
        log.info("Finding Variable By Id: {}", id);
        return variableRepository.findById(id).orElse(null);
    }

    @Timed(value = "io.hashimati.services.variableService.deleteById", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for deleting a variable object by id")
    public boolean deleteById(String id ){
        log.info("Deleting Variable by Id: {}", id);
        try{
            variableRepository.deleteById(id);
            log.info("Deleted Variable by Id: {}", id);
            return true;
        }
        catch(Exception ex)
        {
            log.info("Failed to delete Variable by Id: {}", id);
            ex.printStackTrace();
            return false;
        }
    }

    @Timed(value = "io.hashimati.services.variableService.findAll", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for finding all variable objects")
    public Iterable<Variable> findAll( ) {
        log.info("Find All");
      return  variableRepository.findAll();
    }

    public boolean existsById(String id )
    {
        log.info("Check if id exists: {}", id);
        return  variableRepository.existsById(id);

    }

    @Timed(value = "io.hashimati.services.variableService.update", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for update a variable object")
    public Variable update(Variable variable )
    {
        log.info("update {}", variable);
        return variableRepository.update(variable);

    }
    
    @Timed(value = "io.hashimati.services.variableService.findByName", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for finding a variable object by id")
    public Variable findByName(String query ){
          log.info("Finding Variable By Name: {}", query);
          return variableRepository.findByName(query).orElse(null);
    }

    @Timed(value = "io.hashimati.services.variableService.findByContext", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for finding a variable object by Context")
    public Iterable<Variable> findAllByContext(String context){
        log.info("Finding variables by context: {}", context);
        return variableRepository.findAllByContext(context);
    }



}

