package com.fiap.core.exception.usuario;

import com.fiap.core.exception.DomainException;

public class PapelNaoEncontradoException extends DomainException {

    public PapelNaoEncontradoException() {
        super("Papel do usuário é obrigatório.");
    }

    @Override
    public String errorType() {
        return "VALIDATION_ERROR";
    }
}

