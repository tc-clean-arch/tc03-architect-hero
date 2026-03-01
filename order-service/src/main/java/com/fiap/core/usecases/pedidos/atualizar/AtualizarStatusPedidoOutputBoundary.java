package com.fiap.core.usecases.pedidos.atualizar;

public interface AtualizarStatusPedidoOutputBoundary {

    void present(AtualizarStatusPedidoOutputData output);

    void presentError(String message);
}
