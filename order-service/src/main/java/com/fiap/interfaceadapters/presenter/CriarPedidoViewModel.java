package com.fiap.interfaceadapters.presenter;

import java.math.BigDecimal;
import java.util.UUID;

public record CriarPedidoViewModel(
        UUID pedidoId,
        int valorTotal,
        String status,
        String message,
        boolean success
) { }
