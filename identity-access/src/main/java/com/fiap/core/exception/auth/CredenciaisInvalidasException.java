package com.fiap.core.exception.auth;

import com.fiap.core.exception.DomainException;

public class CredenciaisInvalidasException extends DomainException {

    public CredenciaisInvalidasException() {
        super("Credenciais inválidas");
    }

    @Override
    public String errorType() {
        return "UNAUTHORIZED";
    }
}
