-- Active: 1726053968786@@127.0.0.1@5432@ciclismo
CREATE or REPLACE Trigger anyos_cilcista
AFTER UPDATE on ciclista
for EACH row EXECUTE FUNCTION fn_anyos_cilcista();

CREATE OR REPLACE FUNCTION fn_anyos_cilcista()
RETURNS trigger
LANGUAGE 'plpgsql'
COST 100
VOLATILE NOT LEAKPROOF
AS $BODY$
DECLARE
    var_cuenta integer;
    var_dorsal integer;
    cur cursor for SELECT dorsal, count(*) cuenta from llevar 
        GROUP BY dorsal, codigo order BY dorsal desc;


BEGIN

OPEN cur;
    LOOP 
        FETCH cur.cuenta into var_cuenta;
        Exit WHERE NOT FOUND;
            UPDATE cilcista set edad = edad + 1000 WHERE dorsal = var_cuenta;
    end LOOP;
CLOSE cur;
        


RETURN NEW;
END;
$BODY$; 



-- no han ganada ninguna
SELECT c1.nombre, dorsal FROM ciclista c1 WHERE dorsal
not in (SELECT DORSAL FROM ETAPA WHERE etapa.DORSAL=c1.DORSAL);

-- han ganada mas de 1
SELECT c1.nombre, dorsal FROM ciclista c1 WHERE
(SELECT count(*) FROM ETAPA WHERE etapa.DORSAL=c1.DORSAL)>1;

--llevado mas veces algun maillot


--levado mas veces cualquier maillorts que nadie

UPDATE ciclista set edad = edad +1000
WHERE dorsal >= all in 
(SELECT dorasl from llevar 
GROUP BY dorsal, codigo 
HAVING count(*) FROM llevar GROUP BY dorsal, codigo)





