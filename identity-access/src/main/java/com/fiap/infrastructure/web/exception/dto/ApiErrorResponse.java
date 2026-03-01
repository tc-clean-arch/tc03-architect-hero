package com.fiap.infrastructure.web.exception.dto;

public record ApiErrorResponse(
        String type,
        String message
) {}
