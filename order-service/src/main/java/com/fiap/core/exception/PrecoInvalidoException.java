package com.fiap.core.exception;

public class PrecoInvalidoException extends DomainException {

    public PrecoInvalidoException() {
        super("Preço do item deve ser maior que zero.");
    }

    @Override
    public String errorType() {
        return "VALIDATION_ERROR";
    }
}
