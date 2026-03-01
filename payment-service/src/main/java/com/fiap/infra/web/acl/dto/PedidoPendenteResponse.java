package com.fiap.infra.web.acl.dto;

import java.util.UUID;

public record PedidoPendenteResponse(
        UUID pedidoId,
        UUID clienteId,
        int total
) {}

