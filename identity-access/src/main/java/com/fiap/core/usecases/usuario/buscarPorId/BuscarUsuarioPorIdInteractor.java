package com.fiap.core.usecases.usuario.buscarPorId;

import com.fiap.core.dataaccess.UsuarioDataAccess;
import com.fiap.core.entities.Usuario;
import com.fiap.core.usecases.usuario.buscarPorId.dto.BuscarUsuarioPorIdOutputData;

import java.util.Optional;
import java.util.UUID;

public class BuscarUsuarioPorIdInteractor {

    private final UsuarioDataAccess usuarioDataAccess;
    private final BuscarUsuarioPorIdOutputBoundary presenter;

    public BuscarUsuarioPorIdInteractor(UsuarioDataAccess usuarioDataAccess,
                                        BuscarUsuarioPorIdOutputBoundary presenter) {
        this.usuarioDataAccess = usuarioDataAccess;
        this.presenter = presenter;
    }

    public void execute(UUID id) {
        Optional<Usuario> usuario = usuarioDataAccess.obterPorId(id);
        
        BuscarUsuarioPorIdOutputData usuarioData = usuario
                .map(u -> new BuscarUsuarioPorIdOutputData(
                        u.getId(),
                        u.getEmail(),
                        u.getPapel().name()
                ))
                .orElse(null);

        presenter.present(usuarioData);
    }


}
