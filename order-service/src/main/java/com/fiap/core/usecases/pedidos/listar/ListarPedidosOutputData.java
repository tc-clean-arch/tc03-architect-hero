package com.fiap.core.usecases.pedidos.listar;

import java.util.List;
import java.util.UUID;

public record ListarPedidosOutputData(
        List<PedidoItem> items,
        int page,
        int size,
        long totalElements
) {

    public record PedidoItem(
            UUID pedidoId,
            UUID clienteId,
            Long restauranteId,
            int total,
            String status
    ) {}
}
