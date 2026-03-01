package com.fiap.core.usecases.usuario.registrar;

import com.fiap.core.usecases.usuario.registrar.dto.RegistrarUsuarioOutputData;

// <I> Output Boundary
public interface RegistrarUsuarioOutputBoundary {
    void present(RegistrarUsuarioOutputData outputData);
    void presentError(String message);
}
