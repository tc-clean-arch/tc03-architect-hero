package com.fiap.infrastructure.db.postgres;

import com.fiap.core.entities.Usuario;
import com.fiap.core.usecases.usuario.listar.dto.PagedResult;
import com.fiap.interfaceadapters.gateway.UsuarioGateway;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class UsuarioPanacheAdapter implements UsuarioGateway {

    @Inject
    UsuarioPanacheRepository repository;

    @Override
    public boolean existePorEmail(String email) {
        return repository.find("email", email)
                .firstResultOptional()
                .isPresent();
    }

    @Transactional
    @Override
    public Usuario salvar(Usuario usuario) {
        UsuarioPanacheEntity entity = UsuarioPanacheEntity.fromDomain(usuario);
        repository.persist(entity);

        // ID agora vem do banco
        return entity.toDomain();
    }

    @Override
    public Optional<Usuario> obterPorEmail(String email) {
        return repository.find("email", email)
                .firstResultOptional()
                .map(UsuarioPanacheEntity::toDomain);
    }

    @Override
    public Optional<Usuario> obterPorId(UUID id) {
        return repository.findByIdOptional(id)
                .map(UsuarioPanacheEntity::toDomain);
    }

    @Override
    public List<Usuario> obterTodos() {
        return repository.findAll().list().stream()
                .map(UsuarioPanacheEntity::toDomain)
                .toList();
    }

    @Override
    public PagedResult<Usuario> obterTodosPaginado(int page, int size) {

        if (page < 0) page = 0;
        if (size <= 0) size = 10;

        long totalElements = repository.count();

        List<Usuario> content = repository.findAll()
                .page(page, size)
                .list()
                .stream()
                .map(UsuarioPanacheEntity::toDomain)
                .toList();

        return new PagedResult<>(content, totalElements);
    }
}

