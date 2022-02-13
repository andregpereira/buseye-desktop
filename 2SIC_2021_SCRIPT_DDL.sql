-- DROP DATABASE Projeto_BusEye;
CREATE DATABASE Projeto_BusEye;

USE Projeto_BusEye;

-- DROP TABLE funcionario CASCADE;
CREATE TABLE funcionario (
    idFuncionario  INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
    nome           VARCHAR(50) NOT NULL,
    cpf            CHAR(11) NOT NULL UNIQUE,
    dataNasc       DATE NOT NULL,
    email          VARCHAR(50) NOT NULL UNIQUE CHECK(email LIKE '%@%.com'),
    telefone       CHAR(11) NOT NULL UNIQUE,
    cargo          VARCHAR(30) NOT NULL,
    situacao       VARCHAR(7) NOT NULL CHECK(situacao IN ('ATIVO', 'INATIVO'))
);

select * from usuario;

-- DROP TABLE linha CASCADE;
CREATE TABLE linha (
    idLinha                    INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
    nome                       VARCHAR(50) NOT NULL,
    duracaoPercurso            TIME NOT NULL,
    funcionario_idFuncionario  INTEGER NOT NULL
);

-- DROP TABLE listaFavoritos CASCADE;
CREATE TABLE listaFavoritos (
    idLista            INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
    linha_idLinha      INTEGER NOT NULL,
    usuario_idUsuario  INTEGER NOT NULL
);

-- DROP TABLE onibus CASCADE;
CREATE TABLE onibus (
    idOnibus                   INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
    localizacao                VARCHAR(30) NOT NULL,
    funcionario_idFuncionario  INTEGER NOT NULL,
    linha_idLinha              INTEGER NOT NULL,
    capacidade                 INTEGER NOT NULL
);

-- DROP TABLE usuario CASCADE;
CREATE TABLE usuario (
    idUsuario    INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
    email        VARCHAR(50) NOT NULL UNIQUE CHECK(email LIKE '%@%.com'),
    localizacao  VARCHAR(50) NOT NULL
);

ALTER TABLE linha
    ADD CONSTRAINT linha_funcionario_fk FOREIGN KEY ( funcionario_idFuncionario )
        REFERENCES funcionario ( idFuncionario )
            ON DELETE CASCADE;

ALTER TABLE listaFavoritos
    ADD CONSTRAINT listaFavoritos_linha_fk FOREIGN KEY ( linha_idLinha )
        REFERENCES linha ( idLinha )
            ON DELETE CASCADE;

ALTER TABLE listaFavoritos
    ADD CONSTRAINT listaFavoritos_usuario_fk FOREIGN KEY ( usuario_idUsuario )
        REFERENCES usuario ( idUsuario )
            ON DELETE CASCADE;

ALTER TABLE onibus
    ADD CONSTRAINT onibus_funcionario_fk FOREIGN KEY ( funcionario_idFuncionario )
        REFERENCES funcionario ( idFuncionario )
            ON DELETE CASCADE;

ALTER TABLE onibus
    ADD CONSTRAINT onibus_linha_fk FOREIGN KEY ( linha_idLinha )
        REFERENCES linha ( idLinha )
            ON DELETE CASCADE;