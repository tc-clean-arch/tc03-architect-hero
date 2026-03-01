package com.fiap.core.usecases;

import java.util.UUID;

public record ProcessarPagamentoInputData(
        UUID pedidoId,
        UUID clienteId,
        int valor,
        int tentativas
) {}


