package com.fiap.core.usecases;

import com.fiap.core.event.DomainEventPublisher;
import com.fiap.infra.PagamentoExterno;
import contracts.events.PagamentoAprovadoEvent;
import contracts.events.PagamentoPendenteEvent;
import io.smallrye.faulttolerance.api.CircuitBreakerName;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.jboss.logging.Logger;

import java.time.temporal.ChronoUnit;

@ApplicationScoped
public class ProcessarPagamentoInteractor {

    private static final Logger LOG =
            Logger.getLogger(ProcessarPagamentoInteractor.class);

    @Inject
    PagamentoExterno pagamentoExterno;

    @Inject
    DomainEventPublisher eventPublisher;

    @CircuitBreaker(
            requestVolumeThreshold = 5,
            failureRatio = 0.5,
            delay = 30,
            delayUnit = ChronoUnit.SECONDS
    )
    @CircuitBreakerName("pagamentoExterno")
    @Fallback(fallbackMethod = "fallback")
    public void execute(ProcessarPagamentoInputData input) {

        LOG.infof(
                "💳 Processando pagamento | pedidoId=%s | clienteId=%s | valor=%d | tentativa=%d",
                input.pedidoId(),
                input.clienteId(),
                input.valor(),
                input.tentativas()
        );

        pagamentoExterno.processar(
                input.pedidoId(),
                input.clienteId(),
                input.valor()
        );

        LOG.infof(
                "✅ Pagamento aprovado | pedidoId=%s",
                input.pedidoId()
        );

        eventPublisher.publish(
                PagamentoAprovadoEvent.of(
                        input.pedidoId(),
                        input.clienteId(),
                        input.valor()
                )
        );
    }

    /**
     * Fallback:
     * - Serviço instável
     * - Timeout
     * - Bad Gateway
     * - Circuit Breaker OPEN
     *
     * ❗ NÃO incrementa tentativas
     * ❗ NÃO dispara retry
     */
    void fallback(ProcessarPagamentoInputData input, Throwable cause) {

        LOG.warnf(
                "⏳ Pagamento pendente | pedidoId=%s | clienteId=%s | tentativaAtual=%d | motivo=%s",
                input.pedidoId(),
                input.clienteId(),
                input.tentativas(),
                cause.getMessage()
        );

        eventPublisher.publish(
                PagamentoPendenteEvent.of(
                        input.pedidoId(),
                        input.clienteId(),
                        input.valor(),
                        cause.getMessage(),
                        input.tentativas()
                )
        );
    }
}
