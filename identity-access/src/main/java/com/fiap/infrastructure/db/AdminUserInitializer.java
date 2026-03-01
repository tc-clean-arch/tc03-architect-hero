// package com.fiap.infrastructure.db;
//
// import com.fiap.core.dataaccess.UsuarioDataAccess;
// import com.fiap.core.entities.Papel;
// import com.fiap.core.entities.Usuario;
// import com.fiap.core.usecases.auth.PasswordEncoder;
// import io.quarkus.runtime.StartupEvent;
// import jakarta.enterprise.context.ApplicationScoped;
// import jakarta.enterprise.event.Observes;
// import jakarta.inject.Inject;
//
// import static com.fiap.core.entities.Usuario.novoUsuario;
//
// @ApplicationScoped
// public class AdminUserInitializer {
//
//     @Inject
//     PasswordEncoder passwordEncoder;
//
//
//     @Inject
//     UsuarioDataAccess usuarioRepository;
//
//     void onStart(@Observes StartupEvent event) {
//
//         String adminEmail = "admin@example.com";
//         String rawPassword = "senha123";
//
//         if (usuarioRepository.existePorEmail(adminEmail)) {
//             return;
//         }
//
//         Papel tipoAdmin = Papel.ADMIN;
//
//         String hash = passwordEncoder.hash(rawPassword);
//
//         Usuario admin = novoUsuario(
//                 adminEmail,
//                 hash,
//                 tipoAdmin
//         );
//
//         usuarioRepository.salvar(admin);
//     }
// }
//
