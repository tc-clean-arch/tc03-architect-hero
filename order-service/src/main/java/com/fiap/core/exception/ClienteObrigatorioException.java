package com.fiap.core.exception;


public class ClienteObrigatorioException extends DomainException {

    public ClienteObrigatorioException() {
        super("Cliente é obrigatório para criação do pedido.");
    }

    @Override
    public String errorType() {
        return "VALIDATION_ERROR";
    }
}
