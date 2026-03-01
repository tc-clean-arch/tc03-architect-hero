package com.fiap.interfaceadapters.controller;

import com.fiap.core.usecases.usuario.alterarsenha.AlterarSenhaInputBoundary;
import com.fiap.core.usecases.usuario.alterarsenha.AlterarSenhaInputData;
import com.fiap.core.usecases.usuario.buscarPorEmail.BuscarUsuarioPorEmailInteractor;
import com.fiap.core.usecases.usuario.buscarPorId.BuscarUsuarioPorIdInteractor;
import com.fiap.core.usecases.usuario.listar.ListarUsuariosInputBoundary;
import com.fiap.core.usecases.usuario.listar.ListarUsuariosInteractor;
import com.fiap.core.usecases.usuario.listar.dto.ListarUsuariosInputData;
import com.fiap.core.usecases.usuario.registrar.RegistrarUsuarioInputBoundary;
import com.fiap.core.usecases.usuario.registrar.dto.RegistrarUsuarioInputData;

import java.util.UUID;

public class UsuarioController {
    private final RegistrarUsuarioInputBoundary registrarUsuarioInteractor;
    private final ListarUsuariosInputBoundary listarUsuariosInteractor;
    private final BuscarUsuarioPorEmailInteractor buscarUsuarioPorEmailInteractor;
    private final BuscarUsuarioPorIdInteractor buscarUsuarioPorIdInteractor;
    private final AlterarSenhaInputBoundary alterarSenha;

    public UsuarioController(RegistrarUsuarioInputBoundary registrarUsuarioInteractor,
                             ListarUsuariosInteractor listarUsuariosInteractor,
                             BuscarUsuarioPorEmailInteractor buscarUsuarioPorEmailInteractor,
                             BuscarUsuarioPorIdInteractor buscarUsuarioPorIdInteractor, AlterarSenhaInputBoundary alterarSenha
    ) {
        this.registrarUsuarioInteractor = registrarUsuarioInteractor;
        this.listarUsuariosInteractor = listarUsuariosInteractor;
        this.buscarUsuarioPorEmailInteractor = buscarUsuarioPorEmailInteractor;
        this.buscarUsuarioPorIdInteractor = buscarUsuarioPorIdInteractor;
        this.alterarSenha = alterarSenha;
    }

    public void registrar(String email, String senha, String papel) {
        RegistrarUsuarioInputData input = new RegistrarUsuarioInputData(email, senha, papel);
        registrarUsuarioInteractor.execute(input);
    }

    public void listarTodos(int page, int size) {
        var input = new ListarUsuariosInputData(page, size);
        listarUsuariosInteractor.execute(input);
    }

    public void buscarUsuarioPorEmail(String email) {
        buscarUsuarioPorEmailInteractor.execute(email);
    }

    public void buscarPorId(UUID id) {
        buscarUsuarioPorIdInteractor.execute(id);
    }

    public void alterarSenha(UUID usuarioId, String senhaAtual, String novaSenha) {
        alterarSenha.execute(
                new AlterarSenhaInputData(usuarioId, senhaAtual, novaSenha)
        );
    }
}
