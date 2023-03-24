DROP DATABASE car_wash;

CREATE DATABASE car_wash;

USE car_wash;


CREATE TABLE automovel
(
    id                  int IDENTITY NOT NULL,
    fabricante          varchar(255) NOT NULL,
    modelo              varchar(255) NOT NULL,
    matricula           varchar(255) NOT NULL UNIQUE,
    proetario_pessoa_id int          NOT NULL,
    PRIMARY KEY (id)
);
CREATE TABLE cliente
(
    id              int IDENTITY NOT NULL,
    data_de_registo datetime     NULL,
    pessoa_id       int          NOT NULL UNIQUE,
    PRIMARY KEY (id)
);
CREATE TABLE factura
(
    id                     int IDENTITY   NOT NULL,
    numero                 varchar(255)   NOT NULL UNIQUE,
    data                   date           NOT NULL,
    desconto               decimal(12, 2) NULL,
    total                  decimal(12, 2) NOT NULL,
    cliente_id             int            NOT NULL,
    emissor_funcionario_id int            NOT NULL,
    created_at             datetime       NOT NULL,
    PRIMARY KEY (id)
);
CREATE TABLE factura_item
(
    id           int IDENTITY   NOT NULL,
    factura_id   int            NOT NULL,
    servico_id   int            NOT NULL,
    produto_id   int            NOT NULL,
    quantidade   int            NULL,
    automovel_id int            NOT NULL,
    desconto     decimal(12, 2) NULL,
    total        decimal(12, 2) NULL,
    PRIMARY KEY (id)
);
CREATE TABLE funcionario_
(
    id                  int IDENTITY NOT NULL,
    pessoa_id           int          NOT NULL UNIQUE,
    data_de_admissao    date         NOT NULL,
    tipo_funcionario_id int          NOT NULL,
    PRIMARY KEY (id)
);
CREATE TABLE pessoa
(
    id       int IDENTITY NOT NULL,
    nome     int          NULL,
    telefone int          NULL,
    PRIMARY KEY (id)
);
CREATE TABLE produto
(
    id             int IDENTITY   NOT NULL,
    nome           int            NULL UNIQUE,
    preco_unitario decimal(12, 2) NULL,
    PRIMARY KEY (id)
);
CREATE TABLE recibo
(
    id                     int IDENTITY   NOT NULL,
    total                  decimal(12, 2) NULL,
    client_id              int            NOT NULL,
    emissor_funcionario_id int            NOT NULL,
    numero                 varchar(255)   NULL,
    PRIMARY KEY (id)
);
CREATE TABLE recibo_factura
(
    recebido_id int NOT NULL,
    factura_id  int NOT NULL
);
CREATE TABLE servico
(
    id    int IDENTITY   NOT NULL,
    nome  varchar(255)   NOT NULL UNIQUE,
    preco decimal(12, 2) NOT NULL,
    PRIMARY KEY (id)
);
CREATE TABLE tipo_de_funcionario
(
    id         int IDENTITY NOT NULL,
    designacao int          NOT NULL,
    descricao  int          NULL,
    PRIMARY KEY (id)
);

ALTER TABLE factura_item
    ADD CONSTRAINT FKfactura_it91526 FOREIGN KEY (automovel_id) REFERENCES automovel (id);
ALTER TABLE factura_item
    ADD CONSTRAINT FKfactura_it27269 FOREIGN KEY (factura_id) REFERENCES factura (id);
ALTER TABLE factura_item
    ADD CONSTRAINT FKfactura_it96138 FOREIGN KEY (servico_id) REFERENCES servico (id);
ALTER TABLE factura_item
    ADD CONSTRAINT FKfactura_it303397 FOREIGN KEY (produto_id) REFERENCES produto (id);
ALTER TABLE recibo_factura
    ADD CONSTRAINT FKrecibo_fac452198 FOREIGN KEY (recebido_id) REFERENCES recibo (id);
ALTER TABLE recibo_factura
    ADD CONSTRAINT FKrecibo_fac639415 FOREIGN KEY (factura_id) REFERENCES factura (id);
ALTER TABLE factura
    ADD CONSTRAINT FKfactura649140 FOREIGN KEY (cliente_id) REFERENCES cliente (id);
ALTER TABLE cliente
    ADD CONSTRAINT FKcliente209272 FOREIGN KEY (pessoa_id) REFERENCES pessoa (id);
ALTER TABLE automovel
    ADD CONSTRAINT FKautomovel449190 FOREIGN KEY (proetario_pessoa_id) REFERENCES pessoa (id);
ALTER TABLE funcionario_
    ADD CONSTRAINT FKfuncionari297142 FOREIGN KEY (pessoa_id) REFERENCES pessoa (id);
ALTER TABLE factura
    ADD CONSTRAINT FKfactura46447 FOREIGN KEY (emissor_funcionario_id) REFERENCES funcionario_ (id);
ALTER TABLE recibo
    ADD CONSTRAINT FKrecebido982776 FOREIGN KEY (emissor_funcionario_id) REFERENCES funcionario_ (id);
ALTER TABLE recibo
    ADD CONSTRAINT FKrecebido441521 FOREIGN KEY (client_id) REFERENCES cliente (id);
ALTER TABLE funcionario_
    ADD CONSTRAINT FKfuncionari182107 FOREIGN KEY (tipo_funcionario_id) REFERENCES tipo_de_funcionario (id);


--INSERTS
-- pessoas
INSERT INTO car_wash.dbo.pessoa (nome, telefone)
VALUES (N'Ana', N'345435345345');

INSERT INTO car_wash.dbo.pessoa (nome, telefone)
VALUES (N'Maria', N'435435435');

INSERT INTO car_wash.dbo.pessoa (nome, telefone)
VALUES (N'Carla', N'34534534543');

INSERT INTO car_wash.dbo.pessoa (nome, telefone)
VALUES (N'Berta', N'1234232534545');

INSERT INTO car_wash.dbo.pessoa (nome, telefone)
VALUES (N'Sheila', N'23123123213');

INSERT INTO car_wash.dbo.pessoa (nome, telefone)
VALUES (N'Pedro', N'43543543');

INSERT INTO car_wash.dbo.pessoa (nome, telefone)
VALUES (N'Andre', N'34543534543');

INSERT INTO car_wash.dbo.pessoa (nome, telefone)
VALUES (N'Orlando', N'345435345345');

INSERT INTO car_wash.dbo.pessoa (nome, telefone)
VALUES (N'Julio', null);

INSERT INTO car_wash.dbo.pessoa (nome, telefone)
VALUES (N'Julia', null);

INSERT INTO car_wash.dbo.pessoa (nome, telefone)
VALUES (N'Ana', null);


-- clientes
INSERT INTO car_wash.dbo.cliente (data_de_registo, pessoa_id)
VALUES (N'2023-03-22 08:08:56.000', 1);

INSERT INTO car_wash.dbo.cliente (data_de_registo, pessoa_id)
VALUES (N'2023-04-22 08:09:00.000', 2);

INSERT INTO car_wash.dbo.cliente (data_de_registo, pessoa_id)
VALUES (N'2023-01-22 08:09:07.000', 3);

INSERT INTO car_wash.dbo.cliente (data_de_registo, pessoa_id)
VALUES (N'2022-05-22 08:09:23.000', 4);

INSERT INTO car_wash.dbo.cliente (data_de_registo, pessoa_id)
VALUES (N'2022-03-22 08:09:29.000', 5);

INSERT INTO car_wash.dbo.cliente (data_de_registo, pessoa_id)
VALUES (N'2022-06-22 08:09:33.000', 6);

INSERT INTO car_wash.dbo.cliente (data_de_registo, pessoa_id)
VALUES (N'2022-07-22 08:09:44.000', 7);

INSERT INTO car_wash.dbo.cliente (data_de_registo, pessoa_id)
VALUES (N'2022-10-22 08:09:51.000', 8);

-- tipos de funcionario
INSERT INTO car_wash.dbo.tipo_de_funcionario (id, designacao, descricao)
VALUES (5, N'Caixa', null);
INSERT INTO car_wash.dbo.tipo_de_funcionario (id, designacao, descricao)
VALUES (6, N'Limpador de carros', null);
INSERT INTO car_wash.dbo.tipo_de_funcionario (id, designacao, descricao)
VALUES (7, N'Faxineira', null);

-- funcionarios
INSERT INTO car_wash.dbo.funcionario (id, pessoa_id, data_de_admissao, tipo_funcionario_id)
VALUES (1, 9, N'2021-03-01', 5);
INSERT INTO car_wash.dbo.funcionario (id, pessoa_id, data_de_admissao, tipo_funcionario_id)
VALUES (2, 10, N'2021-03-31', 6);
INSERT INTO car_wash.dbo.funcionario (id, pessoa_id, data_de_admissao, tipo_funcionario_id)
VALUES (3, 11, N'2022-03-02', 7);

--automoveis
INSERT INTO car_wash.dbo.automovel (id, fabricante, modelo, matricula, proetario_pessoa_id)
VALUES (1, N'TOYOTA', N'RACTS', N'AAA', 1);
INSERT INTO car_wash.dbo.automovel (id, fabricante, modelo, matricula, proetario_pessoa_id)
VALUES (2, N'TOYOTA', N'HILUX', N'BBB', 1);
INSERT INTO car_wash.dbo.automovel (id, fabricante, modelo, matricula, proetario_pessoa_id)
VALUES (3, N'BMW', N'X6', N'CCC', 2);
INSERT INTO car_wash.dbo.automovel (id, fabricante, modelo, matricula, proetario_pessoa_id)
VALUES (4, N'AUDI', N'T3', N'DDD', 3);
INSERT INTO car_wash.dbo.automovel (id, fabricante, modelo, matricula, proetario_pessoa_id)
VALUES (5, N'BMW', N'X5', N'EEE', 4);

-- produtos
INSERT INTO car_wash.dbo.produto (id, nome, preco_unitario)
VALUES (1, N'Agua 1L', 50.00);
INSERT INTO car_wash.dbo.produto (id, nome, preco_unitario)
VALUES (2, N'Fanta 2L', 120.00);
INSERT INTO car_wash.dbo.produto (id, nome, preco_unitario)
VALUES (3, N'Cappy', 75.00);
INSERT INTO car_wash.dbo.produto (id, nome, preco_unitario)
VALUES (4, N'2M', 130.00);

-- servicos
INSERT INTO car_wash.dbo.servico (id, nome, preco)
VALUES (1, N'Limpeza interios', 500.00);
INSERT INTO car_wash.dbo.servico (id, nome, preco)
VALUES (2, N'Limpeza Exterior', 500.00);
INSERT INTO car_wash.dbo.servico (id, nome, preco)
VALUES (3, N'Polimento', 1500.00);

-- facturas
INSERT INTO car_wash.dbo.factura (id, numero, data, desconto, total, cliente_id, emissor_funcionario_id, created_at)
VALUES (1, N'2023/0001', N'2023-03-01', 0.00, 1400.00, 1, 1, N'2023-03-22 08:22:07.000');
INSERT INTO car_wash.dbo.factura (id, numero, data, desconto, total, cliente_id, emissor_funcionario_id, created_at)
VALUES (2, N'2023/0002', N'2023-03-02', 0.00, 500.00, 2, 2, N'2023-03-22 08:22:41.000');
INSERT INTO car_wash.dbo.factura (id, numero, data, desconto, total, cliente_id, emissor_funcionario_id, created_at)
VALUES (3, N'2023/0003', N'2023-03-02', 0.00, 900.00, 3, 2, N'2023-03-22 08:23:40.000');
INSERT INTO car_wash.dbo.factura (id, numero, data, desconto, total, cliente_id, emissor_funcionario_id, created_at)
VALUES (4, N'2023/0004', N'2023-03-03', 0.00, 1500.00, 4, 1, N'2023-03-22 08:24:42.000');

-- items da factura
INSERT INTO car_wash.dbo.factura_item (id, factura_id, servico_id, produto_id, quantidade, automovel_id, desconto,
                                       total)
VALUES (3, 3, 1, null, 1, 4, 0.00, 900.00);
INSERT INTO car_wash.dbo.factura_item (id, factura_id, servico_id, produto_id, quantidade, automovel_id, desconto,
                                       total)
VALUES (4, 2, 2, null, 1, 3, 0.00, 500.00);
INSERT INTO car_wash.dbo.factura_item (id, factura_id, servico_id, produto_id, quantidade, automovel_id, desconto,
                                       total)
VALUES (5, 1, 4, null, 1, 1, 0.00, 900.00);
INSERT INTO car_wash.dbo.factura_item (id, factura_id, servico_id, produto_id, quantidade, automovel_id, desconto,
                                       total)
VALUES (6, 1, 1, null, 1, 2, 0.00, 500.00);
INSERT INTO car_wash.dbo.factura_item (id, factura_id, servico_id, produto_id, quantidade, automovel_id, desconto,
                                       total)
VALUES (7, 4, 3, null, 1, 5, 0.00, 1500.00);

-- recibos
INSERT INTO car_wash.dbo.recebido (id, total, client_id, emissor_funcionario_id, numero)
VALUES (1, 1400.00, 1, 1,
        N'R2023/0001                                                                                                                                                                                                                                                     ');
INSERT INTO car_wash.dbo.recebido (id, total, client_id, emissor_funcionario_id, numero)
VALUES (2, 500.00, 2, 2,
        N'R2023/0002                                                                                                                                                                                                                                                     ');
INSERT INTO car_wash.dbo.recebido (id, total, client_id, emissor_funcionario_id, numero)
VALUES (3, 900.00, 3, 1,
        N'R2023/0003                                                                                                                                                                                                                                                     ');
INSERT INTO car_wash.dbo.recebido (id, total, client_id, emissor_funcionario_id, numero)
VALUES (4, 1500.00, 4, 3,
        N'R2023/0004                                                                                                                                                                                                                                                     ');

-- recibo X factura
INSERT INTO car_wash.dbo.recibo_factura (recebido_id, factura_id)
VALUES (1, 1);
INSERT INTO car_wash.dbo.recibo_factura (recebido_id, factura_id)
VALUES (2, 2);
INSERT INTO car_wash.dbo.recibo_factura (recebido_id, factura_id)
VALUES (3, 3);
INSERT INTO car_wash.dbo.recibo_factura (recebido_id, factura_id)
VALUES (4, 4);


exec sp_rename 'funcionario_', funcionario, 'OBJECT'
go



-- storage procedure
CREATE PROCEDURE sp_criar_cliente(
    @nome varchar(255), -- parametro (argumento, obrigatorio)
    @telefone varchar(255) = null -- (parametro (argumento opcional, defino um valor por defeito)
)
AS
DECLARE @nextPessoaId int; -- variavel, armazena o proximo id da pessoa
DECLARE @nextClientId int; -- variavel, armazena o proximo id do cliente
BEGIN
    SET IDENTITY_INSERT pessoa ON; -- activo a insercao de valores em colunas identity
    SELECT @nextPessoaId = max(p.id + 1) from pessoa p; -- pego o proximo id, pego o id maior e depois somo com 1
    INSERT INTO pessoa (id, nome, telefone) values (@nextPessoaId, @nome, @telefone); -- faço a inserção
    SET IDENTITY_INSERT pessoa OFF;-- desactivo a insercao de valores em colunas identity

    SET IDENTITY_INSERT cliente ON;-- activo a insercao de valores em colunas identity
    SELECT @nextClientId = max(c.id + 1) from cliente c; -- pego o proximo id, pego o id maior e depois somo com 1
    INSERT INTO cliente (id, data_de_registo, pessoa_id)
    values (@nextClientId, getdate(), @nextPessoaId); --faço a inserção
    SET IDENTITY_INSERT cliente OFF; -- desactivo a insercao de valores em colunas identity

    select 'Registado com sucesso!' as resultado --retorn um texto tabulado com coluna resultado e linha 'Reg...sso'
END
GO;

exec sp_criar_cliente 'Paulina', '123456789';

CREATE PROCEDURE sp_criar_funcionario(
    @nome varchar(255),
    @telefone varchar(255) = null,
    @tipoFuncionarioId int
)
AS
DECLARE @nextPessoaId int;
DECLARE @nextFuncionarioId int;
BEGIN
    SET IDENTITY_INSERT pessoa ON;
    SELECT @nextPessoaId = max(p.id + 1) from pessoa p;
    INSERT INTO pessoa (id, nome, telefone) values (@nextPessoaId, @nome, @telefone);
    SET IDENTITY_INSERT pessoa OFF;

    SET IDENTITY_INSERT funcionario ON;
    SELECT @nextFuncionarioId = max(f.id + 1) from funcionario f;
    INSERT INTO funcionario (id, pessoa_id, data_de_admissao, tipo_funcionario_id)
    values (@nextFuncionarioId, @nextPessoaId, getdate(), @tipoFuncionarioId);
    SET IDENTITY_INSERT funcionario OFF;

    select 'Registado com sucesso!' as resultado
END;
GO;

create function f_obtem_estado_da_factura(@facturaId int)
    returns varchar(255)
as
begin


    if ((select count(f.id) from factura f where f.id = @facturaId) <= 0)
        return N'factura não existe!'
    else
        if ((select count(r.id)
             from recibo_factura rf
                      inner join recibo r on r.id = rf.recebido_id
             where rf.factura_id = @facturaId) > 0)
            return N'pago';
        else
            return N'não pago'
    return ''
end;
go;

-- views
Create view todas_operacoes_ja_realizadas
as
Select f.numero,
       f.data,
       p.nome,
       f.total                                      as [total da factura],
       concat(ser.nome, prod.nome)                  as [produto ou servico],
       concat(ser.preco, prod.preco_unitario)       as [preco do produto ou servico],
       isnull(replace(trim(concat(auto.fabricante, ' ', auto.modelo, ' (', auto.matricula, ')')), '()', ''),
              null)                                 as [automovel (fabric. modelo (matricula))],
       (select dbo.f_obtem_estado_da_factura(f.id)) as estado

from factura f
         inner join cliente c on c.id = f.cliente_id
         inner join pessoa p on p.id = c.pessoa_id
         inner join funcionario fun on fun.id = f.emissor_funcionario_id
         inner join pessoa pf on pf.id = fun.pessoa_id
         inner join factura_item fi on fi.factura_id = f.id
         left join servico ser on ser.id = fi.servico_id
         left join produto prod on prod.id = fi.produto_id
         left join automovel auto on auto.id = fi.automovel_id
         left join recibo_factura rf on f.id = rf.factura_id
         left join recibo rec on rf.recebido_id = rec.id


select dbo.f_obtem_estado_da_factura(4);


select *
from todas_operacoes_ja_realizadas
order by data asc;







