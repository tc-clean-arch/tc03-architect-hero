package com.fiap.core.usecases.usuario.buscarPorEmail.dto;

import java.util.UUID;

public record BuscarUsuarioPorEmailOutputData(UUID id, String email, String papel) {

}
