package com.fiap.core.exception.auth;

import com.fiap.core.exception.DomainException;

public class ErroAoAutenticarUsuarioException extends DomainException {

    public ErroAoAutenticarUsuarioException(Throwable cause) {
        super("Erro ao autenticar usuário", cause);
    }

    @Override
    public String errorType() {
        return "UNAUTHORIZED";
    }
}
