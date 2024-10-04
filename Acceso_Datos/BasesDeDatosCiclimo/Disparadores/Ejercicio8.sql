
--funcion del equipo
CREATE CONSTRAINT Trigger fn_max3equipo
AFTER INSERT on equipo
DEFERRABLE INITIALLY DEFERRED
for EACH row EXECUTE FUNCTION fn_max3equipo();

CREATE OR REPLACE FUNCTION fn_max3equipo()
RETURNS trigger
LANGUAGE 'plpgsql'
COST 100
VOLATILE NOT LEAKPROOF
AS $BODY$
BEGIN
    if ( (SELECT count(*) FROM ciclista WHERE ciclista.nomeq = new.nomeq)< 3) THEN
        RAISE EXCEPTION 'No se puede crear un equpo con menos de 3 ciclistas';
        RETURN NULL;
    ELSE
        RETURN new ;
    End if;
END;
$BODY$;

--funcion de cilista (Tiger delete)
CREATE or REPLACE Trigger max3ciclicta_delete
BEFORE DELETE on ciclista
for EACH row EXECUTE FUNCTION fn_max3ciclicta_delete();

CREATE OR REPLACE FUNCTION fn_max3ciclicta_delete()
RETURNS trigger
LANGUAGE 'plpgsql'
COST 100
VOLATILE NOT LEAKPROOF
AS $BODY$
BEGIN

    if ( (SELECT count(*) FROM ciclista WHERE ciclista.nomeq = Old.nomeq)< 50) THEN
        RAISE notice 'No se puede crear un equpo con menos de 3 ciclistas';
        RETURN NULL;
    ELSE
        RETURN new ;
    End if; 

END;
$BODY$;

--funcion de cilista (Tiger update)

CREATE or REPLACE Trigger max3ciclicta_update
BEFORE UPDATE of nomeq on ciclista
for EACH row EXECUTE FUNCTION fn_max3ciclicta_update();

CREATE OR REPLACE FUNCTION fn_max3ciclicta_update()
RETURNS trigger
LANGUAGE 'plpgsql'
COST 100
VOLATILE NOT LEAKPROOF
AS $BODY$
BEGIN

    if ( (SELECT count(*) FROM ciclista WHERE ciclista.nomeq = old.nomeq)< 3) THEN
        RAISE notice 'No se puede crear un equpo con menos de 3 ciclistas';
        RETURN NULL;
    ELSE
        RETURN new ;
    End if; 

END;
$BODY$;