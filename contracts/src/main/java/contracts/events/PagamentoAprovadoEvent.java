package contracts.events;

import java.math.BigInteger;
import java.time.Instant;
import java.util.UUID;

public record PagamentoAprovadoEvent(
        UUID pedidoId,
        UUID clienteId,
        int valor,
        Boolean x,
        BigInteger a,
        Instant ocorridoEm
) implements IntegrationEvent {

    @Override
    public UUID aggregateId() {
        return pedidoId;
    }

    public static PagamentoAprovadoEvent of(
            UUID pedidoId,
            UUID clienteId,
            int valor
    ) {
        return new PagamentoAprovadoEvent(
                pedidoId,
                clienteId,
                valor,
                Instant.now()
        );
    }
}

