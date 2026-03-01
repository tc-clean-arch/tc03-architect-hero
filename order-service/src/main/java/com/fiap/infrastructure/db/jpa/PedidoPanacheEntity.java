package com.fiap.infrastructure.db.jpa;

import com.fiap.core.entities.ItemPedido;
import com.fiap.core.entities.Pedido;
import com.fiap.core.entities.StatusPedido;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "pedidos")
public class PedidoPanacheEntity extends PanacheEntityBase {

    @Id
    @UuidGenerator
    @Column(nullable = false, updatable = false)
    public UUID id;

    @Column(name = "cliente_id", nullable = false)
    public UUID clienteId;

    @Column(name = "restaurante_id", nullable = false)
    public Long restauranteId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    public StatusPedido status;

    @Column(nullable = false)
    public int total;

    @OneToMany(
            mappedBy = "pedido",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    public List<ItemPedidoPanacheEntity> itens = new ArrayList<>();

    public static PedidoPanacheEntity fromDomain(Pedido pedido) {
        PedidoPanacheEntity entity = new PedidoPanacheEntity();

        entity.id = pedido.getId();

        entity.clienteId = pedido.getClienteId();
        entity.restauranteId = pedido.getRestauranteId();
        entity.status = pedido.getStatus();
        entity.total = pedido.getTotal();

        entity.itens = new ArrayList<>(
                pedido.getItens().stream().map(item -> {
                    ItemPedidoPanacheEntity e = new ItemPedidoPanacheEntity();
                    e.pedido = entity;
                    e.produtoId = item.getProdutoId();
                    e.nome = item.getNome();
                    e.quantidade = item.getQuantidade();
                    e.preco = item.getPreco();
                    return e;
                }).toList()
        );

        return entity;
    }

    public Pedido toDomain() {
        List<ItemPedido> itensDomain = itens.stream()
                .map(i -> new ItemPedido(
                        i.produtoId,
                        i.nome,
                        i.quantidade,
                        i.preco
                ))
                .toList();

        return new Pedido(
                id,
                clienteId,
                restauranteId,
                itensDomain,
                status
        );
    }
}
