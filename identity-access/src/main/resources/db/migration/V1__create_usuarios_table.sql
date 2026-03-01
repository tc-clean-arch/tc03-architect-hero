-- Habilita extensão para geração de UUID (Postgres)
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE usuarios (
                          id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
                          email VARCHAR(255) NOT NULL UNIQUE,
                          senha VARCHAR(255) NOT NULL,
                          papel VARCHAR(50) NOT NULL,
                          created_at TIMESTAMP NOT NULL DEFAULT now()
);

-- Garante valores válidos para o enum Papel
ALTER TABLE usuarios
    ADD CONSTRAINT chk_usuario_papel
        CHECK (papel IN ('USER', 'CLIENTE', 'DONO_RESTAURANTE', 'ADMIN'));
