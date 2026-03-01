package com.fiap.infrastructure.web.dto;

public record AlterarSenhaRequest(
        String senhaAtual,
        String novaSenha
) {}
