package com.fiap.core.exception.usuario;

import com.fiap.core.exception.DomainException;

public class EmailJaCadastradoException extends DomainException {
    public EmailJaCadastradoException() {
        super("E-mail já cadastrado.");
    }

    @Override
    public String errorType() {
        return "BUSINESS_RULE_VIOLATION";
    }
}
