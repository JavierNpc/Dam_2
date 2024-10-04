-- Active: 1726053968786@@127.0.0.1@5432@musica

CREATE or REPLACE Trigger max_caracteres
BEFORE UPDATE of TITULO on CANCION
for EACH row EXECUTE FUNCTION max_caracteres();

CREATE or REPLACE FUNCTION max_caracteres()
RETURNS TRIGGER
LANGUAGE 'plpgsql'
AS $$
Begin 
    if (NEW.TITULO.length < 2) THEN
        RAISE NOTICE 'NO PUEDE HABER TITULOS CON MENOS DE 2 CARACTERES';
        RETURN NULL;
    END IF;
    RETURN NEW;
END;
$$;

INSERT INTO CANCION VALUES(190,'G',30)