package com.fiap.interfaceadapters.presenter.user;

import com.fiap.core.usecases.usuario.registrar.RegistrarUsuarioOutputBoundary;
import com.fiap.core.usecases.usuario.registrar.dto.RegistrarUsuarioOutputData;

// Presenter
public class RegistrarUsuarioPresenter implements RegistrarUsuarioOutputBoundary {
    private RegistrarUsuarioViewModel viewModel;

    @Override
    public void present(RegistrarUsuarioOutputData outputData) {
        viewModel = new RegistrarUsuarioViewModel(
                outputData.id(),
                outputData.email(),
                outputData.papel(),
                "Usuário registrado com sucesso!",
                true
        );
    }

    @Override
    public void presentError(String message) {
        viewModel = new RegistrarUsuarioViewModel(
                null,
                null,
                null,
                message,
                false
        );
    }

    public RegistrarUsuarioViewModel getViewModel() {
        return viewModel;
    }
}