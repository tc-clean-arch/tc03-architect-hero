package com.fiap.infra.web.acl.http;

import com.fiap.infra.web.acl.dto.PedidoPendenteResponse;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@Path("/internal/pedidos")
@RegisterRestClient(configKey = "order-service")
public interface PedidoRestClient {

    @GET
    @Path("/pendentes")
    List<PedidoPendenteResponse> listarPendentes();
}

