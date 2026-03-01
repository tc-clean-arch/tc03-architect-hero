package com.fiap.infrastructure.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.core.entities.StatusPedido;
import com.fiap.infrastructure.db.repository.PedidoPanacheRepository;
import contracts.events.PagamentoAbandonadoEvent;
import io.smallrye.reactive.messaging.annotations.Blocking;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.jboss.logging.Logger;

@ApplicationScoped
public class PagamentoAbandonadoKafkaConsumer {

    private static final Logger LOG =
            Logger.getLogger(PagamentoAbandonadoKafkaConsumer.class);

    @Inject
    ObjectMapper objectMapper;

    @Inject
    PedidoPanacheRepository pedidoRepository;

    @Incoming("pagamento-abandonado-in")
    @Blocking
    @Transactional
    public void consumir(String payload) {

        PagamentoAbandonadoEvent event;

        try {
            event = objectMapper.readValue(
                    payload,
                    PagamentoAbandonadoEvent.class
            );
        } catch (Exception e) {
            LOG.error("❌ Payload inválido para PagamentoAbandonadoEvent", e);
            return; // não quebra o consumer
        }

        LOG.warnf(
                "💀 Evento PagamentoAbandonado recebido | pedidoId=%s",
                event.pedidoId()
        );

        pedidoRepository.findByIdOptional(event.pedidoId())
                .ifPresentOrElse(
                        entity -> {

                            if (entity.status == StatusPedido.PENDENTE_PAGAMENTO) {
                                LOG.infof(
                                        "🔁 Pedido já está PENDENTE_PAGAMENTO | pedidoId=%s",
                                        entity.id
                                );
                                return;
                            }

                            entity.status = StatusPedido.PENDENTE_PAGAMENTO;

                            LOG.infof(
                                    "📦 Pedido marcado como PENDENTE_PAGAMENTO | pedidoId=%s",
                                    entity.id
                            );
                        },
                        () -> LOG.warnf(
                                "⚠️ Pedido não encontrado | pedidoId=%s",
                                event.pedidoId()
                        )
                );
    }
}
