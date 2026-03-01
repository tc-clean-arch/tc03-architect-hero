package com.fiap.interfaceadapters.presenter.user;

import com.fiap.core.usecases.usuario.alterarsenha.AlterarSenhaOutputBoundary;
import com.fiap.core.usecases.usuario.alterarsenha.AlterarSenhaOutputData;

public class AlterarSenhaPresenter implements AlterarSenhaOutputBoundary {

    private AlterarSenhaViewModel viewModel;

    @Override
    public void present(AlterarSenhaOutputData output) {
        this.viewModel = new AlterarSenhaViewModel(
                output.usuarioId(),
                "Senha alterada com sucesso."
        );
    }

    public AlterarSenhaViewModel getViewModel() {
        return viewModel;
    }
}

