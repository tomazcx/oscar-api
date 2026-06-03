CREATE TABLE IF NOT EXISTS filmes (
    id     SERIAL       PRIMARY KEY,
    nome   VARCHAR(255) NOT NULL,
    genero VARCHAR(100) NOT NULL,
    foto   VARCHAR(500) NOT NULL
);

CREATE TABLE IF NOT EXISTS diretores (
    id   SERIAL       PRIMARY KEY,
    nome VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS usuarios (
    id    SERIAL       PRIMARY KEY,
    login VARCHAR(50)  NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL
);

CREATE INDEX IF NOT EXISTS idx_usuarios_login ON usuarios (login);

CREATE TABLE IF NOT EXISTS votos (
    id          SERIAL  PRIMARY KEY,
    usuario_id  INTEGER NOT NULL REFERENCES usuarios(id),
    filme_id    INTEGER NOT NULL REFERENCES filmes(id),
    diretor_id  INTEGER NOT NULL REFERENCES diretores(id),
    UNIQUE (usuario_id)
);
