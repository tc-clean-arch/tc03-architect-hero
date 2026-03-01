package com.fiap.core.usecases.pedidos.listar;

public interface ListarPedidosOutputBoundary {
    void present(ListarPedidosOutputData output);

    void presentError(String message);
}