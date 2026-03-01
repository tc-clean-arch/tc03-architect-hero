package com.fiap.infra.web.acl;

import com.fiap.infra.dto.PedidoPendente;
import com.fiap.infra.gateway.PedidoGateway;
import com.fiap.infra.web.acl.http.PedidoRestClient;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;

@ApplicationScoped
public class PedidoGatewayRestAdapter implements PedidoGateway {

    @Inject
    @RestClient
    PedidoRestClient client;

    @Override
    public List<PedidoPendente> buscarPendentes() {

        return client.listarPendentes()
                .stream()
                .map(r -> new PedidoPendente(
                        r.pedidoId(),
                        r.clienteId(),
                        r.total(),
                        0
                ))
                .toList();
    }
}
