package com.fiap.core.usecases.pedidos.buscarporid;

import com.fiap.core.dataaccess.PedidoDataAccess;
import com.fiap.core.entities.Pedido;

public class BuscarPedidoPorIdInteractor
        implements BuscarPedidoPorIdInputBoundary {

    private final PedidoDataAccess pedidoDataAccess;
    private final BuscarPedidoPorIdOutputBoundary presenter;

    public BuscarPedidoPorIdInteractor(
            PedidoDataAccess pedidoDataAccess,
            BuscarPedidoPorIdOutputBoundary presenter
    ) {
        this.pedidoDataAccess = pedidoDataAccess;
        this.presenter = presenter;
    }

    @Override
    public void execute(BuscarPedidoPorIdInputData input) {

        Pedido pedido = pedidoDataAccess.obterPorId(input.pedidoId())
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        var output = new BuscarPedidoPorIdOutputData(
                pedido.getId(),
                pedido.getClienteId(),
                pedido.getRestauranteId(),
                pedido.getTotal(),
                pedido.getStatus().name()
        );

        presenter.present(output);
    }
}
