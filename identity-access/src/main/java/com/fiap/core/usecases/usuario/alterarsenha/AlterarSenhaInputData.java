package com.fiap.core.usecases.usuario.alterarsenha;

import java.util.UUID;

public record AlterarSenhaInputData(
        UUID usuarioId,
        String senhaAtual,
        String novaSenha
) {}
