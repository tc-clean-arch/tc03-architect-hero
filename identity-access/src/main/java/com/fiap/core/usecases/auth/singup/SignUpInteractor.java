package com.fiap.core.usecases.auth.singup;

import com.fiap.core.entities.Papel;
import com.fiap.core.usecases.usuario.registrar.RegistrarUsuarioInputBoundary;
import com.fiap.core.usecases.usuario.registrar.dto.RegistrarUsuarioInputData;

public class SignUpInteractor {

    private final RegistrarUsuarioInputBoundary registerUserUseCase;

    public SignUpInteractor(RegistrarUsuarioInputBoundary registerUserUseCase) {
        this.registerUserUseCase = registerUserUseCase;
    }

    public void execute(RegistrarUsuarioInputData inputData) {
        RegistrarUsuarioInputData registerInput = new RegistrarUsuarioInputData(
                inputData.email(),
                inputData.senha(),
                Papel.CLIENTE.name()
        );

        registerUserUseCase.execute(registerInput);
    }
}