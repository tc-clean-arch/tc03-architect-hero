package com.fiap.core.usecases.usuario.buscarPorEmail;

import com.fiap.core.usecases.usuario.buscarPorEmail.dto.BuscarUsuarioPorEmailOutputData;

public interface BuscarUsuarioPorEmailOutputBoundary {
    void present(BuscarUsuarioPorEmailOutputData usuarioData);
}
