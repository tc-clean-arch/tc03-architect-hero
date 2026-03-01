package com.fiap.core.event;

import java.time.Instant;
import java.util.UUID;

public record PedidoPagoEvent(
        UUID pedidoId,
        Long restauranteId,
        Instant ocorridoEm
) {
    public static PedidoPagoEvent of(UUID pedidoId, Long restauranteId) {
        return new PedidoPagoEvent(pedidoId, restauranteId, Instant.now());
    }
}
