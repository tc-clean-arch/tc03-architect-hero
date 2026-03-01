package com.fiap.interfaceadapters.presenter.auth;

import java.util.UUID;

public record LoginViewModel(
        boolean success,
        String message,
        String token,
        UUID userId,
        String email,
        String papel
) {}