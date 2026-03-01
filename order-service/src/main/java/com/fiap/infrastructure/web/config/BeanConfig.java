package com.fiap.infrastructure.web.config;

import com.fiap.core.dataaccess.PedidoDataAccess;
import com.fiap.core.event.publisher.DomainEventPublisher;
import com.fiap.core.usecases.pedidos.atualizar.AtualizarStatusPedidoInputBoundary;
import com.fiap.core.usecases.pedidos.atualizar.AtualizarStatusPedidoInteractor;
import com.fiap.core.usecases.pedidos.atualizar.AtualizarStatusPedidoOutputBoundary;
import com.fiap.core.usecases.pedidos.criar.CriarPedidoInputBoundary;
import com.fiap.core.usecases.pedidos.criar.CriarPedidoInteractor;
import com.fiap.core.usecases.pedidos.criar.CriarPedidoOutputBoundary;
import com.fiap.core.usecases.pedidos.buscarporid.BuscarPedidoPorIdInputBoundary;
import com.fiap.core.usecases.pedidos.buscarporid.BuscarPedidoPorIdInteractor;
import com.fiap.core.usecases.pedidos.buscarporid.BuscarPedidoPorIdOutputBoundary;
import com.fiap.core.usecases.pedidos.listar.ListarPedidosInputBoundary;
import com.fiap.core.usecases.pedidos.listar.ListarPedidosInteractor;
import com.fiap.core.usecases.pedidos.listar.ListarPedidosOutputBoundary;
import com.fiap.interfaceadapters.controller.PedidoController;
import com.fiap.interfaceadapters.presenter.AtualizarStatusPedidoPresenter;
import com.fiap.interfaceadapters.presenter.BuscarPedidoPorIdPresenter;
import com.fiap.interfaceadapters.presenter.CriarPedidoPresenter;
import com.fiap.interfaceadapters.presenter.ListarPedidosPresenter;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Produces;

@ApplicationScoped
public class BeanConfig {

    // =========================
    // PRESENTERS
    // =========================

    @Produces
    @RequestScoped
    public CriarPedidoPresenter criarPedidoPresenter() {
        return new CriarPedidoPresenter();
    }

    @Produces
    @RequestScoped
    public AtualizarStatusPedidoPresenter atualizarStatusPedidoPresenter() {
        return new AtualizarStatusPedidoPresenter();
    }

    @Produces
    @RequestScoped
    public ListarPedidosPresenter listarPedidosPresenter() {
        return new ListarPedidosPresenter();
    }

    @Produces
    @RequestScoped
    public BuscarPedidoPorIdPresenter buscarPedidoPorIdPresenter() {
        return new BuscarPedidoPorIdPresenter();
    }

    // =========================
    // USE CASES — PEDIDO
    // =========================

    @Produces
    public CriarPedidoInputBoundary criarPedidoInteractor(
            PedidoDataAccess pedidoRepository,
            CriarPedidoOutputBoundary presenter,
            DomainEventPublisher eventPublisher
    ) {
        return new CriarPedidoInteractor(
                pedidoRepository,
                presenter,
                eventPublisher
        );
    }

    @Produces
    public AtualizarStatusPedidoInputBoundary atualizarStatusPedidoInteractor(
            PedidoDataAccess pedidoRepository,
            AtualizarStatusPedidoOutputBoundary presenter

    ) {
        return new AtualizarStatusPedidoInteractor(
                pedidoRepository,
                presenter
        );
    }

    @Produces
    public ListarPedidosInputBoundary listarPedidosPorClienteInteractor(
            PedidoDataAccess pedidoRepository,
            ListarPedidosOutputBoundary presenter
    ) {
        return new ListarPedidosInteractor(
                pedidoRepository,
                presenter
        );
    }

    @Produces
    public BuscarPedidoPorIdInputBoundary buscarPedidoPorIdInteractor(
            PedidoDataAccess pedidoRepository,
            BuscarPedidoPorIdOutputBoundary presenter
    ) {
        return new BuscarPedidoPorIdInteractor(
                pedidoRepository,
                presenter
        );
    }

    // =========================
    // CONTROLLERS (Interface Adapters)
    // =========================

    @Produces
    public PedidoController pedidoController(
            CriarPedidoInputBoundary criarPedidoBoundary,
            BuscarPedidoPorIdInputBoundary buscarPedidoBoundary,
            ListarPedidosInputBoundary listarPedidosBoundary
    ) {
        return new PedidoController(
                criarPedidoBoundary,
                buscarPedidoBoundary,
                listarPedidosBoundary
        );
    }

}