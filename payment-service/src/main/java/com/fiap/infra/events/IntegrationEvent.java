package com.fiap.infra.events;

import java.time.Instant;
import java.util.UUID;

public interface IntegrationEvent {

    UUID aggregateId();

    Instant ocorridoEm();

    default String eventVersion() {
        return "1";
    }
}


