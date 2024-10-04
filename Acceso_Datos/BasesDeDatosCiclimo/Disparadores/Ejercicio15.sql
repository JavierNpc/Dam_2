CREATE or REPLACE Trigger anyos_cilcista
AFTER UPDATE on ciclista
for EACH row EXECUTE FUNCTION fn_anyos_cilcista();

CREATE OR REPLACE FUNCTION fn_anyos_cilcista()
RETURNS trigger
LANGUAGE 'plpgsql'
COST 100
VOLATILE NOT LEAKPROOF
AS $BODY$
BEGIN

    UPDATE equipo set nºciclistas_equipo = nºciclistas_equipo+1  WHERE nomeq = NEW.nomeq;
    UPDATE equipo set nºciclistas_equipo = nºciclistas_equipo-1  WHERE nomeq = OLD.nomeq;

RETURN NEW;
END;
$BODY$; 

SELECT c1.nombre, count(l1.codigo) FROM ciclista c1 , llevar l1 , maillot m1 WHERE
c1.dorsal=l1.dorsal and l1.codigo=m1.codigo 
GROUP BY c1.nombre

SELECT c1.nombre, count(e1.netapa) FROM ciclista c1 , llevar l1 , etapa e1 WHERE
c1.dorsal=l1.dorsal and c1.dorsal=e1.dorsal and l1.netapa=e1.netapa
GROUP BY c1.nombre