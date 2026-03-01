package com.fiap.core.entities;

import com.fiap.core.exception.ClienteObrigatorioException;
import com.fiap.core.exception.ItensObrigatoriosException;
import com.fiap.core.exception.RestauranteObrigatorioException;

import java.util.List;
import java.util.UUID;

public class Pedido {

    private final UUID id;
    private final UUID clienteId;
    private final Long restauranteId;
    private final List<ItemPedido> itens;
    private final int total;
    private final StatusPedido status;

    public Pedido(UUID id,
                  UUID clienteId,
                  Long restauranteId,
                  List<ItemPedido> itens,
                  StatusPedido status) {

        validarInvariantes(clienteId, restauranteId, itens);

        this.id = id;
        this.clienteId = clienteId;
        this.restauranteId = restauranteId;
        this.itens = List.copyOf(itens);
        this.total = calcularTotal(itens);
        this.status = status == null ? StatusPedido.CRIADO : status;
    }

    public static Pedido novo(UUID clienteId,
                              Long restauranteId,
                              List<ItemPedido> itens) {
        return new Pedido(
                null, // ID será gerado pelo JPA
                clienteId,
                restauranteId,
                itens,
                StatusPedido.CRIADO
        );
    }

    public Pedido marcarComoPago() {
        return new Pedido(
                id,
                clienteId,
                restauranteId,
                itens,
                StatusPedido.PAGO
        );
    }

    public Pedido marcarComoPendentePagamento() {
        return new Pedido(
                id,
                clienteId,
                restauranteId,
                itens,
                StatusPedido.PENDENTE_PAGAMENTO
        );
    }

    private void validarInvariantes(UUID clienteId,
                                    Long restauranteId,
                                    List<ItemPedido> itens) {
        if (clienteId == null) throw new ClienteObrigatorioException();
        if (restauranteId == null) throw new RestauranteObrigatorioException();
        if (itens == null || itens.isEmpty()) throw new ItensObrigatoriosException();
    }

    private int calcularTotal(List<ItemPedido> itens) {
        return itens.stream()
                .mapToInt(ItemPedido::subtotal)
                .sum();
    }

    public UUID getId() {
        return id;
    }

    public UUID getClienteId() {
        return clienteId;
    }

    public Long getRestauranteId() {
        return restauranteId;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public int getTotal() {
        return total;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public boolean isPago() {
        return status == StatusPedido.PAGO;
    }

}
