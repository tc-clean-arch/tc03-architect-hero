package com.fiap.infrastructure.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.core.event.PedidoCriadoEvent;
import com.fiap.core.event.mapper.PedidoCriadoEventMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

@ApplicationScoped
public class PedidoCriadoKafkaStrategy
        implements EventPublishStrategy<com.fiap.core.event.PedidoCriadoEvent> {

    @Inject
    @Channel("pedido-criado-out")
    Emitter<String> emitter;

    @Inject
    ObjectMapper objectMapper;

    @Override
    public Class<com.fiap.core.event.PedidoCriadoEvent> eventType() {
        return com.fiap.core.event.PedidoCriadoEvent.class;
    }

    @Override
    public void publish(com.fiap.core.event.PedidoCriadoEvent event) {
        try {
            var contractEvent =
                    PedidoCriadoEventMapper.toContract(event);

            emitter.send(objectMapper.writeValueAsString(contractEvent));

        } catch (Exception e) {
            throw new IllegalStateException(
                    "Erro ao publicar PedidoCriadoEvent no Kafka", e
            );
        }
    }
}

