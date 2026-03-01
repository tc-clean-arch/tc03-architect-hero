package com.fiap.interfaceadapters.presenter;

import com.fiap.core.usecases.pedidos.listar.ListarPedidosOutputBoundary;
import com.fiap.core.usecases.pedidos.listar.ListarPedidosOutputData;

import java.util.List;

public class ListarPedidosPresenter
        implements ListarPedidosOutputBoundary {

    private ListarPedidosViewModel viewModel;

    @Override
    public void present(ListarPedidosOutputData output) {

        var items = output.items().stream()
                .map(item -> new ListarPedidosViewModel.PedidoItem(
                        item.pedidoId(),
                        item.clienteId(),
                        item.restauranteId(),
                        item.total(),
                        item.status()
                ))
                .toList();

        viewModel = new ListarPedidosViewModel(
                items,
                output.page(),
                output.size(),
                output.totalElements(),
                true
        );
    }

    @Override
    public void presentError(String message) {
        viewModel = new ListarPedidosViewModel(
                List.of(),
                0,
                0,
                0,
                false
        );
    }

    public ListarPedidosViewModel getViewModel() {
        return viewModel;
    }
}