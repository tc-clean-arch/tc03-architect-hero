package com.fiap.core.usecases.pedidos.listar;

import java.util.UUID;

public record ListarPedidosInputData(
        UUID clienteId,
        int page,
        int size
) {}
