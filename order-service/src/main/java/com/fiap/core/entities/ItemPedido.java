package com.fiap.core.entities;

import java.util.UUID;

public class ItemPedido {

    private final UUID produtoId;
    private final String nome;
    private final int quantidade;
    private final int preco;

    public ItemPedido(UUID produtoId,
                      String nome,
                      int quantidade,
                      int preco) {

        if (produtoId == null) throw new IllegalArgumentException("Produto obrigatório");
        if (quantidade <= 0) throw new IllegalArgumentException("Quantidade inválida");
        if (preco <= 0) throw new IllegalArgumentException("Preço inválido");

        this.produtoId = produtoId;
        this.nome = nome;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    public int subtotal() {
        return quantidade * preco;
    }

    public UUID getProdutoId() {
        return produtoId;
    }

    public String getNome() {
        return nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public int getPreco() {
        return preco;
    }
}
