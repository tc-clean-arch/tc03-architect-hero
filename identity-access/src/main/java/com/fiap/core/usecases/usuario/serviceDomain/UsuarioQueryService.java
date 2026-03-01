package com.fiap.core.usecases.usuario.serviceDomain;

import com.fiap.core.dataaccess.UsuarioDataAccess;
import com.fiap.core.entities.Papel;
import com.fiap.core.entities.Usuario;

import java.util.Optional;
import java.util.UUID;

public class UsuarioQueryService {

    private final UsuarioDataAccess usuarioRepository;

    public UsuarioQueryService(UsuarioDataAccess usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public boolean ehUsuarioDonoRestaurante(UUID id) {
        var usuario = usuarioRepository.obterPorId(id);
        if(usuario.isPresent() && usuario.get().getPapel() == Papel.DONO_RESTAURANTE) {
            return true;
        }
        return false;
    }

    public boolean existeUsuarioPorId(UUID id) {
        return usuarioRepository.obterPorId(id).isPresent();
    }

    public boolean existeUsuarioPorEmail(String email) {
        return usuarioRepository.obterPorEmail(email).isPresent();
    }

    public Optional<UUID> obterIdPorEmail(String email) {
        return usuarioRepository
                .obterPorEmail(email)
                .map(Usuario::getId);
    }

}
