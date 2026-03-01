package com.fiap.infra.gateway;

import com.fiap.infra.dto.PedidoPendente;

import java.util.List;

public interface PedidoGateway {

    List<PedidoPendente> buscarPendentes();
}

