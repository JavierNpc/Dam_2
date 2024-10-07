-- Active: 1726053968786@@127.0.0.1@5432@musica

CREATE or REPLACE Trigger max_caracteres_insert
AFTER INSERT on CANCION
for EACH row EXECUTE FUNCTION max_caracteres_insert();

CREATE or REPLACE FUNCTION max_caracteres_insert()
RETURNS TRIGGER
LANGUAGE 'plpgsql'
AS $$
Begin 
    if (SELECT length(titulo) FROM cancion WHERE cancion.cod = new.cod)< 2 THEN
        RAISE EXCEPTION 'NO PUEDE HABER TITULOS CON MENOS DE 2 CARACTERES'; 
    END IF;
    RETURN NEW;
END;
$$;


--*////////////////////////

CREATE or REPLACE Trigger max_caracteres_update
AFTER UPDATE of titulo on CANCION
for EACH row EXECUTE FUNCTION max_caracteres_update();
  
CREATE or REPLACE FUNCTION max_caracteres_update()
RETURNS TRIGGER
LANGUAGE 'plpgsql'
AS $$
Begin 
    if (SELECT length(titulo) FROM cancion WHERE cancion.cod = new.cod)< 2 THEN
        RAISE EXCEPTION 'NO PUEDE HABER TITULOS CON MENOS DE 2 CARACTERES'; 
    END IF;
    RETURN NEW;
END;
$$;

--*////////////////////////

INSERT INTO CANCION VALUES(190,'R',30)

UPDATE cancion set titulo = 'B' WHERE cod = 20

SELECT titulo ,length(titulo) from cancion
