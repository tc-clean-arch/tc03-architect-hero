// package com.fiap.infrastructure.db;
//
// import com.fiap.core.entities.Usuario;
// import com.fiap.core.usecases.usuario.listar.dto.PagedResult;
// import com.fiap.interfaceadapters.gateway.UsuarioGateway;
// import jakarta.enterprise.context.ApplicationScoped;
//
// import java.util.*;
//
// @ApplicationScoped
// public class InMemoryUsuarioRepository implements UsuarioGateway {
//
//     private final Map<UUID, Usuario> storage = new HashMap<>();
//
//
//     @Override
//     public boolean existePorEmail(String email) {
//         return storage.values().stream()
//                 .anyMatch(u -> u.getEmail().equalsIgnoreCase(email));
//     }
//
//     @Override
//     public Usuario salvar(Usuario usuario) {
//         storage.put(usuario.getId(), usuario);
//         return usuario;
//     }
//
//     @Override
//     public PagedResult<Usuario> obterTodosPaginado(int page, int size) {
//         if (page < 0) page = 0;
//         if (size <= 0) size = 10;
//
//         List<Usuario> allUsers = storage.values().stream()
//                 .sorted(Comparator.comparing(Usuario::getId))
//                 .toList();
//
//         long totalElements = allUsers.size();
//
//         int fromIndex = page * size;
//         int toIndex = Math.min(fromIndex + size, allUsers.size());
//
//         if (fromIndex >= allUsers.size()) {
//             return new PagedResult<>(List.of(), totalElements);
//         }
//
//         List<Usuario> pageContent = allUsers.subList(fromIndex, toIndex);
//
//         return new PagedResult<>(pageContent, totalElements);
//     }
//
//     @Override
//     public List<Usuario> obterTodos() {
//         return storage.values().stream()
//                 .sorted(Comparator.comparing(Usuario::getId))
//                 .toList();
//     }
//
//     @Override
//     public Optional<Usuario> obterPorEmail(String email) {
//         return storage.values().stream()
//                 .filter(u -> u.getEmail().equalsIgnoreCase(email))
//                 .findFirst();
//     }
//
//     @Override
//     public Optional<Usuario> obterPorId(UUID id) {
//         return storage.values().stream()
//                 .filter(u -> u.getId().equals(id))
//                 .findFirst();
//     }
//
//     public void clear() {
//         storage.clear();
//     }
// }
