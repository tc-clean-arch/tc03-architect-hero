package com.fiap.infrastructure.db.postgres;

import com.fiap.core.entities.Papel;
import com.fiap.core.entities.Usuario;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(
        name = "usuarios",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "email")
        }
)
public class UsuarioPanacheEntity extends PanacheEntityBase {

    @Id
    @GeneratedValue
    @Column(nullable = false, updatable = false)
    public UUID id;

    @Column(nullable = false, unique = true)
    public String email;

    @Column(nullable = false)
    public String senha;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    public Papel papel;

    /**
     * Construtor exigido pelo JPA
     */
    protected UsuarioPanacheEntity() {
    }

    /**
     * Factory para criar a entidade JPA a partir do domínio
     */
    public static UsuarioPanacheEntity fromDomain(Usuario usuario) {
        UsuarioPanacheEntity entity = new UsuarioPanacheEntity();
        entity.id = usuario.getId();
        entity.email = usuario.getEmail();
        entity.senha = usuario.getSenha();
        entity.papel = usuario.getPapel();
        return entity;
    }

    /**
     * Converte entidade JPA para entidade de domínio
     */
    public Usuario toDomain() {
        return new Usuario(
                this.id,
                this.email,
                this.senha,
                this.papel
        );
    }
}
