package com.fiap.infra.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import contracts.events.PagamentoPendenteEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

@ApplicationScoped
public class PagamentoPendenteKafkaStrategy
        implements EventPublishStrategy {

    @Inject
    @Channel("pagamento-pendente-out")
    Emitter<String> emitter;

    @Inject
    ObjectMapper objectMapper;

    @Override
    public Class<?> eventType() {
        return PagamentoPendenteEvent.class;
    }

    @Override
    public void publish(Object event) {
        try {
            emitter.send(objectMapper.writeValueAsString(event));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

