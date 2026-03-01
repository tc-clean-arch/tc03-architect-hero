package com.fiap.core.usecases.pedidos.criar.dto;

import com.fiap.core.entities.ItemPedido;

import java.util.List;
import java.util.UUID;

public record CriarPedidoInputData(
        UUID clienteId,
        Long restauranteId,
        List<ItemPedido> itens
) { }
