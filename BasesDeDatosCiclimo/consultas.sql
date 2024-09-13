-- Active: 1726053968786@@127.0.0.1@5432@ciclismo

--1
SELECT codigo, tipo , color , premio FROM maillot;

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
               
   
   

--20
SELECT ciclista.dorsal, nombre FROM ciclista INNER JOIN puerto USING (dorsal)
WHERE ciclista.dorsal = puerto.dorsal and altura = (SELECT max(altura) from puerto)

--23

SELECT dorsal, nombre FROM ciclista as c
WHERE (SELECT count(*) from puerto WHERE c.dorsal = puerto.dorsal)>1


--24

SELECT DISTINCT p1.netapa from puerto p1
WHERE not EXISTS (SELECT * from puerto p2 WHERE p2.altura <=700 and p1.netapa = p2.netapa)

--25





