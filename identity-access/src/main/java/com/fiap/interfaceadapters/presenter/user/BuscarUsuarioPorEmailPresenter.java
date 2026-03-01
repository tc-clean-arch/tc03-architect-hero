package com.fiap.interfaceadapters.presenter.user;

import com.fiap.core.usecases.usuario.buscarPorEmail.BuscarUsuarioPorEmailOutputBoundary;
import com.fiap.core.usecases.usuario.buscarPorEmail.dto.BuscarUsuarioPorEmailOutputData;

public class BuscarUsuarioPorEmailPresenter implements BuscarUsuarioPorEmailOutputBoundary {
    private BuscarUsuarioPorEmailViewModel viewModel;

    @Override
    public void present(BuscarUsuarioPorEmailOutputData usuarioData) {
        viewModel = new BuscarUsuarioPorEmailViewModel (
                usuarioData.id(), usuarioData.email(), usuarioData.papel());
    }

    public BuscarUsuarioPorEmailViewModel getViewModel() {
        return viewModel;
    }
}
