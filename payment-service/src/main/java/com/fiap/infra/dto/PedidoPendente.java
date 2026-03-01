package com.fiap.infra.dto;

import java.util.UUID;

public record PedidoPendente(
        UUID pedidoId,
        UUID clienteId,
        int valor,
        int tentativas
) {}

