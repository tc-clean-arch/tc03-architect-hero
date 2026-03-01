package com.fiap.core.usecases.usuario.buscarPorEmail;

import com.fiap.core.dataaccess.UsuarioDataAccess;
import com.fiap.core.entities.Usuario;
import com.fiap.core.usecases.usuario.buscarPorEmail.dto.BuscarUsuarioPorEmailOutputData;

import java.util.Optional;

public class BuscarUsuarioPorEmailInteractor {
    private final UsuarioDataAccess usuarioDataAccess;
    private final BuscarUsuarioPorEmailOutputBoundary presenter;

    public BuscarUsuarioPorEmailInteractor(UsuarioDataAccess usuarioDataAccess, BuscarUsuarioPorEmailOutputBoundary presenter) {
        this.usuarioDataAccess = usuarioDataAccess;
        this.presenter = presenter;
    }

    public void execute(String email) {

        Optional<Usuario> output = usuarioDataAccess.obterPorEmail(email);

        BuscarUsuarioPorEmailOutputData usuarioData = output
                .map(u -> new BuscarUsuarioPorEmailOutputData(
                        u.getId(),
                        u.getEmail(),
                        u.getPapel().name()
                ))
                .orElse(null);

        presenter.present(usuarioData);
    }

}
