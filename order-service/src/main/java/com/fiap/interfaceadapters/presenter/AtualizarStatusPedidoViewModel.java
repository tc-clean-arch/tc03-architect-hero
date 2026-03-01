package com.fiap.interfaceadapters.presenter;

import java.util.UUID;

public record AtualizarStatusPedidoViewModel(
        UUID pedidoId,
        String status,
        boolean success
) {}
