package com.fiap.core.event.mapper;

import com.fiap.core.event.PedidoCriadoEvent;

public class PedidoCriadoEventMapper {

    public static PedidoCriadoEvent toContract(
            PedidoCriadoEvent event
    ) {
        return PedidoCriadoEvent.of(
                event.pedidoId(),
                event.clienteId(),
                event.valor(),
                event.status()
        );
    }
}


