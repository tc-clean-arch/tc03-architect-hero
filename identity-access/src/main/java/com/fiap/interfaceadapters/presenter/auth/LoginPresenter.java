package com.fiap.interfaceadapters.presenter.auth;

import com.fiap.core.usecases.auth.login.LoginOutputBoundary;
import com.fiap.core.usecases.auth.login.LoginOutputData;

public class LoginPresenter implements LoginOutputBoundary {
    private LoginViewModel viewModel;

    @Override
    public void present(LoginOutputData output) {
        viewModel = new LoginViewModel(
                true,
                "Autenticação realizada com sucesso.",
                output.token(),
                output.userId(),
                output.email(),
                output.papel()
        );
    }

    @Override
    public void presentError(String message) {
        viewModel = new LoginViewModel(
                false,
                message,
                null,
                null,
                null,
                null
        );
    }

    public LoginViewModel getViewModel() {
        return viewModel;
    }
}
