package com.fiap.core.usecases.pedidos.criar.dto;

import java.util.UUID;

public record CriarPedidoOutputData(
        UUID pedidoId,
        int valorTotal,
        String status
) { }