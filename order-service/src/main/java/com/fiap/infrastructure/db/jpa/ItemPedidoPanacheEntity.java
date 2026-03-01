package com.fiap.infrastructure.db.jpa;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "pedido_itens")
public class ItemPedidoPanacheEntity extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long id;


    @ManyToOne(optional = false)
    @JoinColumn(name = "pedido_id")
    public PedidoPanacheEntity pedido;

    @Column(name = "produto_id", nullable = false)
    public UUID produtoId;

    @Column(nullable = false)
    public String nome;

    @Column(nullable = false)
    public int quantidade;

    @Column(nullable = false)
    public int preco;
}
