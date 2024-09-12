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

SELECT  ciclista.nombre, etapa.netapa, color FROM maillot
    INNER JOIN llevar on llevar.codigo = maillot.codigo
    INNER JOIN etapa on etapa.netapa = llevar.netapa
    INNER JOIN ciclista on ciclista.dorsal = llevar.dorsal
    WHERE color = 'Amarillo' 
GROUP BY ciclista.nombre , etapa.netapa ,color

--15

SELECT * FROM etapa 
WHERE llegada != salida


 

















