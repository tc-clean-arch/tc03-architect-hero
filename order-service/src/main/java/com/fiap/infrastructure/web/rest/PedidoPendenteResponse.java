package com.fiap.infrastructure.web.rest;

import java.util.UUID;

public record PedidoPendenteResponse(
        UUID pedidoId,
        UUID clienteId,
        int total
) {}

