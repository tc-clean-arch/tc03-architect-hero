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
        implements EventPublishStrategy<PedidoCriadoEvent> {

    @Inject
    @Channel("pedido-criado-out")
    Emitter<String> emitter;

    @Inject
    ObjectMapper objectMapper;

    @Override
    public Class<PedidoCriadoEvent> eventType() {
        return PedidoCriadoEvent.class;
    }

    @Override
    public void publish(PedidoCriadoEvent event) {
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

