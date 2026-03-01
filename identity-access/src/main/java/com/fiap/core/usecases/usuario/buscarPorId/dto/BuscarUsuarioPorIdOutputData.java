package com.fiap.core.usecases.usuario.buscarPorId.dto;

import java.util.UUID;

public record BuscarUsuarioPorIdOutputData (UUID id, String email, String papel) {
    
}
