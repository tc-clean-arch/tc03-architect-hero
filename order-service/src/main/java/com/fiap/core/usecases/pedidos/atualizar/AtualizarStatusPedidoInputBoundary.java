package com.fiap.core.usecases.pedidos.atualizar;

public interface AtualizarStatusPedidoInputBoundary {

    void marcarComoPago(AtualizarStatusPedidoInputData input);

    void marcarComoPendente(AtualizarStatusPedidoInputData input);
}
