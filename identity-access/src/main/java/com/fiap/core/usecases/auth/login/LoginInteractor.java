package com.fiap.core.usecases.auth.login;


import com.fiap.core.dataaccess.UsuarioDataAccess;
import com.fiap.core.entities.Usuario;
import com.fiap.core.exception.auth.CredenciaisInvalidasException;
import com.fiap.core.usecases.auth.PasswordEncoder;
import com.fiap.core.usecases.auth.TokenProvider;

public class LoginInteractor implements LoginInputBoundary {

    private final UsuarioDataAccess usuarioDataAccess;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final LoginOutputBoundary presenter;

    public LoginInteractor(UsuarioDataAccess usuarioDataAccess,
                           PasswordEncoder passwordEncoder,
                           TokenProvider tokenProvider,
                           LoginOutputBoundary presenter) {
        this.usuarioDataAccess = usuarioDataAccess;
        this.passwordEncoder = passwordEncoder;
        this.tokenProvider = tokenProvider;
        this.presenter = presenter;
    }

    @Override
    public void execute(LoginInputData input) {

        Usuario usuario = usuarioDataAccess.obterPorEmail(input.email())
                .orElseThrow(CredenciaisInvalidasException::new);

        boolean senhaOk = passwordEncoder.matches(
                input.senha(),
                usuario.getSenha()
        );

        if (!senhaOk) {
            throw new CredenciaisInvalidasException();
        }

        String papel = usuario.getPapel().name();
        String token = tokenProvider.generateToken(
                usuario.getId(),
                usuario.getEmail(),
                papel
        );

        LoginOutputData output =
                new LoginOutputData(
                        usuario.getId(),
                        usuario.getEmail(),
                        papel,
                        token
                );

        presenter.present(output);
    }
}
