-- Consultas simples
-- Lista (nome, sexo, data de nascimento e idade) de todos os pacientes;
select pessoa.nome, pessoa.sexo, pessoa.data_nascimento, (curdate() - pessoa.data_nascimento)
from paciente p
         inner join pessoa on pessoa.id = p.pessoa_id;


	-- Lista os diagnosticos e nome de todos os pacientes
select pess.nome, d.*
from diagnostico d
         inner join paciente pac on pac.id = d.paciente_id
         inner join pessoa pess on pess.id = pac.pessoa_id;

-- Lista os medicos e os diagnoticos com as receitas que ja realizou
select mpess.nome as  medico, diag.data, pacpess.nome as paciente, diag.detalhes, rec.descricao, med.designacao
from medico m
         inner join diagnostico diag on diag.medico_id = m.id
         inner join pessoa mpess on mpess.id = m.pessoa_id

         inner join receita rec on rec.id = diag.receita_id
         left join receita_item recitem on recitem.receita_id = rec.id
         inner join medicamento med on med.id = recitem.medicamento_id

         inner join paciente pac on diag.paciente_id = pac.id
         inner join pessoa pacpess on pacpess.id = pac.pessoa_id
;
-- Lista os pacientes que fizeram mais de 10 consultas dentro de 365 dias
select distinct (pac.id), pacpess.nome from consulta c

inner join paciente pac on c.paciente_id = pac.id
         inner join pessoa pacpess on pacpess.id = pac.pessoa_id

where c.data > (curdate() - 365) 
  and c.data < (curdate()) 
  and (select (count(*) >10) from consulta con where con.paciente_id = pac.id);

-- alterando uma tabela
alter table pessoa
    add coluna_adiconada varchar(45);
    
-- Actualizando um registo
    UPDATE endereco
set provincia='GAZA',
    bairro='Kumbeza',
    descricao='av. mocambique'
where id = 3;

-- Faça consultas aninhadas com que envolvam funções de agregação, nomedamente:
-- COUNT()
-- Conta pacientes
select count(paciente.id)
from paciente;

-- DISTINCT()
-- Lista consultas de pacientes dieferentes.
select distinct(p.id), c.* from consulta c
inner join paciente p on c.paciente_id = p.id;

-- SUM()
select distinct (p.id),
                (select sum(cons.valor_pago) from consulta cons where cons.paciente_id = p.id) as valorGanho,
                p2.nome
from paciente p
         inner join pessoa p2
                    on p.pessoa_id = p2.id;

-- GROUP BY()
-- conta as consultas que cada recepcionista fez;
select nome, count(c.id) from consulta c
inner join recepcionista r on r.id = c.recepcionista_id
inner join pessoa p on r.pessoa_id = p.id
group by r.id;


