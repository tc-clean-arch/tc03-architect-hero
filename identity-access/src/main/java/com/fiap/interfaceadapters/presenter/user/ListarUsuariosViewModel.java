package com.fiap.interfaceadapters.presenter.user;

import java.util.List;
import java.util.UUID;

public record ListarUsuariosViewModel(
        List<UsuarioItemViewModel> data,
        int page,
        int size,
        long totalElements,
        int totalPages,
        String message
) {

    public record UsuarioItemViewModel(
            UUID id,
            String email,
            String papel
    ) {}
}