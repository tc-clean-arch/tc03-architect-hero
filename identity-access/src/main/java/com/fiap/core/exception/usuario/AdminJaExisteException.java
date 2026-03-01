package com.fiap.core.exception.usuario;

import com.fiap.core.exception.DomainException;

public class AdminJaExisteException extends DomainException {
    public AdminJaExisteException() {
        super("Já existe um usuário ADMIN no sistema. Só pode haver um.");
    }

    @Override
    public String errorType() {
        return "BUSINESS_RULE_VIOLATION";
    }
}
