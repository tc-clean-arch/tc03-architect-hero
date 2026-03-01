package com.fiap.core.usecases.usuario.listar;

import com.fiap.core.usecases.usuario.listar.dto.ListarUsuariosOutputData;

public interface ListarUsuariosOutputBoundary {
    void present(ListarUsuariosOutputData outputData);
}