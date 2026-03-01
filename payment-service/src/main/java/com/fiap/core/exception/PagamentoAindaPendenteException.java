package com.fiap.core.exception;

public class PagamentoAindaPendenteException extends RuntimeException {
    private final int tentativas;

    public PagamentoAindaPendenteException(String message, int tentativas) {
        super(message);
        this.tentativas = tentativas;
    }

    public int getTentativas() {
        return tentativas;
    }
}
