package com.fiap.infrastructure.db.repository;

import com.fiap.core.entities.StatusPedido;
import com.fiap.infrastructure.db.jpa.PedidoPanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class PedidoPanacheRepository
        implements PanacheRepositoryBase<PedidoPanacheEntity, UUID> {

    public List<PedidoPanacheEntity> buscarPendentesPagamento() {
        return find("status", StatusPedido.PENDENTE_PAGAMENTO).list();
    }
}

