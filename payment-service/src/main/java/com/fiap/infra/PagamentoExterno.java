package com.fiap.infra;

import com.fiap.interfaceadapters.gateway.PagamentoGateway;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.UUID;

@ApplicationScoped
public class PagamentoExterno implements PagamentoGateway {

    @Inject
    @RestClient
    PagamentoExternoClient client;

    @Override
    public void processar(
            UUID pedidoId,
            UUID clienteId,
            int valor
    ) {

        var response = client.pagar(
                new RequisicaoPagamentoDTO(
                        pedidoId.toString(),
                        clienteId.toString(),
                        valor
                )
        );

        if (response.getStatus() != 200 && response.getStatus() != 201) {
            throw new RuntimeException(
                    "Falha no serviço externo de pagamento | status=" + response.getStatus()
            );
        }
    }
}
