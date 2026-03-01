package com.fiap.infrastructure.event;

public interface EventPublishStrategy<T> {

    Class<T> eventType();

    void publish(T event);
}

