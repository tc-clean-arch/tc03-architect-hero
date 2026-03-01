package com.fiap.core.dataaccess;

import com.fiap.core.entities.Pedido;
import com.fiap.core.entities.StatusPedido;
import com.fiap.core.usecases.pedidos.listar.dto.PagedResult;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PedidoDataAccess {
    Pedido salvar(Pedido pedido);

    Optional<Pedido> obterPorId(UUID id);

    void atualizar(Pedido pedido);

    PagedResult<Pedido> obterTodosPorCliente(UUID clienteId, int page, int size);

    void atualizarStatus(UUID pedidoId, StatusPedido status);

    List<Pedido> obterTodosPorCliente(UUID clienteId);
}
