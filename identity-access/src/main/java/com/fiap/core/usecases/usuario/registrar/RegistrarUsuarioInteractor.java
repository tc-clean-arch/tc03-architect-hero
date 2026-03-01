package com.fiap.core.usecases.usuario.registrar;

import com.fiap.core.dataaccess.UsuarioDataAccess;
import com.fiap.core.entities.Papel;
import com.fiap.core.entities.Usuario;
import com.fiap.core.exception.usuario.AdminJaExisteException;
import com.fiap.core.exception.usuario.EmailJaCadastradoException;
import com.fiap.core.exception.usuario.PapelNaoEncontradoException;
import com.fiap.core.usecases.auth.PasswordEncoder;
import com.fiap.core.usecases.usuario.registrar.dto.RegistrarUsuarioInputData;
import com.fiap.core.usecases.usuario.registrar.dto.RegistrarUsuarioOutputData;

import java.util.Arrays;

import static com.fiap.core.entities.Usuario.novoUsuario;

// UseCaseInteractor
public class RegistrarUsuarioInteractor implements RegistrarUsuarioInputBoundary {
    private final UsuarioDataAccess usuarioRepository;
    private final RegistrarUsuarioOutputBoundary presenter;
    private final PasswordEncoder passwordEncryptor;

    public RegistrarUsuarioInteractor(UsuarioDataAccess usuarioRepository,
                                      RegistrarUsuarioOutputBoundary presenter, PasswordEncoder passwordEncryptor) {
        this.usuarioRepository = usuarioRepository;
        this.presenter = presenter;
        this.passwordEncryptor = passwordEncryptor;
    }

    @Override
    public void execute(RegistrarUsuarioInputData inputData) {

        if (usuarioRepository.existePorEmail(inputData.email())) {
            throw new EmailJaCadastradoException();
        }

        Papel papel = Arrays.stream(Papel.values())
                .filter(p -> p.name().equalsIgnoreCase(inputData.papel()))
                .findFirst()
                .orElseThrow(PapelNaoEncontradoException::new);

        if (papel == Papel.ADMIN) {
            boolean jaExisteAdmin = usuarioRepository.obterTodos().stream()
                    .anyMatch(u -> u.getPapel() == Papel.ADMIN);

            if (jaExisteAdmin) {
                throw new AdminJaExisteException();
            }
        }

        String passwordHash = passwordEncryptor.hash(inputData.senha());

        Usuario usuario = novoUsuario(
                inputData.email(),
                passwordHash,
                papel
        );

        Usuario saved = usuarioRepository.salvar(usuario);

        presenter.present(new RegistrarUsuarioOutputData(
                saved.getId(),
                saved.getEmail(),
                saved.getPapel().name()
        ));
    }
}
