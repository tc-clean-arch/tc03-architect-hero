package com.fiap.core.exception.usuario;

import com.fiap.core.exception.DomainException;

public class SenhaInvalidaException extends DomainException {
    public SenhaInvalidaException() {
        super("Senha atual inválida.");
    }

    @Override
    public String errorType() {
        return "BUSINESS_RULE_VIOLATION";
    }
}
