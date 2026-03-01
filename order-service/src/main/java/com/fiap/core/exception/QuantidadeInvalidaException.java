package com.fiap.core.exception;

public class QuantidadeInvalidaException extends DomainException {

    public QuantidadeInvalidaException() {
        super("Quantidade do item deve ser maior que zero.");
    }

    @Override
    public String errorType() {
        return "VALIDATION_ERROR";
    }
}
