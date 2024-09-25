CREATE or REPLACE CONSTRAINT Trigger tablaMax3
AFTER INSERT on equipo
DEFERRABLE INITIALLY DEFERRED
for EACH row EXECUTE FUNCTION fn_tablaMax3();


CREATE OR REPLACE FUNCTION fn_tablaFija()
RETURNS trigger
LANGUAGE 'plpgsql'
COST 100
VOLATILE NOT LEAKPROOF
AS $BODY$
BEGIN
    if ( (SELECT count(ciclista.nombre)FROM ciclista WHERE ciclista.nomeq = new.nomeq)< 3) THEN
        RAISE EXCEPTION 'no se puede crear un equpo con menos de 3 ciclistas';
    ELSE
        RETURN new ;
    End if;

END;
$BODY$;


CREATE OR REPLACE FUNCTION fn_tablaMax3()
RETURNS trigger
LANGUAGE 'plpgsql'
COST 100
VOLATILE NOT LEAKPROOF
AS $BODY$
BEGIN
    INSERT INTO equipo
    INSERT INTO ciclista
    INSERT INTO ciclista
    INSERT INTO ciclista
  
RETURN NULL;
COMMIT;
$BODY$;

UPDATE equipo SET director= 'Alex' WHERE nomeq= 'Artiach';
INSERT INTO equipo VALUES('Alex','Javi')


