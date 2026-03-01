package com.fiap.core.usecases.usuario.registrar;

import com.fiap.core.usecases.usuario.registrar.dto.RegistrarUsuarioInputData;

// <I> Input Boundary
public interface RegistrarUsuarioInputBoundary {
    void execute(RegistrarUsuarioInputData inputData);
}
