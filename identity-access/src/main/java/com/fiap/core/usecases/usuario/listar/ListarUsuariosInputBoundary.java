package com.fiap.core.usecases.usuario.listar;

import com.fiap.core.usecases.usuario.listar.dto.ListarUsuariosInputData;

public interface ListarUsuariosInputBoundary {
    void execute(ListarUsuariosInputData inputData);

}