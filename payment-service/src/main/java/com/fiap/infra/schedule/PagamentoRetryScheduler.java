package com.fiap.infra.schedule;

import com.fiap.core.usecases.ProcessarPagamentoInputData;
import com.fiap.core.usecases.ProcessarPagamentoInteractor;
import com.fiap.infra.gateway.PedidoGateway;
import com.fiap.infra.dto.PedidoPendente;
import io.quarkus.scheduler.Scheduled;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.jboss.logging.Logger;

import java.util.List;

@ApplicationScoped
public class PagamentoRetryScheduler {

    private static final Logger LOG =
            Logger.getLogger(PagamentoRetryScheduler.class);

    @Inject
    PedidoGateway pedidoGateway;

    @Inject
    ProcessarPagamentoInteractor processarPagamento;

    /**
     * Retry temporal:
     * - NÃO usa Kafka
     * - NÃO roda em loop agressivo
     * - Respeita o Circuit Breaker
     */
    @Scheduled(every = "30s")
    void tentarReprocessarPendentes() {

        List<PedidoPendente> pendentes =
                pedidoGateway.buscarPendentes();

        if (pendentes.isEmpty()) {
            return;
        }

        LOG.infof("🔁 Reprocessando %d pagamentos pendentes", pendentes.size());

        for (PedidoPendente pedido : pendentes) {

            processarPagamento.execute(
                    new ProcessarPagamentoInputData(
                            pedido.pedidoId(),
                            pedido.clienteId(),
                            pedido.valor(),
                            pedido.tentativas() + 1
                    )
            );
        }
    }
}

