package com.fiap.infrastructure.web.dto;

import java.util.List;

public record CriarPedidoRequest(
        Long restauranteId,
        List<ItemPedidoRequest> itens
) { }
