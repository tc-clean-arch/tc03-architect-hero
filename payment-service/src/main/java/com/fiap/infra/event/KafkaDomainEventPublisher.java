package com.fiap.infra.event;

import com.fiap.core.event.DomainEventPublisher;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;

import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class KafkaDomainEventPublisher implements DomainEventPublisher {

    private final Map<Class<?>, EventPublishStrategy> strategies = new HashMap<>();

    @Inject
    public KafkaDomainEventPublisher(
            Instance<EventPublishStrategy> discoveredStrategies
    ) {
        discoveredStrategies.forEach(
                strategy -> strategies.put(strategy.eventType(), strategy)
        );
    }

    @Override
    public void publish(Object domainEvent) {

        for (var entry : strategies.entrySet()) {
            if (entry.getKey().isAssignableFrom(domainEvent.getClass())) {
                entry.getValue().publish(domainEvent);
                return;
            }
        }

        throw new IllegalStateException(
                "Nenhuma strategy encontrada para evento: "
                        + domainEvent.getClass().getName()
        );
    }
}

