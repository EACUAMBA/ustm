CREATE DATABASE IF NOT EXISTS `teste_1`;
USE `teste_1`;

CREATE TABLE IF NOT EXISTS `departamento`(
	`numero` VARCHAR(255) NOT NULL UNIQUE,
    `nome` VARCHAR(255),
    `localizacao` VARCHAR(255),
    PRIMARY KEY (`numero`)
);

CREATE TABLE IF NOT EXISTS `empregado`(
	`nss` VARCHAR(255) NOT NULL UNIQUE,
    `pnome` VARCHAR(50),
    `mnome` VARCHAR(50),
    `snome` VARCHAR(50),
    `sexo` CHAR,
    `salario` DECIMAL(11, 2),
    `data_nascimento` DATE,
    `supervisor` VARCHAR(255),
    `departamento` VARCHAR(255),
    PRIMARY KEY (`nss`),
    FOREIGN KEY (`supervisor`) REFERENCES `empregado`(`nss`)
);

CREATE TABLE IF NOT EXISTS `gerente`(
	`empregado_nss` VARCHAR(255),
    `departamento_numero` VARCHAR(255),
    `data_inicio` DATE,
    FOREIGN KEY (`empregado_nss`) REFERENCES `empregado`(`nss`),
    FOREIGN KEY (`departamento_numero`) REFERENCES `departamento`(`numero`)
);

CREATE TABLE IF NOT EXISTS `projecto`(
	`numero` VARCHAR(255),
    `nome` VARCHAR(255),
    `localizacao` VARCHAR(255),
    `departamento_numero` VARCHAR(255),
    PRIMARY KEY (`numero`),
    FOREIGN KEY (`departamento_numero`) REFERENCES `departamento`(`numero`)
);

CREATE TABLE IF NOT EXISTS `trabalha_em`(
	`empregado_numero` VARCHAR(255),
    `projecto_numero` VARCHAR(255),
    `horas` VARCHAR(255),
    FOREIGN KEY (`empregado_numero`) REFERENCES `empregado`(`nss`),
    FOREIGN KEY (`projecto_numero`) REFERENCES `projecto`(`numero`)
);

CREATE TABLE IF NOT EXISTS `dependente`(
	`nome` VARCHAR(255),
    `sexo` VARCHAR(255),
    `data_nascimento` DATE,
    `empregado_numero` VARCHAR(255),
    FOREIGN KEY (`empregado_numero`) REFERENCES `empregado`(`nss`)
);



