package com.fiap.interfaceadapters.presenter;

import com.fiap.core.usecases.pedidos.atualizar.AtualizarStatusPedidoOutputBoundary;
import com.fiap.core.usecases.pedidos.atualizar.AtualizarStatusPedidoOutputData;

public class AtualizarStatusPedidoPresenter
        implements AtualizarStatusPedidoOutputBoundary {

    private AtualizarStatusPedidoViewModel viewModel;

    @Override
    public void present(AtualizarStatusPedidoOutputData output) {
        viewModel = new AtualizarStatusPedidoViewModel(
                output.pedidoId(),
                output.status(),
                true
        );
    }

    @Override
    public void presentError(String message) {
        viewModel = new AtualizarStatusPedidoViewModel(
                null,
                null,
                false
        );
    }

    public AtualizarStatusPedidoViewModel getViewModel() {
        return viewModel;
    }
}

