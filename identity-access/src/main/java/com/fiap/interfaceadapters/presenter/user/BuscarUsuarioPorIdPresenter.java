package com.fiap.interfaceadapters.presenter.user;

import com.fiap.core.usecases.usuario.buscarPorId.BuscarUsuarioPorIdOutputBoundary;
import com.fiap.core.usecases.usuario.buscarPorId.dto.BuscarUsuarioPorIdOutputData;

public class BuscarUsuarioPorIdPresenter implements BuscarUsuarioPorIdOutputBoundary {

    BuscarUsuarioPorIdViewModel viewModel;

    @Override
    public void present(BuscarUsuarioPorIdOutputData outputData) {
        viewModel = new BuscarUsuarioPorIdViewModel(outputData.id(), outputData.email(), outputData.papel());
    }

    public BuscarUsuarioPorIdViewModel getViewModel() {
        return viewModel;
    }
    
}
