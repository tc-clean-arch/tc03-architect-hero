package com.fiap.core.usecases.pedidos.criar;

import com.fiap.core.usecases.pedidos.criar.dto.CriarPedidoOutputData;

public interface CriarPedidoOutputBoundary {

    void present(CriarPedidoOutputData outputData);

    void presentError(String message);
}
