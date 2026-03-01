package com.fiap.infra;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(configKey = "pagamento-externo")
@Path("/requisicao")
public interface PagamentoExternoClient {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    Response pagar(RequisicaoPagamentoDTO dto);
}

