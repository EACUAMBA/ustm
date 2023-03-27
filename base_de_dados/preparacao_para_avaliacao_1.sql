-- CREATE DATABSE
CREATE DATABASE preparacao_para_avaliacao_1;

-- USE CREATED DATABASE
USE preparacao_para_avaliacao_1;

-- CREATE TABLE
CREATE TABLE employee
(
    id        INT NOT NULL,
    pay_month INT,
    salary    NUMERIC
);

-- INSERT DATA
INSERT INTO preparacao_para_avaliacao_1.dbo.employee (id, pay_month, salary)
VALUES (1, 1, 20);
INSERT INTO preparacao_para_avaliacao_1.dbo.employee (id, pay_month, salary)
VALUES (2, 1, 20);
INSERT INTO preparacao_para_avaliacao_1.dbo.employee (id, pay_month, salary)
VALUES (1, 2, 30);
INSERT INTO preparacao_para_avaliacao_1.dbo.employee (id, pay_month, salary)
VALUES (2, 2, 30);
INSERT INTO preparacao_para_avaliacao_1.dbo.employee (id, pay_month, salary)
VALUES (3, 2, 40);
INSERT INTO preparacao_para_avaliacao_1.dbo.employee (id, pay_month, salary)
VALUES (1, 3, 40);
INSERT INTO preparacao_para_avaliacao_1.dbo.employee (id, pay_month, salary)
VALUES (3, 3, 60);
INSERT INTO preparacao_para_avaliacao_1.dbo.employee (id, pay_month, salary)
VALUES (1, 4, 60);
INSERT INTO preparacao_para_avaliacao_1.dbo.employee (id, pay_month, salary)
VALUES (3, 4, 70);

--SELECT DATA
SELECT e.id,
       e.pay_month,
       e.salary,
       (
           SELECT SUM(em.salary)
           FROM employee em
           WHERE em.pay_month <= e.pay_month
             AND em.id = e.id
       ) AS comulative_sum

FROM employee e

WHERE e.pay_month IN (
    SELECT TOP (3) emp.pay_month
    FROM employee emp
    WHERE id = e.id
    ORDER BY emp.id ASC, emp.pay_month ASC)

ORDER BY e.id ASC, e.pay_month ASC
;

-- Exercicio 2 - Visitantes Mobile e da Web

-- CREATE TABLE
CREATE TABLE mobile
(
    user_id  INT,
    page_url CHAR
);

-- INSERT DATA
INSERT INTO preparacao_para_avaliacao_1.dbo.mobile (user_id, page_url)
VALUES (1, N'A');
INSERT INTO preparacao_para_avaliacao_1.dbo.mobile (user_id, page_url)
VALUES (2, N'B');
INSERT INTO preparacao_para_avaliacao_1.dbo.mobile (user_id, page_url)
VALUES (3, N'C');
INSERT INTO preparacao_para_avaliacao_1.dbo.mobile (user_id, page_url)
VALUES (4, N'A');
INSERT INTO preparacao_para_avaliacao_1.dbo.mobile (user_id, page_url)
VALUES (9, N'B');
INSERT INTO preparacao_para_avaliacao_1.dbo.mobile (user_id, page_url)
VALUES (2, N'C');
INSERT INTO preparacao_para_avaliacao_1.dbo.mobile (user_id, page_url)
VALUES (10, N'B');

-- CREATE TABLE
CREATE TABLE web
(
    user_id  INT,
    page_url CHAR
);

-- INSERT DATA
INSERT INTO preparacao_para_avaliacao_1.dbo.web (user_id, page_url)
VALUES (6, N'A');
INSERT INTO preparacao_para_avaliacao_1.dbo.web (user_id, page_url)
VALUES (2, N'B');
INSERT INTO preparacao_para_avaliacao_1.dbo.web (user_id, page_url)
VALUES (3, N'C');
INSERT INTO preparacao_para_avaliacao_1.dbo.web (user_id, page_url)
VALUES (7, N'A');
INSERT INTO preparacao_para_avaliacao_1.dbo.web (user_id, page_url)
VALUES (4, N'B');
INSERT INTO preparacao_para_avaliacao_1.dbo.web (user_id, page_url)
VALUES (8, N'C');
INSERT INTO preparacao_para_avaliacao_1.dbo.web (user_id, page_url)
VALUES (5, N'B');


-- SELECT DATA
SELECT (CAST(COUNT(visitas.user_id) AS NUMERIC(4, 2)) /
        (SELECT COUNT(m.user_id) FROM mobile m WHERE m.user_id NOT IN (SELECT w.user_id FROM web w)))   AS mobile,
       (CAST(COUNT(visitas.user_id) AS NUMERIC(4, 2)) /
        (SELECT COUNT(w.user_id) FROM web w WHERE w.user_id NOT IN (SELECT m.user_id FROM mobile m)))   AS web,
       (CAST(COUNT(visitas.user_id) AS NUMERIC(4, 2)) /
        ((SELECT COUNT(m.user_id) FROM mobile m WHERE m.user_id NOT IN (SELECT w.user_id FROM web w)) +
         (SELECT COUNT(w.user_id) FROM web w WHERE w.user_id NOT IN (SELECT m.user_id FROM mobile m)))) AS ambos

FROM (
         SELECT user_id, page_url
         FROM mobile

         UNION ALL

         SELECT user_id, page_url
         FROM web
     ) AS visitas;

-- Exercicio 1 - Recomendação de conteudo

-- CREATE TABLE
CREATE TABLE friends
(
    user_id INT,
    friend  INT
)

-- INSERT DATA
INSERT INTO preparacao_para_avaliacao_1.dbo.friends (user_id, friend)
VALUES (1, 2);
INSERT INTO preparacao_para_avaliacao_1.dbo.friends (user_id, friend)
VALUES (1, 3);
INSERT INTO preparacao_para_avaliacao_1.dbo.friends (user_id, friend)
VALUES (1, 4);
INSERT INTO preparacao_para_avaliacao_1.dbo.friends (user_id, friend)
VALUES (2, 1);
INSERT INTO preparacao_para_avaliacao_1.dbo.friends (user_id, friend)
VALUES (3, 1);
INSERT INTO preparacao_para_avaliacao_1.dbo.friends (user_id, friend)
VALUES (3, 4);
INSERT INTO preparacao_para_avaliacao_1.dbo.friends (user_id, friend)
VALUES (4, 1);
INSERT INTO preparacao_para_avaliacao_1.dbo.friends (user_id, friend)
VALUES (4, 3);


-- CREATE TABLE
CREATE TABLE likes
(
    user_id    INT,
    page_likes CHAR
)

-- INSERT DATA
INSERT INTO preparacao_para_avaliacao_1.dbo.likes (user_id, page_likes)
VALUES (1, N'A');
INSERT INTO preparacao_para_avaliacao_1.dbo.likes (user_id, page_likes)
VALUES (1, N'B');
INSERT INTO preparacao_para_avaliacao_1.dbo.likes (user_id, page_likes)
VALUES (1, N'C');
INSERT INTO preparacao_para_avaliacao_1.dbo.likes (user_id, page_likes)
VALUES (2, N'A');
INSERT INTO preparacao_para_avaliacao_1.dbo.likes (user_id, page_likes)
VALUES (3, N'B');
INSERT INTO preparacao_para_avaliacao_1.dbo.likes (user_id, page_likes)
VALUES (3, N'C');
INSERT INTO preparacao_para_avaliacao_1.dbo.likes (user_id, page_likes)
VALUES (4, N'B');


-- SELECT DATA
SELECT DISTINCT f.user_id               AS utilizador,
                myfriend.user_id        AS amigo_do_utilizador,
                friendslikes.page_likes AS recomenda

FROM friends f
         INNER JOIN friends myfriend ON myfriend.user_id = f.friend
         INNER JOIN likes friendslikes ON friendslikes.user_id = myfriend.user_id

WHERE friendslikes.page_likes NOT IN (SELECT mylikes.page_likes FROM likes mylikes WHERE mylikes.user_id = f.user_id)

ORDER BY f.user_id ASC



