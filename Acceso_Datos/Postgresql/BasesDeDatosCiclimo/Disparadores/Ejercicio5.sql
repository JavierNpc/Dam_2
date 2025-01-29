
CREATE Trigger maximo20
BEFORE INSERT on ciclista
for EACH row EXECUTE FUNCTION public.max20equip();

CREATE OR REPLACE FUNCTION public.max20equip()
RETURNS trigger
LANGUAGE 'plpgsql'
COST 100
VOLATILE NOT LEAKPROOF
AS $BODY$
BEGIN
    IF (SELECT COUNT(*) FROM ciclista WHERE nomeq=new.nomeq) > 19 THEN
        RAISE NOTICE 'No puede haber más de 20 en un equipo';
        RETURN NULL;
    ELSE
        RETURN NEW;
    END IF;
END;
$BODY$;
