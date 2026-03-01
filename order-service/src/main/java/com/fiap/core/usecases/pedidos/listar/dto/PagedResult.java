package com.fiap.core.usecases.pedidos.listar.dto;

import java.util.List;

public record PagedResult<T>(
        List<T> data,
        long totalElements
) {}