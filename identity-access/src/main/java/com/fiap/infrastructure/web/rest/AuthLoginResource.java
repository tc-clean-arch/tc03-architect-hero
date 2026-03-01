package com.fiap.infrastructure.web.rest;

import com.fiap.infrastructure.web.dto.*;
import com.fiap.interfaceadapters.controller.AuthController;
import com.fiap.interfaceadapters.presenter.auth.LoginPresenter;
import com.fiap.interfaceadapters.presenter.auth.LoginViewModel;
import jakarta.annotation.security.PermitAll;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


@Path("/api/v1/auth")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class AuthLoginResource {

    private final AuthController authController;
    private final LoginPresenter loginPresenter;

    public AuthLoginResource(
            AuthController authController,
            LoginPresenter loginPresenter
    ) {
        this.authController = authController;
        this.loginPresenter = loginPresenter;
    }

    @POST
    @Path("/login")
    @PermitAll
    public Response login(LoginRequest request) {
        authController.login(request.email(), request.senha());
        LoginViewModel vm = loginPresenter.getViewModel();

        return vm.success()
                ? Response.ok(vm).build()
                : Response.status(Response.Status.UNAUTHORIZED).entity(vm).build();
    }
}

