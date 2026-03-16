package com.fiap.infrastructure.web.dto;

import java.util.UUID;

public record ItemPedidoRequest(
        UUID produtoId,
        String nome,
        int quantidade,
        int preco
) { }
