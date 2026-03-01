package com.fiap.core.usecases.pedidos.criar;

import com.fiap.core.dataaccess.PedidoDataAccess;
import com.fiap.core.entities.Pedido;
import com.fiap.core.event.PedidoCriadoEvent;
import com.fiap.core.event.publisher.DomainEventPublisher;
import com.fiap.core.usecases.pedidos.criar.dto.CriarPedidoInputData;
import com.fiap.core.usecases.pedidos.criar.dto.CriarPedidoOutputData;

import org.jboss.logging.Logger;

public class CriarPedidoInteractor implements CriarPedidoInputBoundary {

    private static final Logger LOG =
            Logger.getLogger(CriarPedidoInteractor.class);

    private final PedidoDataAccess pedidoRepository;
    private final CriarPedidoOutputBoundary presenter;
    private final DomainEventPublisher eventPublisher;

    public CriarPedidoInteractor(
            PedidoDataAccess pedidoRepository,
            CriarPedidoOutputBoundary presenter,
            DomainEventPublisher eventPublisher
    ) {
        this.pedidoRepository = pedidoRepository;
        this.presenter = presenter;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public void execute(CriarPedidoInputData input) {

        LOG.infof(
                "📝 Criando pedido | clienteId=%s | restauranteId=%s",
                input.clienteId(),
                input.restauranteId()
        );

        Pedido pedido = Pedido.novo(
                input.clienteId(),
                input.restauranteId(),
                input.itens()
        );

        Pedido salvo = pedidoRepository.salvar(pedido);

        LOG.infof(
                "📦 Pedido salvo | pedidoId=%s | status=%s",
                salvo.getId(),
                salvo.getStatus()
        );

        eventPublisher.publish(
                PedidoCriadoEvent.of(
                        salvo.getId(),
                        salvo.getClienteId(),
                        salvo.getTotal(),
                        salvo.getStatus()
                )
        );

        LOG.infof(
                "📢 Evento PedidoCriadoEvent publicado | pedidoId=%s",
                salvo.getId()
        );

        presenter.present(new CriarPedidoOutputData(
                salvo.getId(),
                salvo.getTotal(),
                salvo.getStatus().name()
        ));
    }
}
