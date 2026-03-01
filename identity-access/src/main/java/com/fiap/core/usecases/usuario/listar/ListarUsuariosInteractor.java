// src/main/java/com/example/clean_arch/usecases/listusers/ListUsersInteractor.java
package com.fiap.core.usecases.usuario.listar;

import com.fiap.core.dataaccess.UsuarioDataAccess;
import com.fiap.core.usecases.usuario.listar.dto.ListarUsuariosInputData;
import com.fiap.core.usecases.usuario.listar.dto.ListarUsuariosOutputData;

public class ListarUsuariosInteractor
        implements ListarUsuariosInputBoundary {

    private final UsuarioDataAccess usuarioDataAccess;
    private final ListarUsuariosOutputBoundary presenter;

    public ListarUsuariosInteractor(
            UsuarioDataAccess usuarioDataAccess,
            ListarUsuariosOutputBoundary presenter
    ) {
        this.usuarioDataAccess = usuarioDataAccess;
        this.presenter = presenter;
    }

    @Override
    public void execute(ListarUsuariosInputData input) {

        var result = usuarioDataAccess.obterTodosPaginado(
                input.page(),
                input.size()
        );

        var items = result.data().stream()
                .map(u -> new ListarUsuariosOutputData.UserItem(
                        u.getId(),
                        u.getEmail(),
                        u.getPapel().name()
                ))
                .toList();

        var output = new ListarUsuariosOutputData(
                items,
                input.page(),
                input.size(),
                result.totalElements()
        );

        presenter.present(output);
    }
}