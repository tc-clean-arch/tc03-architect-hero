CREATE TABLE pedidos (
                         id UUID PRIMARY KEY,
                         cliente_id UUID NOT NULL,
                         restaurante_id BIGINT NOT NULL,
                         status VARCHAR(30) NOT NULL,
                         total INTEGER NOT NULL
);

CREATE TABLE pedido_itens (
                              id BIGSERIAL PRIMARY KEY,
                              pedido_id UUID NOT NULL,
                              produto_id UUID NOT NULL,
                              nome VARCHAR(255) NOT NULL,
                              quantidade INTEGER NOT NULL,
                              preco INTEGER NOT NULL,
                              CONSTRAINT fk_pedido
                                  FOREIGN KEY (pedido_id)
                                      REFERENCES pedidos(id)
                                      ON DELETE CASCADE
);

CREATE INDEX idx_pedidos_cliente
    ON pedidos(cliente_id);

CREATE INDEX idx_pedido_itens_pedido
    ON pedido_itens(pedido_id);
