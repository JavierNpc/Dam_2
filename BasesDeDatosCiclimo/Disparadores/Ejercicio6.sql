CREATE or REPLACE Trigger nombrefijo
BEFORE UPDATE on ciclista
for EACH row EXECUTE FUNCTION fn_nombrefijo();

CREATE OR REPLACE FUNCTION fn_nombrefijo()
RETURNS trigger
LANGUAGE 'plpgsql'
COST 100
VOLATILE NOT LEAKPROOF
AS $BODY$
BEGIN
    if(OLD.nombre <> new.nombre)THEN
        RAISE NOTICE "El nombre no se puede cambiar"
        new.nombre = OLD.nombre;
    end if;   
RETURN new;
END;
$BODY$;

UPDATE ciclista SET nombre= 'Alex', edad = 19 WHERE dorsal= 1;