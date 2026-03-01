package com.fiap.infrastructure.web.rest;

import com.fiap.infrastructure.db.repository.PedidoPanacheRepository;
import jakarta.annotation.security.PermitAll;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

import java.util.List;

@Path("/internal/pedidos")
@ApplicationScoped
@PermitAll
public class PedidoInternalResource {

    @Inject
    PedidoPanacheRepository pedidoRepository;

    @GET
    @Path("/pendentes")
    public List<PedidoPendenteResponse> listarPendentes() {

        return pedidoRepository.buscarPendentesPagamento()
                .stream()
                .map(p -> new PedidoPendenteResponse(
                        p.id,
                        p.clienteId,
                        p.total
                ))
                .toList();
    }
}

