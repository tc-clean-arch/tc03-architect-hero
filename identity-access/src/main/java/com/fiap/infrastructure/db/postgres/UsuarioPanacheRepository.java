package com.fiap.infrastructure.db.postgres;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.UUID;

@ApplicationScoped
public class UsuarioPanacheRepository
        implements PanacheRepositoryBase<UsuarioPanacheEntity, UUID> {
}
