package com.fiap.interfaceadapters.presenter.user;

import java.util.UUID;

public record BuscarUsuarioPorEmailViewModel(UUID id, String email, String papel) {
}
