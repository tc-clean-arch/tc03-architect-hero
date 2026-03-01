package com.fiap.infrastructure.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.core.usecases.pedidos.atualizar.AtualizarStatusPedidoInputBoundary;
import com.fiap.core.usecases.pedidos.atualizar.AtualizarStatusPedidoInputData;
import contracts.events.PagamentoAprovadoEvent;
import io.smallrye.reactive.messaging.annotations.Blocking;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.control.ActivateRequestContext;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.jboss.logging.Logger;

@ApplicationScoped
public class PagamentoAprovadoKafkaConsumer {

    private static final Logger LOG =
            Logger.getLogger(PagamentoAprovadoKafkaConsumer.class);

    @Inject
    ObjectMapper objectMapper;

    @Inject
    AtualizarStatusPedidoInputBoundary atualizarStatusBoundary;

    @Incoming("pagamento-aprovado-in")
    @Blocking
    @ActivateRequestContext
    public void consume(String payload) {

        try {
            PagamentoAprovadoEvent event =
                    objectMapper.readValue(payload, PagamentoAprovadoEvent.class);

            LOG.infof(
                    "💰 Evento PagamentoAprovado recebido | pedidoId=%s",
                    event.pedidoId()
            );

            atualizarStatusBoundary.marcarComoPago(
                    new AtualizarStatusPedidoInputData(event.pedidoId())
            );

        } catch (Exception e) {
            LOG.warn(
                    "⚠️ Erro ao processar pagamento.aprovado (evento ignorado)",
                    e
            );
        }
    }
}
