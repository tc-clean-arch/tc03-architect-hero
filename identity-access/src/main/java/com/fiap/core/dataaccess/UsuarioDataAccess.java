package com.fiap.core.dataaccess;


import com.fiap.core.entities.Usuario;
import com.fiap.core.usecases.usuario.listar.dto.PagedResult;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

// DataAccessInterface
public interface UsuarioDataAccess {
    boolean existePorEmail(String email);
    Usuario salvar(Usuario usuario);
    PagedResult<Usuario> obterTodosPaginado(int page, int size);
    List<Usuario> obterTodos();
    Optional<Usuario> obterPorEmail(String email);
    Optional<Usuario> obterPorId(UUID id);
}