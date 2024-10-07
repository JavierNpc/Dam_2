-- Active: 1726053968786@@127.0.0.1@5432@musica


CREATE CONSTRAINT Trigger artista_grupo
AFTER INSERT on artista
DEFERRABLE INITIALLY DEFERRED
for EACH row EXECUTE FUNCTION fn_artista_grupo();
  
CREATE or REPLACE FUNCTION fn_artista_grupo()
RETURNS TRIGGER
LANGUAGE 'plpgsql'
AS $$
Begin 
    if (SELECT count(cod) FROM pertence WHERE dni = (SELECT dni FROM artista WHERE dni = new.dni))<1 THEN
        RAISE EXCEPTION 'no puede haber un artista sin grupo';
    end if;
return NEW;
End;
$$;

BEGIN;
	INSERT into artista VALUES('1234567890','javier');
	INSERT INTO pertence VALUE ('1234567890','3','flauta');
COMMIT;

ALTER table pertence ADD constraint fk_artista Foreign Key (dni) REFERENCES artista(dni); 
ALTER table pertence ADD constraint fk_grupo Foreign Key (cod) REFERENCES grupo(cod); 

