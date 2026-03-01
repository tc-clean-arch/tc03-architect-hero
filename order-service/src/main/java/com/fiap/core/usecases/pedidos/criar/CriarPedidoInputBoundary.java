package com.fiap.core.usecases.pedidos.criar;

import com.fiap.core.usecases.pedidos.criar.dto.CriarPedidoInputData;

public interface CriarPedidoInputBoundary {
    void execute(CriarPedidoInputData inputData);
}