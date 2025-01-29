-- Active: 1726053968786@@127.0.0.1@5432@musica


CREATE CONSTRAINT Trigger artista_grupo
AFTER INSERT on PUBLIC.artista
DEFERRABLE INITIALLY DEFERRED
for EACH row EXECUTE FUNCTION fn_artista_grupo();
  
CREATE or REPLACE FUNCTION fn_artista_grupo()
RETURNS TRIGGER
LANGUAGE 'plpgsql'
AS $$
Begin 
    IF NOT EXISTS (SELECT dni FROM pertence WHERE dni = New.dni) THEN
        RAISE EXCEPTION 'No puede haber un artista sin grupo';
        RETURN NULL;
    ELSE
        RETURN NEW;
    end if;
End;
$$;

BEGIN;
    INSERT into artista VALUES('1234567890','javier');
    INSERT INTO pertence VALUES('1234567890',3,'flauta');
COMMIT;


ROLLBACK;




-- if NEW.dni not in (SELECT dni FROM pertecence) THEN ...

-- if not exists (SELECT dni FROM pertecence WHERE NEW.dni = dni) THEN ...