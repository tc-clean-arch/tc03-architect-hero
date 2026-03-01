package com.fiap.infra.event;

import com.fiap.core.usecases.ProcessarPagamentoInputData;
import com.fiap.core.usecases.ProcessarPagamentoInteractor;
import com.fiap.infra.dto.PedidoPendente;
import com.fiap.infra.gateway.PedidoGateway;
import io.smallrye.reactive.messaging.annotations.Blocking;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.jboss.logging.Logger;

import java.util.List;

@ApplicationScoped
public class PagamentoDisponivelKafkaConsumer {

    private static final Logger LOG =
            Logger.getLogger(PagamentoDisponivelKafkaConsumer.class);

    @Inject
    PedidoGateway pedidoGateway;

    @Inject
    ProcessarPagamentoInteractor processarPagamento;

    @Incoming("pagamento-disponivel-in")
    @Blocking
    public void consumir(String payload) {

        LOG.info("🟢 Pagamento disponível novamente. Reprocessando pendentes.");

        List<PedidoPendente> pendentes =
                pedidoGateway.buscarPendentes();

        for (PedidoPendente pedido : pendentes) {

            processarPagamento.execute(
                    new ProcessarPagamentoInputData(
                            pedido.pedidoId(),
                            pedido.clienteId(),
                            pedido.valor(),
                            0
                    )
            );
        }
    }
}
