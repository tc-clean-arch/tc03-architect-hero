package com.fiap.infrastructure.db.postgres;//package com.fiap.identityaccess.infrastructure.db.postgres;
//
//import com.fiap.core.entities.Usuario;
//import com.fiap.core.usecases.usuario.listar.dto.PagedResult;
//import com.fiap.interfaceadapters.gateway.UsuarioGateway;
//import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
//import io.quarkus.panache.common.Sort;
//import jakarta.enterprise.context.ApplicationScoped;
//import jakarta.transaction.Transactional;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.UUID;
//import java.util.stream.Collectors;
//
//@ApplicationScoped
//public class UsuarioPostgresRepository
//        implements UsuarioGateway,
//        PanacheRepositoryBase<UsuarioEntity, UUID> {
//
//    private final PapelPostgresRepository papelRepository;
//
//    public UsuarioPostgresRepository(
//            PapelPostgresRepository papelRepository
//    ) {
//        this.papelRepository = papelRepository;
//    }
//
//    @Override
//    public boolean existePorEmail(String email) {
//        return count("email", email) > 0;
//    }
//
//    @Override
//    @Transactional
//    public Usuario salvar(Usuario usuario) {
//
//        PapelEntity papelEntity =
//                PapelEntity.fromDomain(papelRepository
//                        .obterPorPapel(
//                                PapelEntity.Papel.fromDomain(
//                                        usuario.getPapel().getPapel()
//                                )
//                        )
//                        .orElseThrow(() ->
//                                new IllegalStateException(
//                                        "Papel não encontrado: " +
//                                                usuario.getPapel().getPapel()
//                                )
//                        ));
//
//        UsuarioEntity entity =
//                UsuarioEntity.fromDomain(usuario, papelEntity);
//
//        UsuarioEntity managedEntity = getEntityManager().merge(entity);
//        getEntityManager().flush();
//
//        return managedEntity.toDomain();
//    }
//
//    @Override
//    public PagedResult<Usuario> obterTodosPaginado(int page, int size) {
//
//        List<Usuario> usuarios =
//                findAll(Sort.descending("id"))
//                        .page(page, size)
//                        .stream()
//                        .map(UsuarioEntity::toDomain)
//                        .collect(Collectors.toList());
//
//        return new PagedResult<>(usuarios, size);
//    }
//
//    @Override
//    public Optional<Usuario> obterPorEmail(String email) {
//        return find("email", email)
//                .firstResultOptional()
//                .map(UsuarioEntity::toDomain);
//    }
//
//    @Override
//    public Optional<Usuario> obterPorId(UUID id) {
//        return findByIdOptional(id)
//                .map(UsuarioEntity::toDomain);
//    }
//
//    @Override
//    public List<Usuario> obterTodos() {
//        return listAll()
//                .stream()
//                .map(UsuarioEntity::toDomain)
//                .collect(Collectors.toList());
//    }
//}
