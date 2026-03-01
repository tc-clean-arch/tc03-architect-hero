package contracts.events;

import java.time.Instant;

public record PagamentoDisponivelEvent(
        Instant ocorridoEm
) {
    public static PagamentoDisponivelEvent now() {
        return new PagamentoDisponivelEvent(Instant.now());
    }
}

