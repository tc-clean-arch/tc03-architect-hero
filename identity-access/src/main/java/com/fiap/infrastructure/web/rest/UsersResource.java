package com.fiap.infrastructure.web.rest;

import com.fiap.infrastructure.web.dto.*;
import com.fiap.interfaceadapters.controller.UsuarioController;
import com.fiap.interfaceadapters.presenter.user.*;
import io.quarkus.security.Authenticated;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.UUID;

@Path("/api/v1/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped
@Authenticated
public class UsersResource {

    private final UsuarioController usuarioController;
    private final RegistrarUsuarioPresenter registrarUsuarioPresenter;
    private final ListarUsuariosPresenter listarUsuariosPresenter;
    private final BuscarUsuarioPorIdPresenter buscarUsuarioPresenter;
    private final BuscarUsuarioPorEmailPresenter buscarUsuarioPorEmailPresenter;
    private final AlterarSenhaPresenter alterarSenhaPresenter;

    public UsersResource(
            UsuarioController usuarioController,
            RegistrarUsuarioPresenter registrarUsuarioPresenter,
            ListarUsuariosPresenter listarUsuariosPresenter,
            BuscarUsuarioPorIdPresenter buscarUsuarioPresenter,
            BuscarUsuarioPorEmailPresenter buscarUsuarioPorEmailPresenter,
            AlterarSenhaPresenter alterarSenhaPresenter
    ) {
        this.usuarioController = usuarioController;
        this.registrarUsuarioPresenter = registrarUsuarioPresenter;
        this.listarUsuariosPresenter = listarUsuariosPresenter;
        this.buscarUsuarioPresenter = buscarUsuarioPresenter;
        this.buscarUsuarioPorEmailPresenter = buscarUsuarioPorEmailPresenter;
        this.alterarSenhaPresenter = alterarSenhaPresenter;
    }

    @PATCH
    @Path("/{id}/password")
    public Response alterarSenha(
            @PathParam("id") UUID id,
            AlterarSenhaRequest request
    ) {
        usuarioController.alterarSenha(
                id,
                request.senhaAtual(),
                request.novaSenha()
        );

        return Response.ok(alterarSenhaPresenter.getViewModel()).build();
    }

    @POST
    @RolesAllowed("ADMIN")
    public Response registrar(RegistrarUsuarioRequest request) {
        usuarioController.registrar(
                request.email(),
                request.senha(),
                request.papel()
        );

        return Response.status(Response.Status.CREATED)
                .entity(registrarUsuarioPresenter.getViewModel())
                .build();
    }

    @GET
    @RolesAllowed("ADMIN")
    public Response listar(
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("size") @DefaultValue("20") int size
    ) {
        usuarioController.listarTodos(page, Math.min(size, 100));
        return Response.ok(listarUsuariosPresenter.getViewModel()).build();
    }

    @GET
    @Path("/{id}")
    @RolesAllowed("ADMIN")
    public Response buscarPorId(@PathParam("id") UUID id) {
        usuarioController.buscarPorId(id);
        return Response.ok(buscarUsuarioPresenter.getViewModel()).build();
    }

    @GET
    @Path("/email/{email}")
    @RolesAllowed("ADMIN")
    public Response buscarPorEmail(@PathParam("email") String email) {
        usuarioController.buscarUsuarioPorEmail(email);
        return Response.ok(buscarUsuarioPorEmailPresenter.getViewModel()).build();
    }
}

