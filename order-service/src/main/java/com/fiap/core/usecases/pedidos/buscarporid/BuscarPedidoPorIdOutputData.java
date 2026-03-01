package com.fiap.core.usecases.pedidos.buscarporid;


import java.math.BigDecimal;
import java.util.UUID;

public record BuscarPedidoPorIdOutputData(
        UUID pedidoId,
        UUID clienteId,
        Long restauranteId,
        int total,
        String status
) {}
