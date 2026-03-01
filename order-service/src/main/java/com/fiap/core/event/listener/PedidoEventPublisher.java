package com.fiap.core.event.listener;


import com.fiap.core.entities.Pedido;

// Event Publisher (Port)
public interface PedidoEventPublisher {

    void pedidoCriado(Pedido pedido);

    void pedidoPago(Pedido pedido);

    void pedidoPagamentoPendente(Pedido pedido);
}
