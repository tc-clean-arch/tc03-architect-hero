package com.fiap.infra.event.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.core.exception.PagamentoAindaPendenteException;
import com.fiap.core.usecases.ProcessarPagamentoInteractor;
import com.fiap.core.usecases.ProcessarPagamentoInputData;
import io.smallrye.reactive.messaging.annotations.Blocking;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.jboss.logging.Logger;

@ApplicationScoped
public class PedidoCriadoKafkaConsumer {

    private static final Logger LOG =
            Logger.getLogger(PedidoCriadoKafkaConsumer.class);

    @Inject
    ProcessarPagamentoInteractor interactor;

    @Inject
    ObjectMapper objectMapper;

    @Incoming("pedido-criado-in")
    @Blocking
    public void consume(String payload) {
        try {
           com.fiap.infra.events.PedidoCriadoEvent event =
                    objectMapper.readValue(payload, com.fiap.infra.events.PedidoCriadoEvent.class);

            interactor.execute(
                    new ProcessarPagamentoInputData(
                            event.pedidoId(),
                            event.clienteId(),
                            event.valor(),
                            0
                    )
            );

        } catch (PagamentoAindaPendenteException e) {
            LOG.infof(
                    "⏳ Pagamento pendente tratado | pedidoId=%s",
                    e.getMessage()
            );

        } catch (Exception e) {
            throw new RuntimeException(
                    "Erro ao desserializar PedidoCriadoEvent",
                    e
            );
        }
    }

}
