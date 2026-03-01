INSERT INTO usuarios (email, senha, papel)
SELECT
    'admin@example.com',
    '$2a$12$aCH77CJgrF/RAPRhrGUr6uZbATcVA6RIRpbJmQkpezUTj4ir6W1sq',
    'ADMIN'
    WHERE NOT EXISTS (
    SELECT 1 FROM usuarios WHERE email = 'admin@example.com'
);
