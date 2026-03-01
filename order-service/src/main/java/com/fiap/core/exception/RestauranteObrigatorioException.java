package com.fiap.core.exception;

public class RestauranteObrigatorioException extends DomainException {

    public RestauranteObrigatorioException() {
        super("Restaurante é obrigatório para criação do pedido.");
    }

    @Override
    public String errorType() {
        return "VALIDATION_ERROR";
    }
}