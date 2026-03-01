package com.fiap.core.event;

import java.time.Instant;
import java.util.UUID;
import com.fiap.core.entities.StatusPedido;

public record PedidoCriadoEvent(
        UUID pedidoId,
        UUID clienteId,
        int valor,
        StatusPedido status,
        Instant ocorridoEm
) {
    public static PedidoCriadoEvent of(
            UUID pedidoId,
            UUID clienteId,
            int valor,
            StatusPedido status
    ) {
        return new PedidoCriadoEvent(
                pedidoId,
                clienteId,
                valor,
                status,
                Instant.now()
        );
    }
}
