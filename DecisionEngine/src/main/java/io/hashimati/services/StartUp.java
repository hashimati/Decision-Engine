package io.hashimati.services;


import io.hashimati.domains.Sequence;
import io.hashimati.repositories.SequenceRepository;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.runtime.event.annotation.EventListener;
import io.micronaut.runtime.server.event.ServerStartupEvent;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
@Introspected
public class StartUp {
    @Inject private SequenceService sequenceService;
    @EventListener
    public void createSequence(final ServerStartupEvent event)
    {
        Sequence context = new Sequence();
        context.setCounter(0);
        context.setName("context");
        context.setId("context");

        Sequence variable = new Sequence();
        variable.setCounter(0);
        variable.setName("variable");
        variable.setId("variable");

        Sequence rule = new Sequence();
        rule.setCounter(0);
        rule.setName("rule");
        rule.setId("rule");

        Sequence decision = new Sequence();
        decision.setCounter(0);
        decision.setName("decision");
        decision.setId("decision");

        if(sequenceService.findAll().iterator().hasNext())
        {
            return ;
        }
        sequenceService.save(context);
        sequenceService.save(variable);
        sequenceService.save(rule);
        sequenceService.save(decision);

    }
}
