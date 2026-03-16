package com.fiap.infrastructure.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.infrastructure.contracts.events.PagamentoPendenteEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

@ApplicationScoped
public class PagamentoPendenteKafkaStrategy implements EventPublishStrategy<PagamentoPendenteEvent> {

    @Inject
    @Channel("pagamento-pendente-out")
    Emitter<String> emitter;

    @Inject
    ObjectMapper objectMapper;

    @Override
    public Class<PagamentoPendenteEvent> eventType() {
        return PagamentoPendenteEvent.class;
    }

    @Override
    public void publish(PagamentoPendenteEvent event) {
        try {
            PagamentoPendenteEvent contractEvent =
                    (PagamentoPendenteEvent) event;

            emitter.send(objectMapper.writeValueAsString(contractEvent));

        } catch (Exception e) {
            throw new RuntimeException(
                    "Erro ao serializar PagamentoPendenteEvent",
                    e
            );
        }
    }
}
