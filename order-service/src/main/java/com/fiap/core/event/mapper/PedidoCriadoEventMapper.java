package com.fiap.core.event.mapper;

import com.fiap.core.event.PedidoCriadoEvent;

public class PedidoCriadoEventMapper {

    public static contracts.events.PedidoCriadoEvent toContract(
            PedidoCriadoEvent event
    ) {
        return contracts.events.PedidoCriadoEvent.of(
                event.pedidoId(),
                event.clienteId(),
                event.valor()
        );
    }
}


