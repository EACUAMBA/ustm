
-- Criando um logon para a minha instancia SQLServer, utilizando a autenticação do windows.
-- Funcionaria se a minha instancia não estivesse em um container linux
CREATE LOGIN [EACUAMBA-HP\eacuamba]
    FROM WINDOWS
WITH DEFAULT_DATABASE = [CAR_WASH];
GO;

CREATE LOGIN basededados
    WITH PASSWORD = 'B4s3.d3.d4d0$';
GO;


-- Criando um utilizador associado ao logon  [EACUAMBA-HP\eacuamba] para utilizar a base de dados
use car_wash;
go;

create user [basededados] for login [basededados];
go;

-- garantindo permissão de fazer consultas a tabela especifica
Grant select on automovel to [basededados];
go;

-- Incrivel fogo!