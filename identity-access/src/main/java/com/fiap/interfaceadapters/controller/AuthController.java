package com.fiap.interfaceadapters.controller;

import com.fiap.core.usecases.auth.login.LoginInputBoundary;
import com.fiap.core.usecases.auth.login.LoginInputData;
import com.fiap.core.usecases.auth.singup.SignUpInteractor;
import com.fiap.core.usecases.usuario.registrar.dto.RegistrarUsuarioInputData;

public class AuthController {
    private final LoginInputBoundary authUseCase;
    private final SignUpInteractor signUpUseCase;

    public AuthController(LoginInputBoundary authUseCase, SignUpInteractor signUpUseCase) {
        this.authUseCase = authUseCase;
        this.signUpUseCase = signUpUseCase;
    }

    public void login(String email, String password) {
        authUseCase.execute(new LoginInputData(email, password));
    }

    public void signUp(String email, String senha, String papel) {
        RegistrarUsuarioInputData input = new RegistrarUsuarioInputData(email, senha, papel);
        signUpUseCase.execute(input);
    }
}