package com.fiap.infra.event;

public interface EventPublishStrategy {

    Class<?> eventType();

    void publish(Object event);
}
