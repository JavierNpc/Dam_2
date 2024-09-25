CREATE or REPLACE Trigger tablaFija
BEFORE UPDATE or INSERT on equipo
for EACH row EXECUTE FUNCTION fn_tablaFija();

CREATE OR REPLACE FUNCTION fn_tablaFija()
RETURNS trigger
LANGUAGE 'plpgsql'
COST 100
VOLATILE NOT LEAKPROOF
AS $BODY$
BEGIN
    RAISE EXCEPTION 'No se puede actualizar o insertar valores';
END;
$BODY$;

UPDATE equipo SET director= 'Alex' WHERE nomeq= 'Artiach';
INSERT INTO equipo VALUES('Alex','Javi')

DROP FUNCTION fn_tablaFija() CASCADE