package com.fiap.infrastructure.contracts.events;

import java.time.Instant;
import java.util.UUID;

public record PagamentoPendenteEvent(
        UUID pedidoId,
        UUID clienteId,
        int valor,
        String motivo,
        int tentativas,
        Instant ocorridoEm
) implements IntegrationEvent {

    @Override
    public UUID aggregateId() {
        return pedidoId;
    }

    public static PagamentoPendenteEvent of(
            UUID pedidoId,
            UUID clienteId,
            int valor,
            String motivo,
            int tentativas
    ) {
        return new PagamentoPendenteEvent(
                pedidoId,
                clienteId,
                valor,
                motivo,
                tentativas,
                Instant.now()
        );
    }
}

