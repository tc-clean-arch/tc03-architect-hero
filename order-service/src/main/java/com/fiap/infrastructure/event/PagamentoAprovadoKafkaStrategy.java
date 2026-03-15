package com.fiap.infrastructure.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.infrastructure.contracts.events.PagamentoAprovadoEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

@ApplicationScoped
public class PagamentoAprovadoKafkaStrategy implements EventPublishStrategy {

    @Inject
    @Channel("pagamento-aprovado-out")
    Emitter<String> emitter;

    @Inject
    ObjectMapper objectMapper;

    @Override
    public Class<?> eventType() {
        return PagamentoAprovadoEvent.class;
    }

    @Override
    public void publish(Object event) {
        try {
            PagamentoAprovadoEvent contractEvent =
                    (PagamentoAprovadoEvent) event;

            emitter.send(objectMapper.writeValueAsString(contractEvent));

        } catch (Exception e) {
            throw new RuntimeException(
                    "Erro ao serializar PagamentoAprovadoEvent",
                    e
            );
        }
    }
}
