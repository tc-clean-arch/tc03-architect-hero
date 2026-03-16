package com.fiap.interfaceadapters.presenter;

import java.util.UUID;

public record BuscarPedidoPorIdViewModel(
        UUID pedidoId,
        UUID clienteId,
        Long restauranteId,
        int total,
        String status,
        boolean success
) {}
