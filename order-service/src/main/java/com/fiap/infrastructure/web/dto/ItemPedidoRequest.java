package com.fiap.infrastructure.web.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record ItemPedidoRequest(
        UUID produtoId,
        String nome,
        int quantidade,
        int preco
) { }
