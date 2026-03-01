package com.fiap.infrastructure.web.config;

import com.fiap.core.dataaccess.UsuarioDataAccess;
import com.fiap.core.usecases.auth.PasswordEncoder;
import com.fiap.core.usecases.auth.TokenProvider;
import com.fiap.core.usecases.auth.login.LoginInputBoundary;
import com.fiap.core.usecases.auth.login.LoginInteractor;
import com.fiap.core.usecases.auth.login.LoginOutputBoundary;
import com.fiap.core.usecases.auth.singup.SignUpInteractor;
import com.fiap.core.usecases.usuario.alterarsenha.AlterarSenhaInputBoundary;
import com.fiap.core.usecases.usuario.alterarsenha.AlterarSenhaInteractor;
import com.fiap.core.usecases.usuario.alterarsenha.AlterarSenhaOutputBoundary;
import com.fiap.core.usecases.usuario.buscarPorEmail.BuscarUsuarioPorEmailInteractor;
import com.fiap.core.usecases.usuario.buscarPorEmail.BuscarUsuarioPorEmailOutputBoundary;
import com.fiap.core.usecases.usuario.buscarPorId.BuscarUsuarioPorIdInteractor;
import com.fiap.core.usecases.usuario.buscarPorId.BuscarUsuarioPorIdOutputBoundary;
import com.fiap.core.usecases.usuario.listar.ListarUsuariosInteractor;
import com.fiap.core.usecases.usuario.listar.ListarUsuariosOutputBoundary;
import com.fiap.core.usecases.usuario.registrar.RegistrarUsuarioInputBoundary;
import com.fiap.core.usecases.usuario.registrar.RegistrarUsuarioInteractor;
import com.fiap.core.usecases.usuario.registrar.RegistrarUsuarioOutputBoundary;
import com.fiap.core.usecases.usuario.serviceDomain.UsuarioQueryService;
import com.fiap.interfaceadapters.controller.AuthController;
import com.fiap.interfaceadapters.controller.UsuarioController;
import com.fiap.interfaceadapters.presenter.auth.LoginPresenter;
import com.fiap.interfaceadapters.presenter.user.*;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Produces;

@ApplicationScoped
public class BeanConfig {

    // =========================
    // PRESENTERS
    // =========================

    @Produces
    @RequestScoped
    public RegistrarUsuarioPresenter registrarUsuarioPresenter() {
        return new RegistrarUsuarioPresenter();
    }

    @Produces
    @RequestScoped
    public ListarUsuariosPresenter listarUsuariosPresenter() {
        return new ListarUsuariosPresenter();
    }

    @Produces
    @RequestScoped
    public LoginPresenter loginPresenter() {
        return new LoginPresenter();
    }

    @Produces
    @RequestScoped
    public BuscarUsuarioPorEmailPresenter buscarUsuarioPorEmailPresenter(){
        return new BuscarUsuarioPorEmailPresenter();
    }

    @Produces
    @RequestScoped
    public BuscarUsuarioPorIdPresenter buscarUsuarioPorIdPresenter() {
        return new BuscarUsuarioPorIdPresenter();
    }

    @Produces
    @RequestScoped
    public AlterarSenhaPresenter alterarSenhaPresenter() {
        return new AlterarSenhaPresenter();
    }

    // =========================
    // USE CASES — USER
    // =========================

    @Produces
    public RegistrarUsuarioInputBoundary registrarUsuarioInteractor(
            UsuarioDataAccess usuarioRepository,
            RegistrarUsuarioOutputBoundary presenter,
            PasswordEncoder passwordEncoder
    ) {
        return new RegistrarUsuarioInteractor(
                usuarioRepository,
                presenter,
                passwordEncoder
        );
    }

    @Produces
    public ListarUsuariosInteractor listarUsuariosInteractor(
            UsuarioDataAccess usuarioRepository,
            ListarUsuariosOutputBoundary presenter
    ) {
        return new ListarUsuariosInteractor(usuarioRepository, presenter);
    }

    @Produces
    public BuscarUsuarioPorEmailInteractor buscarUsuarioPorEmailInteractor(
            UsuarioDataAccess usuarioRepository,
            BuscarUsuarioPorEmailOutputBoundary presenter
    ) {
        return new BuscarUsuarioPorEmailInteractor(usuarioRepository, presenter);
    }

    @Produces
    public BuscarUsuarioPorIdInteractor buscarUsuarioPorIdInteractor(
            UsuarioDataAccess usuarioRepository,
            BuscarUsuarioPorIdOutputBoundary presenter
    ) {
        return new BuscarUsuarioPorIdInteractor(usuarioRepository, presenter);
    }
    @Produces
    public AlterarSenhaInputBoundary alterarSenhaInteractor(
            UsuarioDataAccess usuarioRepository,
            PasswordEncoder passwordEncoder,
            AlterarSenhaOutputBoundary presenter
    ) {
        return new AlterarSenhaInteractor(
                usuarioRepository,
                passwordEncoder,
                presenter
        );
    }

    // =========================
    // USE CASES — AUTH
    // =========================

    @Produces
    public SignUpInteractor signUpInteractor(
            RegistrarUsuarioInputBoundary registrarUsuarioInputBoundary
    ) {
        return new SignUpInteractor(registrarUsuarioInputBoundary);
    }

    // =========================
    // CONTROLLERS (Interface Adapters)
    // =========================

    @Produces
    public UsuarioController usuarioController(
            RegistrarUsuarioInputBoundary registerBoundary,
            ListarUsuariosInteractor listBoundary,
            BuscarUsuarioPorEmailInteractor buscarUsuarioPorEmailInteractor,
            BuscarUsuarioPorIdInteractor buscarUsuarioPorIdInteractor,
            AlterarSenhaInputBoundary alterarSenhaInteractor
    ) {
        return new UsuarioController(registerBoundary, listBoundary, buscarUsuarioPorEmailInteractor, buscarUsuarioPorIdInteractor, alterarSenhaInteractor);
    }

    @Produces
    public AuthController authController(
            LoginInputBoundary authUseCase,
            SignUpInteractor signUpUseCase
    ) {
        return new AuthController(authUseCase, signUpUseCase);
    }
    @Produces
    public LoginInputBoundary authenticateUserInteractor(
            UsuarioDataAccess usuarioDataAccess,
            PasswordEncoder passwordEncoder,
            TokenProvider tokenProvider,
            LoginOutputBoundary presenter
    ) {
        return new LoginInteractor(
                usuarioDataAccess,
                passwordEncoder,
                tokenProvider,
                presenter
        );
    }

    @Produces
    public UsuarioQueryService usuarioQueryService(
            UsuarioDataAccess usuarioDataAccess
    ) {
        return new UsuarioQueryService(usuarioDataAccess);
    }

}

