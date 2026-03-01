package com.fiap.core.exception;

public class ItensObrigatoriosException extends DomainException {

    public ItensObrigatoriosException() {
        super("O pedido deve conter ao menos um item.");
    }

    @Override
    public String errorType() {
        return "VALIDATION_ERROR";
    }
}