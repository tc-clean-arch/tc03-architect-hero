package com.fiap.core.exception;

public class PedidoNaoEncontradoException extends DomainException {

    public PedidoNaoEncontradoException() {
        super("Pedido não encontrado.");
    }

    @Override
    public String errorType() {
        return "BUSINESS_RULE_VIOLATION";
    }
}
