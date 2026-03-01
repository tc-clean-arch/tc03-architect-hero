package com.fiap.core.usecases.usuario.listar.dto;

import java.util.List;
import java.util.UUID;

public record ListarUsuariosOutputData(
        List<UserItem> usuarios,
        int page,
        int size,
        long totalElements
) {
    public record UserItem(
            UUID id,
            String email,
            String papel
    ) {}

    public int totalPages() {
        return (int) Math.ceil((double) totalElements / size);
    }
}

