package com.fiap.core.usecases.usuario.alterarsenha;

import com.fiap.core.dataaccess.UsuarioDataAccess;
import com.fiap.core.entities.Usuario;
import com.fiap.core.exception.usuario.SenhaInvalidaException;
import com.fiap.core.usecases.auth.PasswordEncoder;

public class AlterarSenhaInteractor implements AlterarSenhaInputBoundary {

    private final UsuarioDataAccess usuarioDataAccess;
    private final PasswordEncoder passwordEncoder;
    private final AlterarSenhaOutputBoundary presenter;

    public AlterarSenhaInteractor(
            UsuarioDataAccess usuarioDataAccess,
            PasswordEncoder passwordEncoder,
            AlterarSenhaOutputBoundary presenter
    ) {
        this.usuarioDataAccess = usuarioDataAccess;
        this.passwordEncoder = passwordEncoder;
        this.presenter = presenter;
    }

    @Override
    public void execute(AlterarSenhaInputData input) {

        Usuario usuario = usuarioDataAccess.obterPorId(input.usuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (!passwordEncoder.matches(input.senhaAtual(), usuario.getSenha())) {
            throw new SenhaInvalidaException();
        }

        String novaSenhaHash = passwordEncoder.hash(input.novaSenha());

        Usuario usuarioAtualizado = usuario.comSenhaAlterada(novaSenhaHash);

        usuarioDataAccess.salvar(usuarioAtualizado);

        presenter.present(new AlterarSenhaOutputData(usuarioAtualizado.getId()));
    }

}
