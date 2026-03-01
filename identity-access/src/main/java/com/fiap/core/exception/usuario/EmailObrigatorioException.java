package com.fiap.core.exception.usuario;

import com.fiap.core.exception.DomainException;

public class EmailObrigatorioException extends DomainException {
    public EmailObrigatorioException() {
        super("E-mail é obrigatório.");
    }

    @Override
    public String errorType() {
        return "VALIDATION_ERROR";
    }
}
