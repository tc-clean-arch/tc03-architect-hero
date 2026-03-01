package com.fiap.interfaceadapters.controller;


import com.fiap.core.entities.ItemPedido;
import com.fiap.core.usecases.pedidos.criar.CriarPedidoInputBoundary;
import com.fiap.core.usecases.pedidos.criar.dto.CriarPedidoInputData;
import com.fiap.core.usecases.pedidos.buscarporid.BuscarPedidoPorIdInputBoundary;
import com.fiap.core.usecases.pedidos.buscarporid.BuscarPedidoPorIdInputData;
import com.fiap.core.usecases.pedidos.listar.ListarPedidosInputBoundary;
import com.fiap.core.usecases.pedidos.listar.ListarPedidosInputData;

import java.util.List;
import java.util.UUID;

public class PedidoController {

    private final CriarPedidoInputBoundary criarPedidoBoundary;
    private final BuscarPedidoPorIdInputBoundary buscarPedidoBoundary;
    private final ListarPedidosInputBoundary listarPedidosBoundary;

    public PedidoController(CriarPedidoInputBoundary criarPedidoBoundary, BuscarPedidoPorIdInputBoundary buscarPedidoBoundary, ListarPedidosInputBoundary listarPedidosBoundary) {
        this.criarPedidoBoundary = criarPedidoBoundary;
        this.buscarPedidoBoundary = buscarPedidoBoundary;
        this.listarPedidosBoundary = listarPedidosBoundary;
    }

    public void criar(
            UUID clienteId,
            Long restauranteId,
            List<ItemPedido> itens
    ) {
        CriarPedidoInputData inputData = new CriarPedidoInputData(
                clienteId,
                restauranteId,
                itens
        );

        criarPedidoBoundary.execute(inputData);
    }

    public void buscarPorId(UUID pedidoId) {
        buscarPedidoBoundary.execute(
                new BuscarPedidoPorIdInputData(pedidoId)
        );
    }

    public void listarPorCliente(UUID clienteId, int page, int size) {
        listarPedidosBoundary.execute(
                new ListarPedidosInputData(
                        clienteId,
                        page,
                        size
                )
        );
    }
}
