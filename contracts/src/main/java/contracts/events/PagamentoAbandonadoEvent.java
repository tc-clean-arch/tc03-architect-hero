package contracts.events;

import java.time.Instant;
import java.util.UUID;

public record PagamentoAbandonadoEvent(
        UUID pedidoId,
        String motivo,
        Instant ocorridoEm
) {

    public static PagamentoAbandonadoEvent of(UUID pedidoId, String motivo) {
        return new PagamentoAbandonadoEvent(
                pedidoId,
                motivo,
                Instant.now()
        );
    }
}

