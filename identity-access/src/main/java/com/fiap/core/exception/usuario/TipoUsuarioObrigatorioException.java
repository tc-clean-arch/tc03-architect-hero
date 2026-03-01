package com.fiap.core.exception.usuario;

import com.fiap.core.exception.DomainException;

public class TipoUsuarioObrigatorioException extends DomainException {
    public TipoUsuarioObrigatorioException() {
        super("Papel do usuário é obrigatório.");
    }

    @Override
    public String errorType() {
        return "VALIDATION_ERROR";
    }
}
