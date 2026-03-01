package com.fiap.infrastructure.web.rest;

import com.fiap.core.entities.ItemPedido;
import com.fiap.infrastructure.web.dto.CriarPedidoRequest;
import com.fiap.interfaceadapters.controller.PedidoController;
import com.fiap.interfaceadapters.presenter.BuscarPedidoPorIdPresenter;
import com.fiap.interfaceadapters.presenter.CriarPedidoPresenter;
import com.fiap.interfaceadapters.presenter.ListarPedidosPresenter;
import io.quarkus.security.Authenticated;
import io.quarkus.security.identity.SecurityIdentity;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.UUID;

@Path("/api/v1/orders")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Authenticated
@ApplicationScoped
public class PedidoResource {

    private final PedidoController controller;
    private final CriarPedidoPresenter presenter;
    private final BuscarPedidoPorIdPresenter buscarPresenter;
    private final ListarPedidosPresenter listarPedidosPresenter;

    public PedidoResource(PedidoController controller, CriarPedidoPresenter presenter, BuscarPedidoPorIdPresenter buscarPresenter, ListarPedidosPresenter listarPedidosPresenter) {
        this.controller = controller;
        this.presenter = presenter;
        this.buscarPresenter = buscarPresenter;
        this.listarPedidosPresenter = listarPedidosPresenter;
    }

    @POST
    @RolesAllowed("CLIENTE")
    public Response criarPedido(
            @Context SecurityIdentity identity,
            CriarPedidoRequest request
    ) {
        UUID clienteId =
                UUID.fromString(identity.getPrincipal().getName());

        List<ItemPedido> itens = request.itens().stream()
                .map(item -> new ItemPedido(
                        item.produtoId(),
                        item.nome(),
                        item.quantidade(),
                        item.preco()
                ))
                .toList();

        controller.criar(
                clienteId,
                request.restauranteId(),
                itens
        );

        return Response.status(Response.Status.CREATED)
                .entity(presenter.getViewModel())
                .build();
    }

    @GET
    public Response listar(
            @Context SecurityIdentity identity,
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("size") @DefaultValue("20") int size
    ) {
        UUID clienteId =
                UUID.fromString(identity.getPrincipal().getName());

        controller.listarPorCliente(clienteId, page, size);

        return Response.ok(
                listarPedidosPresenter.getViewModel()
        ).build();
    }

    @GET
    @Path("/{id}")
    public Response buscar(@PathParam("id") UUID id) {

        controller.buscarPorId(id);

        return Response.ok(buscarPresenter.getViewModel()).build();
    }
}

