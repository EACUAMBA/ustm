-- Insert Departamento
INSERT INTO `departamento` (`id`, `nome`, `descricao`)
VALUES (NULL, 'Pediatria',
        'O Departamento de Pediatria é constituído por 10 serviços nomeadamente: Cuidados Intensivos; Urgência Pediátrica; Cirurgia Pediátrica; Lactentes; Hemato- oncologia; Doenças Gerais; Doenças de Infectocontagiosas; Pneumologia; Neonatologia e Consultas Externas'),
       (NULL, 'Ortopedia',
        'É um departamento clínico vocacionado ao tratamento de patologias do aparelho particular, tendões e nervos periféricos, desde a congênita, adquirida, degenerativa, traumática, infecciosa e tumorais.'),
       (NULL, 'Medicina Física e de Reabilitação ou Fisiatria',
        'Medicina Física e de Reabilitação ou Fisiatria: Uma especialidade médica autónoma cujo objectivo reside na promoção da funcionalidade física e cognitiva, na actividade (incluindo comportamentos), na participação (incluindo a qualidade de vida) e na modificação dos factores pessoais e ambientais.');

-- Insert  Medicamentos
INSERT INTO `medicamento` (`id`, `designacao`, `precounitario`, `descricao`, `referencia`)
VALUES (NULL, 'Parcetamol', '120', 'Comprimido dor de cabeca', '00010'),
       (NULL, 'Epinefrina', '300', NULL, '000122'),
       (NULL, 'Para Tudo', '20', NULL, '090009');

-- Insert Endereco
INSERT INTO `endereco` (`id`, `provincia`, `bairro`, `descricao`)
VALUES (NULL, 'Maputo', 'Choupal', 'av.mocambique'),
       (NULL, 'xai-xai', 'CMC', 'av.julio Nherere'),
       (NULL, 'Maputo', 'Zimpeto', 'Estado nacional ');

-- Insert Pessoa
INSERT INTO `pessoa` (`id`, `nome`, `data_nascimento`, `sexo`, `endereco_id`)
VALUES (1, 'Edilson Tomas Zavala', '2000-10-05', 'M', '1'),
       (2, 'Edilson Alexandre Cuamba', '2002-11-08', 'M', '3'),
       (3, 'Carlos Macuaca ', '1960-11-08', 'M', '2'),
       (4, 'Alfredo Mikael', '2000-10-05', 'M', '1'),
       (5, 'Jorge Timoteo', '2002-11-08', 'M', '3'),
       (6, 'Carla Friedman ', '1960-11-08', 'F', '2'),
       (7, 'Terman Fildfith', '2000-10-05', 'M', '1'),
       (8, 'Orlando Gorge', '2002-11-08', 'M', '3'),
       (9, 'Milkway Olfoman ', '1960-11-08', 'M', '2');

-- Insert Paciente
INSERT INTO `paciente` (`id`, `pessoa_id`)
VALUES (NULL, '3'),
       (NULL, '2'),
       (NULL, '1');

-- Insert Receita
INSERT INTO `receita` (`id`, `data`, `descricao`)
VALUES (NULL, '2022-11-01', 'receita do paciente'),
       (NULL, '2022-11-02', NULL),
       (NULL, '2022-11-08', 'receita do paciente');

-- Insert Receita Item
INSERT INTO `receita_item` (`id`, `medicamento_id`, `dosagem`, `quantidade`, `receita_id`)
VALUES (NULL, '2', '20', '1', '2'),
       (NULL, '3', '20', '2', '1'),
       (NULL, '1', '10', '10', '3');

-- Insert Medico
INSERT INTO `medico` (`id`, `pessoa_id`, `departamento_id`)
VALUES (NULL, '6', '3'),
       (NULL, '4', '2'),
       (NULL, '5', '1');

-- Insert Recepcionista
INSERT INTO `recepcionista` (`id`, `pessoa_id`)
VALUES (NULL, '7'),
       (NULL, '8'),
       (NULL, '3');

-- Insert Consulta
INSERT INTO `consulta` (`id`, `paciente_id`, `sintomas`, `data`, `valor_pago`, `recepcionista_id`)
VALUES (NULL, '1', 'Dor de cabeca', '2022-11-08 04:20:41', '1000', '1'),
       (NULL, '2', 'Dor estomago', '2022-11-08 04:20:41', '500', '2'),
       (NULL, '3', 'Dor de cabeca', '2022-11-08 04:21:26', '900', '3');

-- Insert diagnostico
INSERT INTO `diagnostico` (`id`, `recepcionista_id`, `paciente_id`, `detalhes`, `receita_id`, `transferido`,
                           `departamento_id`, `vem_de_transferencia`, `consulta_id`, `data`, `medico_id`, `hora`)
VALUES (NULL, '1', '1', ' sintomas de dor de cabeca ', '2', b'0', '3', NULL, '1', '2022-11-08', '1', '14:13:09.117316'),
       (NULL, '2', '2', 'Dor de estomago ', '1', b'0', '2', NULL, '3', '2022-11-08', '2', '17:22:03.000000'),
       (NULL, '3', '3', 'Dor de cabeca ', '3', b'0', '1', NULL, '2', '2022-11-09', '3', '17:22:03.000000');
