package com.fiap.interfaceadapters.presenter.user;


import java.util.UUID;

public record AlterarSenhaViewModel(
        UUID usuarioId,
        String message
) {}
