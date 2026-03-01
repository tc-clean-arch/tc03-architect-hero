package com.fiap.infrastructure.web.dto;

public record LoginRequest(
        String email,
        String senha
) {}
