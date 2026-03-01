package com.fiap.core.exception.usuario;

import com.fiap.core.exception.DomainException;

public class SenhaObrigatoriaException extends DomainException {
    public SenhaObrigatoriaException() {
        super("Senha é obrigatória.");
    }

    @Override
    public String errorType() {
        return "VALIDATION_ERROR";
    }
}