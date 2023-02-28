DROP DATABASE ibd;
CREATE DATABASE IF NOT EXISTS ibd;
USE ibd;

CREATE TABLE IF NOT EXISTS pessoa(
id int not null unique auto_increment,
primeiro_nome varchar(50) not null,
nomes_meio varchar(100) null,
apelido varchar(50) not null,
data_nascimento date not null,
primary key (id)
);

INSERT INTO pessoa(primeiro_nome, nomes_meio, apelido, data_nascimento)
values
('Edilson', 'Alexandre', 'Cuamba', '19991027'),
('João', null, 'Alburquerque', '19991027'),
('Amelia', 'Nilza', 'Riu', '19991027'),
('Ninata', 'Ikorle', 'Uty', '19991027'),
('Lerton', 'Unito', 'Katyo', '19991027'),
('Benjamin', 'Kirlosson', 'Builder', '19991027')
;


SELECT * FROM pessoa;
SELECT * FROM pessoa ORDER BY primeiro_nome;
Select * from pessoa ORDER BY nomes_meio;

CREATE TABLE carro (
id int unique not null,
marca varchar(50) not null,
modelo varchar(50) not null,
matricula varchar(50) not null unique,
proprietaria_pessoa_id int not null,
primary key (id),
foreign key (proprietaria_pessoa_id) references pessoa (id)
);

ALTER TABLE carro MODIFY COLUMN id int unique not null auto_increment;

insert into carro (marca, modelo, matricula, proprietaria_pessoa_id)
values ('NISSAN', 'AMAROK', 'AAA-001-MP', '1')




