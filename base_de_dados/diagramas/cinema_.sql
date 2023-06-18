CREATE DATABASE cinema;

use cinema;

create table sala
(
    id                bigint identity (1, 1),
    numero            varchar(20) not null,
    numero_de_lugares int,

    primary key (id),
    constraint UNIQUE_SALA_NUMERO unique (numero),
    index INDEX_SALA_NUMERO (numero)
);

create table filme
(
    id                bigint identity (1, 1),
    nome_em_portugues varchar(255),
    nome_original     varchar(255),
    director          varchar(255),
    ano_lancamento    smallint,
    tipo              varchar(255),
    sinopse           varchar(max),

    primary key (id),
    constraint UNIQUE_FILME_NOME_EM_PORTUGUES_ANO_LANCAMENTO unique (nome_em_portugues, ano_lancamento)
);

create table premiacao
(
    id        bigint identity (1, 1),
    ano       smallint,
    descricao varchar(255),

    filme_id  bigint,

    primary key (id),
    CONSTRAINT FK_PREMIACAO_FILME_ID foreign key (filme_id) references filme (id)
);

create table horario
(
    id   bigint identity (1, 1),
    hora time,
    constraint PK_HORARIO_ID primary key (id),
    CHECK (HORA in ('16:00', '17:00', '18:00', '19:30', '20:00', '22:00', '24:00'))
);

create table exibicao
(
    id         bigint identity (1, 1),
    filme_id   bigint,
    horario_id bigint,

    primary key (id),
    foreign key (filme_id) references filme (id),
    foreign key (horario_id) references horario (id)
);

create table exibicao_sala
(
    exibicao_id bigint,
    sala_id     bigint,

    foreign key (exibicao_id) references exibicao (id),
    foreign key (sala_id) references sala (id),
);

create table funcionario
(
    id                             bigint,
    funcao                         varchar(255),
    numero_de_carteira_de_trabalho varchar(20),
    nome                           varchar(255),
    data_de_emissao                varchar(255),
    salario                        varchar(255),

    primary key (id),
    constraint UNIQUE_FUNCIONARIO_NUMERO_CARTEIRA_TRABALHO unique (numero_de_carteira_de_trabalho)
);

create table horario_de_trabalho
(
    horario_id     bigint,
    funcionario_id bigint,

    foreign key (horario_id) references horario (id),
    foreign key (funcionario_id) references funcionario (id),

);


-- INSERTIONS
alter table funcionario
    add default identity(1, 1) for id

INSERT INTO funcionario
(id, numero_de_carteira_de_trabalho, nome, data_de_emissao, salario)
values (1, '123456789', 'Arlindo Boaventura', '20230306', 190000.99);


select * from funcionario where numero_de_carteira_de_trabalho like '%1%';

