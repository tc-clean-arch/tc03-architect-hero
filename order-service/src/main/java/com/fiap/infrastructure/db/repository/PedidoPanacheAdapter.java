package com.fiap.infrastructure.db.repository;

import com.fiap.core.entities.Pedido;
import com.fiap.core.entities.StatusPedido;
import com.fiap.core.usecases.pedidos.listar.dto.PagedResult;
import com.fiap.infrastructure.db.jpa.PedidoPanacheEntity;
import com.fiap.interfaceadapters.gateway.PedidoGateway;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class PedidoPanacheAdapter implements PedidoGateway {

    @Inject
    PedidoPanacheRepository repository;

    /* =========================
       COMMAND
       ========================= */

    @Transactional
    @Override
    public Pedido salvar(Pedido pedido) {
        PedidoPanacheEntity entity = PedidoPanacheEntity.fromDomain(pedido);

        PedidoPanacheEntity managed =
                repository.getEntityManager().merge(entity);

        return managed.toDomain();
    }

    @Transactional
    @Override
    public void atualizar(Pedido pedido) {
        PedidoPanacheEntity entity = PedidoPanacheEntity.fromDomain(pedido);
        repository.getEntityManager().merge(entity);
    }

    /* =========================
       QUERY
       ========================= */

    @Override
    public Optional<Pedido> obterPorId(UUID id) {
        return repository.findByIdOptional(id)
                .map(PedidoPanacheEntity::toDomain);
    }

    @Override
    public List<Pedido> obterTodosPorCliente(UUID clienteId) {
        return repository.find("clienteId", clienteId)
                .list()
                .stream()
                .map(PedidoPanacheEntity::toDomain)
                .toList();
    }

    @Override
    public PagedResult<Pedido> obterTodosPorCliente(UUID clienteId, int page, int size) {

        if (page < 0) page = 0;
        if (size <= 0) size = 10;

        long totalElements =
                repository.find("clienteId", clienteId).count();

        List<Pedido> content =
                repository.find("clienteId", clienteId)
                        .page(page, size)
                        .list()
                        .stream()
                        .map(PedidoPanacheEntity::toDomain)
                        .toList();

        return new PagedResult<>(content, totalElements);
    }

    @Transactional
    @Override
    public void atualizarStatus(UUID pedidoId, StatusPedido status) {
        PedidoPanacheEntity entity = repository.findById(pedidoId);

        if (entity == null) {
            throw new IllegalStateException("Pedido não encontrado: " + pedidoId);
        }

        entity.status = status;
    }

}

