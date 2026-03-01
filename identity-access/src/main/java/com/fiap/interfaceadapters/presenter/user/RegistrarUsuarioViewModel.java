package com.fiap.interfaceadapters.presenter.user;

public record RegistrarUsuarioViewModel(
        java.util.UUID id,
        String email,
        String papel,
        String message,
        boolean success
) { }
