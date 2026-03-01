package com.fiap.core.usecases.auth.login;

public interface LoginOutputBoundary {
    void present(LoginOutputData output);
    void presentError(String message);
}
