package com.fiap.interfaceadapters.gateway;

import java.util.UUID;

public interface PagamentoGateway {
    void processar(
            UUID pedidoId,
            UUID clienteId,
            int valor
    );
}
