CREATE or REPLACE Trigger edadCrez
BEFORE UPDATE on ciclista
for EACH row EXECUTE FUNCTION fn_edadCrez();

CREATE OR REPLACE FUNCTION fn_edadCrez()
RETURNS trigger
LANGUAGE 'plpgsql'
COST 100
VOLATILE NOT LEAKPROOF
AS $BODY$
BEGIN
    if(OLD.edad>NEW.edad) THEN
        RAISE NOTICE 'La edad solo puede ser mas alta';
        RETURN null;
    End if;
RETURN NEW;
END;
$BODY$;

