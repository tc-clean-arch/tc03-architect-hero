package com.fiap.core.usecases.pedidos.atualizar;

import java.util.UUID;

public record AtualizarStatusPedidoInputData(
        UUID pedidoId
) {}