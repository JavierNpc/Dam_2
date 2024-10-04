CREATE or REPLACE Trigger edad_update
AFTER UPDATE on ciclista
for EACH row EXECUTE FUNCTION fn_edad_update();

CREATE OR REPLACE FUNCTION fn_edad_update()
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

--****************************

CREATE or REPLACE Trigger edadCrez
AFTER INSERT on ciclista
for EACH row EXECUTE FUNCTION fn_edadCrez();

CREATE OR REPLACE FUNCTION fn_edadCrez()
RETURNS trigger
LANGUAGE 'plpgsql'
COST 100
VOLATILE NOT LEAKPROOF
AS $BODY$
BEGIN
    
    UPDATE equipo set nºciclistas_equipo = nºciclistas_equipo+1  WHERE nomeq = new.nomeq;
  
RETURN NEW;
END;
$BODY$; 

--*********************


CREATE or REPLACE Trigger edadDesc
AFTER DELETE on ciclista
for EACH row EXECUTE FUNCTION fn_edadDesc();

CREATE OR REPLACE FUNCTION fn_edadDesc()
RETURNS trigger
LANGUAGE 'plpgsql'
COST 100
VOLATILE NOT LEAKPROOF
AS $BODY$
BEGIN
    
   UPDATE equipo set nºciclistas_equipo = nºciclistas_equipo-1  WHERE nomeq = OLD.nomeq;

RETURN NEW;
END;
$BODY$; 




SELECT count(*) as num ,e1.nomeq FROM equipo e1, ciclista c1 
WHERE e1.nomeq = c1.nomeq 
GROUP BY e1.nomeq
ORDER BY e1.nomeq

INSERT INTO ciclista VALUES (104,'javi',25,'Banesto')

DELETE FROM "ciclista" WHERE "dorsal"=104

 UPDATE ciclista set nomeq = 'PDM'  WHERE dorsal = 1;
 UPDATE ciclista set nomeq = 'Banesto'  WHERE dorsal = 1;
