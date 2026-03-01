package com.fiap.interfaceadapters.presenter.user;

import com.fiap.core.usecases.usuario.listar.ListarUsuariosOutputBoundary;
import com.fiap.core.usecases.usuario.listar.dto.ListarUsuariosOutputData;

import java.util.List;

public class ListarUsuariosPresenter implements ListarUsuariosOutputBoundary {
    private ListarUsuariosViewModel viewModel;

    @Override
    public void present(ListarUsuariosOutputData outputData) {
        List<ListarUsuariosViewModel.UsuarioItemViewModel> items =
                outputData.usuarios().stream()
                        .map(u -> new ListarUsuariosViewModel.UsuarioItemViewModel(
                                u.id(),
                                u.email(),
                                u.papel()
                        ))
                        .toList();

        String message = items.isEmpty()
                ? "Nenhum usuário encontrado."
                : "Usuários encontrados: " + items.size();

        viewModel = new ListarUsuariosViewModel(
                items,
                outputData.page(),
                outputData.size(),
                outputData.totalElements(),
                outputData.totalPages(),
                message
        );
    }

    public ListarUsuariosViewModel getViewModel() {
        return viewModel;
    }
}
