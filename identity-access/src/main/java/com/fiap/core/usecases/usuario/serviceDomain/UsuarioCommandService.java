package com.fiap.core.usecases.usuario.serviceDomain;

import com.fiap.core.dataaccess.UsuarioDataAccess;
import com.fiap.core.entities.Papel;
import com.fiap.core.entities.Usuario;
import com.fiap.core.security.PasswordGenerator;
import com.fiap.core.usecases.auth.PasswordEncoder;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UsuarioCommandService {

    private final UsuarioDataAccess usuarioRepository;
    private final PasswordEncoder passwordEncryptor;

    public UsuarioCommandService(
            UsuarioDataAccess usuarioRepository,
            PasswordEncoder passwordEncryptor
    ) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncryptor = passwordEncryptor;
    }

    public String criarUsuarioGenerico(String email) {

        Papel papel = Papel.USER;

        String senhaGerada = PasswordGenerator.generate();
        String senhaHash = passwordEncryptor.hash(senhaGerada);

        Usuario usuario = Usuario.novoUsuario(
                email,
                senhaHash,
                papel
        );

        usuarioRepository.salvar(usuario);

        return senhaGerada;
    }
}