package com.fiap.core.usecases.pedidos.buscarporid;

public interface BuscarPedidoPorIdOutputBoundary {
    void present(BuscarPedidoPorIdOutputData output);
    void presentError(String message);
}
