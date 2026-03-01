package com.fiap.core.usecases.pedidos.atualizar;

import com.fiap.core.dataaccess.PedidoDataAccess;
import com.fiap.core.entities.Pedido;

import com.fiap.core.entities.StatusPedido;
import com.fiap.core.event.PedidoPagoEvent;
import com.fiap.core.event.publisher.DomainEventPublisher;
import com.fiap.core.exception.PedidoNaoEncontradoException;
import org.jboss.logging.Logger;

public class AtualizarStatusPedidoInteractor
        implements AtualizarStatusPedidoInputBoundary {

    private static final Logger LOG =
            Logger.getLogger(AtualizarStatusPedidoInteractor.class);

    private final PedidoDataAccess pedidoRepository;
    private final AtualizarStatusPedidoOutputBoundary presenter;

    public AtualizarStatusPedidoInteractor(
            PedidoDataAccess pedidoRepository,
            AtualizarStatusPedidoOutputBoundary presenter
    ) {
        this.pedidoRepository = pedidoRepository;
        this.presenter = presenter;
    }

    @Override
    public void marcarComoPago(AtualizarStatusPedidoInputData input) {

        Pedido pedido = pedidoRepository.obterPorId(input.pedidoId())
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        if (pedido.getStatus() == StatusPedido.PAGO) {
            LOG.infof("🔁 Pedido já está PAGO | pedidoId=%s", pedido.getId());
            return;
        }

        LOG.infof("🔄 Atualizando pedido para PAGO | pedidoId=%s", pedido.getId());

        pedidoRepository.atualizarStatus(
                pedido.getId(),
                StatusPedido.PAGO
        );

        presenter.present(
                new AtualizarStatusPedidoOutputData(
                        pedido.getId(),
                        StatusPedido.PAGO.name()
                )
        );
    }

    @Override
    public void marcarComoPendente(AtualizarStatusPedidoInputData input) {

        LOG.warnf(
                "⏳ Atualizando pedido para PENDENTE_PAGAMENTO | pedidoId=%s",
                input.pedidoId()
        );

        Pedido pedido = pedidoRepository.obterPorId(input.pedidoId())
                .orElseThrow(PedidoNaoEncontradoException::new);

        if (pedido.getStatus() == StatusPedido.PENDENTE_PAGAMENTO) {
            LOG.infof(
                    "🔁 Pedido já está PENDENTE_PAGAMENTO | pedidoId=%s",
                    pedido.getId()
            );
            return;
        }

        pedidoRepository.atualizarStatus(
                pedido.getId(),
                StatusPedido.PENDENTE_PAGAMENTO
        );

        presenter.present(
                new AtualizarStatusPedidoOutputData(
                        pedido.getId(),
                        StatusPedido.PENDENTE_PAGAMENTO.name()
                )
        );
    }

}

