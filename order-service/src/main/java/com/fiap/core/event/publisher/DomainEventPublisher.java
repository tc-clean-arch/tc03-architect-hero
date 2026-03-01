package com.fiap.core.event.publisher;

public interface DomainEventPublisher {

    /**
     * Publica um evento de domínio.
     * A implementação concreta decide
     * como esse evento será propagado
     * (CDI, Kafka, etc).
     */
    void publish(Object domainEvent);
}
