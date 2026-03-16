package com.fiap.infrastructure.event;

import com.fiap.core.event.publisher.DomainEventPublisher;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;

import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class KafkaDomainEventPublisher
        implements DomainEventPublisher {

    private final Map<Class<?>, EventPublishStrategy<?>> strategies =
            new HashMap<>();

    @Inject
    public KafkaDomainEventPublisher(
            Instance<EventPublishStrategy<?>> discoveredStrategies
    ) {
        discoveredStrategies.forEach(
                strategy -> strategies.put(strategy.eventType(), strategy)
        );
    }

    @Override
    @SuppressWarnings("unchecked")
    public void publish(Object domainEvent) {
        EventPublishStrategy<Object> strategy =
                (EventPublishStrategy<Object>)
                        strategies.get(domainEvent.getClass());

        if (strategy == null) {
            throw new IllegalStateException(
                    "Nenhuma strategy encontrada para evento: "
                            + domainEvent.getClass().getName()
            );
        }

        strategy.publish(domainEvent);
    }
}