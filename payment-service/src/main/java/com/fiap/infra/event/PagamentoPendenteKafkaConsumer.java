package com.fiap.infra.event;

import com.fiap.core.event.DomainEventPublisher;
import com.fiap.core.usecases.ProcessarPagamentoInputData;
import com.fiap.core.usecases.ProcessarPagamentoInteractor;

import com.fiap.infra.events.PagamentoAbandonadoEvent;
import com.fiap.infra.events.PagamentoPendenteEvent;
import io.smallrye.reactive.messaging.annotations.Blocking;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.jboss.logging.Logger;


@ApplicationScoped
public class PagamentoPendenteKafkaConsumer {

    private static final Logger LOG =
            Logger.getLogger(PagamentoPendenteKafkaConsumer.class);

    @Inject
    ProcessarPagamentoInteractor interactor;

    @Inject
    DomainEventPublisher eventPublisher;

    @Incoming("pagamento-pendente-in")
    @Blocking
    public void retry(PagamentoPendenteEvent event) {

        if (event.tentativas() >= 5) {

            LOG.errorf(
                    "💀 Pagamento abandonado | pedidoId=%s | tentativas=%d",
                    event.pedidoId(),
                    event.tentativas()
            );

            eventPublisher.publish(
                    PagamentoAbandonadoEvent.of(
                            event.pedidoId(),
                            "Falha definitiva após " + event.tentativas() + " tentativas"
                    )
            );

            return;
        }

        int proximaTentativa = event.tentativas() + 1;

        LOG.infof(
                "🔁 Reprocessando pagamento | pedidoId=%s | tentativa=%d",
                event.pedidoId(),
                proximaTentativa
        );

        interactor.execute(
                new ProcessarPagamentoInputData(
                        event.pedidoId(),
                        event.clienteId(),
                        event.valor(),
                        proximaTentativa
                )
        );
    }
}

