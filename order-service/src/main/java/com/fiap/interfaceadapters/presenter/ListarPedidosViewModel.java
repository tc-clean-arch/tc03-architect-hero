package com.fiap.interfaceadapters.presenter;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record ListarPedidosViewModel(
        List<PedidoItem> items,
        int page,
        int size,
        long totalElements,
        boolean success
) {

    public record PedidoItem(
            UUID pedidoId,
            UUID clienteId,
            Long restauranteId,
            int total,
            String status
    ) {}
}
