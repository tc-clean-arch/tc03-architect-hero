package com.fiap.core.entities;

import com.fiap.core.exception.usuario.EmailObrigatorioException;
import com.fiap.core.exception.usuario.TipoUsuarioObrigatorioException;
import com.fiap.core.exception.usuario.SenhaObrigatoriaException;

import java.util.UUID;

public class Usuario {
    private final UUID id;
    private final String email;
    private final String senha;
    private final Papel papel;

    public Usuario(UUID id, String email, String senha, Papel papel) {
        validaInvariantes(email,senha, papel);

        this.id = id;
        this.email = email;
        this.senha = senha;
        this.papel = papel;
    }

    public static Usuario novoUsuario(String email, String senhaHash, Papel papel) {
        return new Usuario(null, email, senhaHash, papel);
    }

    public Usuario comSenhaAlterada(String novaSenhaHash) {
        return new Usuario(
                this.id,
                this.email,
                novaSenhaHash,
                this.papel
        );
    }

    private static void validaInvariantes(String email,String senha, Papel papel) {
        if (email == null || email.isBlank()) {
            throw new EmailObrigatorioException();
        }
        if (senha == null || senha.isBlank()) {
            throw new SenhaObrigatoriaException();
        }
        if (papel == null) {
            throw new TipoUsuarioObrigatorioException();
        }
    }

    public UUID getId() { return id; }
    public String getEmail() { return email; }
    public Papel getPapel() { return papel; }
    public String getSenha() { return senha; }
}
