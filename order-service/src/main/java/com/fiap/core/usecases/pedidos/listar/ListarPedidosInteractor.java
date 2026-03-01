package com.fiap.core.usecases.pedidos.listar;

import com.fiap.core.dataaccess.PedidoDataAccess;


public class ListarPedidosInteractor
        implements ListarPedidosInputBoundary {

    private final PedidoDataAccess pedidoDataAccess;
    private final ListarPedidosOutputBoundary presenter;

    public ListarPedidosInteractor(
            PedidoDataAccess pedidoDataAccess,
            ListarPedidosOutputBoundary presenter
    ) {
        this.pedidoDataAccess = pedidoDataAccess;
        this.presenter = presenter;
    }

    @Override
    public void execute(ListarPedidosInputData input) {

        var result = pedidoDataAccess.obterTodosPorCliente(
                input.clienteId(),
                input.page(),
                input.size()
        );

        var items = result.data().stream()
                .map(p -> new ListarPedidosOutputData.PedidoItem(
                        p.getId(),
                        p.getClienteId(),
                        p.getRestauranteId(),
                        p.getTotal(),
                        p.getStatus().name()
                ))
                .toList();

        var output = new ListarPedidosOutputData(
                items,
                input.page(),
                input.size(),
                result.totalElements()
        );

        presenter.present(output);
    }
}

