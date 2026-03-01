package com.fiap.core.exception.usuario;

import com.fiap.core.exception.DomainException;

public class ErroAoRegistrarUsuarioException extends DomainException {
    public ErroAoRegistrarUsuarioException(Throwable cause) {
        super("Erro ao registrar usuário: " + cause.getMessage(), cause);
    }

    @Override
    public String errorType() {
        return "BUSINESS_RULE_VIOLATION";
    }
}
