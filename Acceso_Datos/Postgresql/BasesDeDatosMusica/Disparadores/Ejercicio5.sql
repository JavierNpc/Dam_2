SELECT cod, count(dni) FROM pertence GROUP BY cod ORDER BY cod;

CREATE
or REPLACE Trigger max_artistas BEFORE INSERT on pertence for EACH row
EXECUTE FUNCTION fn_max_artistas ();

CREATE or REPLACE FUNCTION fn_max_artistas()
RETURNS TRIGGER
LANGUAGE 'plpgsql'
AS $$
DECLARE
    sentencia INTEGER = (SELECT count(*) FROM pertence WHERE new.cod = cod GROUP BY cod );
Begin 
    if sentencia >= 4 THEN
        RAISE NOTICE 'No puede haber mas de 4 artisatas por grupo';
        RETURN NULL;
    ELSE
        RETURN NEW;
    end if;
End;
$$;

INSERT INTO pertence VALUES('1234567890',1,'saxo')