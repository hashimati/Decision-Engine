package io.hashimati.services;


import io.hashimati.domains.Decision;
import io.hashimati.domains.DecisionsCardResult;
import io.hashimati.domains.VariableTypes;
import io.hashimati.repositories.DecisionRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.micrometer.core.annotation.Timed;

import io.hashimati.domains.DecisionsCard;
import io.hashimati.repositories.DecisionsCardRepository;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;


@Singleton
public class DecisionsCardService {

    private static final Logger log = LoggerFactory.getLogger(DecisionsCardService.class);
    @Inject private DecisionsCardRepository decisionsCardRepository;
    @Inject private DecisionRepository decisionRepository;

    @Inject
    private ExpressionEvaluation expressionEvaluation;

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


    @Timed(value = "io.hashimati.services.decisionsCardService.processDecisionCard", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for finding a decisionsCard object by Context")
    public DecisionsCardResult processDecisionCard(String context, String decisionCardID, HashMap<String, Object> parameters)
    {
        log.info("Processing card: {}", decisionCardID);
        DecisionsCard decisionCard = decisionsCardRepository.findByIdAndContext(decisionCardID, context).orElse(null);

        if(decisionCard == null)
            return new DecisionsCardResult(null, null, "The decision card is not found");

        List<Decision> decisions = decisionCard.getDecisions().stream()
                .map(x->decisionRepository.findById(x)
                        .orElse(null)).collect(Collectors.toList());
        if(decisions == null || decisions.isEmpty())
            return new DecisionsCardResult(null, null, "There is no defined rule in the decision card!");

        
        HashMap<String, Object> result = new HashMap<>();
        decisions.forEach(x->{
            String rule = x.getRule();
            for (String y : parameters.keySet()) {
                rule = rule.replace(y,parameters.get(y).toString());
            }

            if(VariableTypes.isBoolean(x.getReturnType()))
            {
                result.putIfAbsent(x.getName(), expressionEvaluation.evalBool(rule));
            }
            else if(VariableTypes.isString(x.getReturnType()))
            {
                result.putIfAbsent(x.getName(), expressionEvaluation.evalString(rule));
            }
            else if(VariableTypes.isNumeric(x.getReturnType()))
            {
                result.putIfAbsent(x.getName(), expressionEvaluation.evalDouble(rule));
            }
        });
        return new DecisionsCardResult(parameters, result, "The card processed successfully!");

    }


}

