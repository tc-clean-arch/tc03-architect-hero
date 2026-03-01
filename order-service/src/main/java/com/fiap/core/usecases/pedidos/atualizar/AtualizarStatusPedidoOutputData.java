package com.fiap.core.usecases.pedidos.atualizar;

import java.util.UUID;

public record AtualizarStatusPedidoOutputData(
        UUID pedidoId,
        String status
) {}
