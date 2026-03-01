package com.fiap.core.usecases.auth.login;

import java.util.UUID;

public record LoginOutputData(
        UUID userId,
        String email,
        String papel,
        String token
) {}