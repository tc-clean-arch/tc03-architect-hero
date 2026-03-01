package com.fiap.core.event;

public interface DomainEventPublisher {
    void publish(Object event);
}

