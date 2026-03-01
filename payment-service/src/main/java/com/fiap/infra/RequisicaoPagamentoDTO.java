package com.fiap.infra;

import com.fasterxml.jackson.annotation.JsonProperty;

public record RequisicaoPagamentoDTO(

        @JsonProperty("pagamento_id")
        String pagamentoId,

        @JsonProperty("cliente_id")
        String clienteId,

        @JsonProperty("valor")
        int valor
) {}
