package com.fiap.interfaceadapters.presenter;

import com.fiap.core.usecases.pedidos.criar.CriarPedidoOutputBoundary;
import com.fiap.core.usecases.pedidos.criar.dto.CriarPedidoOutputData;

public class CriarPedidoPresenter implements CriarPedidoOutputBoundary {

    private CriarPedidoViewModel viewModel;

    @Override
    public void present(CriarPedidoOutputData output) {
        viewModel = new CriarPedidoViewModel(
                output.pedidoId(),
                output.valorTotal(),
                output.status(),
                "Pedido criado com sucesso!",
                true
        );
    }

    @Override
    public void presentError(String message) {
        return;
    }

    public CriarPedidoViewModel getViewModel() {
        return viewModel;
    }
}
