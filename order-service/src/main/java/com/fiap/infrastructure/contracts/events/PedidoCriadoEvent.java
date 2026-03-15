package com.fiap.infrastructure.contracts.events;

import java.time.Instant;
import java.util.UUID;

public record PedidoCriadoEvent(
        UUID pedidoId,
        UUID clienteId,
        int valor,
        Instant ocorridoEm
) implements IntegrationEvent {

    @Override
    public UUID aggregateId() {
        return pedidoId;
    }

    public static PedidoCriadoEvent of(
            UUID pedidoId,
            UUID clienteId,
            int valor
    ) {
        return new PedidoCriadoEvent(
                pedidoId,
                clienteId,
                valor,
                Instant.now()
        );
    }
}


