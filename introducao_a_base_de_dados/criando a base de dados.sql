drop database if exists ibd_gestao_hospitalar;
create database if not exists ibd_gestao_hospitalar;
use ibd_gestao_hospitalar;


CREATE TABLE endereco
(
    id        int(10) NOT NULL AUTO_INCREMENT,
    provincia varchar(255),
    bairro    varchar(255),
    descricao varchar(255),
    PRIMARY KEY (id)
);

CREATE TABLE pessoa
(
    id              int(10)      NOT NULL AUTO_INCREMENT,
    nome            varchar(255) NOT NULL,
    data_nascimento date,
    sexo            varchar(1)   NOT NULL,
    endereco_id     int(10)      NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (endereco_id) REFERENCES endereco (id)
);

CREATE TABLE departamento
(
    id        int(10) NOT NULL AUTO_INCREMENT,
    nome      varchar(255),
    descricao varchar(510),
    PRIMARY KEY (id)
);

CREATE TABLE medico
(
    id              int(10) NOT NULL AUTO_INCREMENT,
    pessoa_id       int(10) NOT NULL UNIQUE,
    departamento_id int(10) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (pessoa_id) REFERENCES pessoa (id),
    FOREIGN KEY (departamento_id) REFERENCES departamento (id)

);

CREATE TABLE paciente
(
    id        int(10) NOT NULL AUTO_INCREMENT,
    pessoa_id int(10) NOT NULL UNIQUE,
    PRIMARY KEY (id),
    FOREIGN KEY (pessoa_id) REFERENCES pessoa (id)

);

CREATE TABLE recepcionista
(
    id        int(10) NOT NULL AUTO_INCREMENT,
    pessoa_id int(10) NOT NULL UNIQUE,
    PRIMARY KEY (id),
    FOREIGN KEY (pessoa_id) REFERENCES pessoa (id)
);

CREATE TABLE consulta
(
    id               int(10)        NOT NULL AUTO_INCREMENT,
    paciente_id      int(10)        NOT NULL,
    sintomas         varchar(510)   NOT NULL,
    data             timestamp      NOT NULL,
    valor_pago       decimal(19, 2) NOT NULL,
    recepcionista_id int(10)        NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (paciente_id) REFERENCES paciente (id),
    FOREIGN KEY (recepcionista_id) REFERENCES recepcionista (id)
);

CREATE TABLE receita
(
    id        int(10) NOT NULL AUTO_INCREMENT,
    data      date,
    descricao varchar(510),
    PRIMARY KEY (id)
);

CREATE TABLE medicamento
(
    id            int(10)        NOT NULL AUTO_INCREMENT,
    designacao    varchar(255),
    precounitario decimal(11, 2) NOT NULL,
    descricao     varchar(510),
    referencia    varchar(100)   NOT NULL UNIQUE,
    PRIMARY KEY (id)
);


CREATE TABLE receita_item
(
    id             int(10) NOT NULL AUTO_INCREMENT,
    medicamento_id int(10) NOT NULL,
    dosagem        int(10),
    quantidade     int(10),
    receita_id     int(10) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (receita_id) REFERENCES receita (id),
    FOREIGN KEY (medicamento_id) REFERENCES medicamento (id)
);

CREATE TABLE diagnostico
(
    id                   int(10)          NOT NULL AUTO_INCREMENT,
    recepcionista_id     int(10)          NOT NULL,
    paciente_id          int(10)          NOT NULL,
    detalhes             varchar(510)     NOT NULL,
    receita_id           int(10),
    transferido          bit(1) DEFAULT 0 NOT NULL,
    departamento_id      int(10)          NOT NULL,
    vem_de_transferencia bit(1),
    consulta_id          int(10)          NOT NULL,
    data                 date             NOT NULL,
    medico_id            int(10)          NOT NULL,
    hora                 time(6),
    PRIMARY KEY (id),
    FOREIGN KEY (recepcionista_id) REFERENCES recepcionista (id),
    FOREIGN KEY (medico_id) REFERENCES medico (id),
    FOREIGN KEY (departamento_id) REFERENCES departamento (id),
    FOREIGN KEY (consulta_id) REFERENCES consulta (id),
    FOREIGN KEY (paciente_id) REFERENCES paciente (id),
    FOREIGN KEY (receita_id) REFERENCES receita (id)
);
