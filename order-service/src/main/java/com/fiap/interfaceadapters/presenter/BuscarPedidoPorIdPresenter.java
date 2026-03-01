package com.fiap.interfaceadapters.presenter;


import com.fiap.core.usecases.pedidos.buscarporid.BuscarPedidoPorIdOutputBoundary;
import com.fiap.core.usecases.pedidos.buscarporid.BuscarPedidoPorIdOutputData;

public class BuscarPedidoPorIdPresenter
        implements BuscarPedidoPorIdOutputBoundary {

    private BuscarPedidoPorIdViewModel viewModel;

    @Override
    public void present(BuscarPedidoPorIdOutputData output) {
        viewModel = new BuscarPedidoPorIdViewModel(
                output.pedidoId(),
                output.clienteId(),
                output.restauranteId(),
                output.total(),
                output.status(),
                true
        );
    }

    @Override
    public void presentError(String message) {
        viewModel = new BuscarPedidoPorIdViewModel(
                null, null, null, 0, null, false
        );
    }

    public BuscarPedidoPorIdViewModel getViewModel() {
        return viewModel;
    }
}

