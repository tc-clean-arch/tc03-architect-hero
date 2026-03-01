package com.fiap.infrastructure.web.rest;

import com.fiap.infrastructure.web.dto.*;
import com.fiap.interfaceadapters.controller.AuthController;
import com.fiap.interfaceadapters.presenter.user.*;
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
public class SignupResource {

    private final AuthController authController;
    private final RegistrarUsuarioPresenter registrarUsuarioPresenter;

    public SignupResource(
            AuthController authController,
            RegistrarUsuarioPresenter registrarUsuarioPresenter
    ) {
        this.authController = authController;
        this.registrarUsuarioPresenter = registrarUsuarioPresenter;
    }

    @POST
    @Path("/signup")
    @PermitAll
    public Response signup(SingUpUserRequest request) {
        authController.signUp(request.email(), request.senha(), null);

        return Response.status(Response.Status.CREATED)
                .entity(registrarUsuarioPresenter.getViewModel())
                .build();
    }
}

