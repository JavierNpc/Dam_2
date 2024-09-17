-- Active: 1726053968786@@127.0.0.1@5432@ciclismo

--1
SELECT codigo,tipo,color,premio FROM maillot;

--2
SELECT dorsal, nombre FROM ciclista
WHERE edad <=  25;

--3

SELECT nompuerto, altura FROM puerto
WHERE categoria = 'E';

--4

SELECT netapa FROM etapa
WHERE salida = llegada;

--5

SELECT count(dorsal) FROM ciclista

--6

SELECT count(dorsal) FROM ciclista
WHERE edad > 25;

--7

SELECT count(nomeq) FROM equipo

--8

SELECT avg(edad) FROM ciclista

--9

SELECT min(altura), max(altura) FROM puerto

--10

SELECT nompuerto, categoria FROM puerto 
    INNER JOIN ciclista USING (dorsal) 
    INNER JOIN equipo USING (nomeq) 
    INNER JOIN Llevar USING (dorsal) 
    INNER JOIN Maillot USING (codigo) 
WHERE nomeq = 'Banesto'
GROUP BY nompuerto, categoria

--11

SELECT nompuerto, etapa.netapa, km FROM puerto 
INNER JOIN Etapa USING (netapa) 

--12

SELECT nomeq, director FROM ciclista 
INNER JOIN equipo USING (nomeq)    
WHERE edad > 33
GROUP BY  nomeq, director 

--13

SELECT ciclista.nombre, maillot.color FROM ciclista 
    INNER JOIN Llevar USING (dorsal) 
    INNER JOIN Maillot USING (codigo) 
    GROUP BY ciclista.nombre, maillot.color
ORDER BY ciclista.nombre

--14 !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

SELECT DISTINCT  ciclista.nombre, etapa.netapa FROM ciclista
INNER JOIN etapa USING ( dorsal)
INNER JOIN Llevar USING (dorsal)
INNER JOIN maillot USING (codigo)
    WHERE color = 'Amarillo' and llevar.codigo = maillot.codigo


--15

SELECT e1.netapa from etapa e1 , etapa e2
WHERE e1.netapa-1 = e2.netapa AND e1.salida <> e2.llegada



--(Subconsultas)

--16


SELECT DISTINCT etapa.netapa, salida FROM etapa 
WHERE netapa not in (SELECT netapa from puerto )

--17

SELECT  avg(edad) FROM ciclista 
WHERE dorsal in (SELECT dorsal from etapa) 

--18

SELECT  nompuerto FROM puerto
WHERE altura > (SELECT avg(altura) from puerto) 

--19

SELECT  salida, llegada FROM etapa INNER JOIN puerto USING (netapa)
WHERE pendiente = (SELECT max(pendiente) from puerto) 

    
--20
SELECT ciclista.dorsal, nombre FROM ciclista INNER JOIN puerto USING (dorsal)
WHERE ciclista.dorsal = puerto.dorsal and altura = (SELECT max(altura) from puerto)

--21

SELECT nombre FROM ciclista 
WHERE edad = (SELECT min(edad) from ciclista)


--22

SELECT nombre FROM ciclista INNER JOIN etapa USING (dorsal)
WHERE edad = (SELECT min(edad) from ciclista c1 , etapa e1 WHERE c1.dorsal = e1.dorsal) 


--23 

SELECT dorsal, nombre FROM ciclista as c
WHERE (SELECT count(*) from puerto WHERE c.dorsal = puerto.dorsal)>1


--24

SELECT DISTINCT p1.netapa from puerto p1
WHERE not EXISTS (SELECT * from puerto p2 WHERE p2.altura <=700 and p1.netapa = p2.netapa)

--25 !!!

SELECT DISTINCT nomeq, director from equipo INNER JOIN ciclista USING (nomeq)
WHERE dorsal in (SELECT DISTINCT dorsal from ciclista WHERE edad > 25)

--26

Select DISTINCT c1.dorsal, nombre from ciclista c1 , etapa e1
WHERE c1.dorsal = e1.dorsal AND EXISTS (SELECT netapa FROM etapa WHERE km > 170 and c1.dorsal = etapa.dorsal)
ORDER BY c1.dorsal, nombre

--27

SELECT DISTINCT nombre from ciclista c1, etapa e1 , puerto p1
WHERE c1.dorsal = e1.dorsal and e1.netapa = p1.netapa and
not EXISTS (SELECT dorsal FROM puerto WHERE e1.netapa = puerto.netapa and c1.dorsal <> puerto.dorsal)

--28 !!

SELECT DISTINCT e1.nomeq FROM equipo e1 , ciclista c1 WHERE dorsal in ((SELECT dorsal FROM llevar INNER JOIN maillot USING (codigo) WHERE c1.dorsal = llevar.dorsal ) OR 
(SELECT dorsal from puerto WHERE c1.dorsal = puerto.dorsal) )


--29 Obtener el código y el color de aquellos maillots que sólo han sido llevados por ciclistas de un mismo equipo.

SELECT DISTINCT m1.codigo, color from maillot m1 WHERE EXISTS (SELECT DISTINCT e1.nomeq, nombre from equipo e1, ciclista c1 ,llevar l1 , maillot WHERE m1.color = maillot.color )
)

SELECT DISTINCT e1.nomeq, nombre from equipo e1, ciclista c1 WHERE e1.nomeq=c1.nomeq and e1.nomeq = (SELECT nomeq FROM ciclista WHERE c1.nombre = ciclista.nombre)
