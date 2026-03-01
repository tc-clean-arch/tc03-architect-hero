package com.fiap.infrastructure.event.observer;

import com.fiap.core.event.PedidoCriadoEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;

@ApplicationScoped
public class PedidoCriadoObserver {

    public void onPedidoCriado(@Observes PedidoCriadoEvent event) {
        System.out.println("👂 Observando pedido criado: " + event.pedidoId());
    }
}
