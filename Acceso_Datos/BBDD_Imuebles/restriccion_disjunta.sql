-- Active: 1726053968786@@127.0.0.1@5432@inmuebles

--Borrado casa

CREATE or REPLACE FUNCTION fn_no_borrado_casa()
RETURNS TRIGGER
LANGUAGE 'plpgsql'
AS $$
Begin 
    if EXISTS (SELECT id_inmueble FROM inmueble WHERE OLD.id_inmueble = inmueble.id_inmueble ) THEN
        RAISE NOTICE 'No puedes eliminar una casa';
        RETURN null;
    ELSE
        RETURN OLD;
    END IF;
    
End;
$$;

CREATE or REPLACE Trigger no_borrado_casa
BEFORE DELETE on casa 
for EACH row EXECUTE FUNCTION fn_no_borrado_casa();

--Borrado CHALET

CREATE or REPLACE FUNCTION fn_no_borrado_chalet()
RETURNS TRIGGER
LANGUAGE 'plpgsql'
AS $$
Begin 
    if EXISTS (SELECT id_inmueble FROM inmueble WHERE OLD.id_inmueble = inmueble.id_inmueble ) THEN
        RAISE NOTICE 'No puedes eliminar una chalet';
        RETURN null;
    ELSE
        RETURN OLD;
    END IF;
    
End;
$$;

CREATE or REPLACE Trigger no_borrado_chalet
BEFORE DELETE on PUBLIC.chalet 
for EACH row EXECUTE FUNCTION fn_no_borrado_chalet();

-- SENTNCIAS DE BORRADO---------------------

        DELETE FROM "chalet" WHERE "id_inmueble"='987654321';
        DELETE FROM "inmueble" WHERE "id_inmueble"='987654321';

-- SENTENCIAS DE CREADO ------------------

        INSERT INTO chalet VALUES('987654321',500);
        INSERT INTO casa VALUES('123456789','Valencia');
        INSERT INTO inmueble VALUES('123456789','calle 1',30);


--Resriccion total de inmuebles

CREATE or REPLACE FUNCTION fn_restriccion_disjunta_casa()
RETURNS TRIGGER
LANGUAGE 'plpgsql'
AS $$
Begin 
   if EXISTS (SELECT id_inmueble from chalet WHERE id_inmueble = new.id_inmueble) THEN
        RAISE notice 'No se puede insertar en casa porque ya exixte en chalet';
        RETURN NULL;
   end if;
   RETURN new;
End;
$$;

CREATE or REPLACE Trigger restriccion_disjunta_casa
BEFORE INSERT or UPDATE on PUBLIC.casa
for EACH row EXECUTE FUNCTION fn_restriccion_disjunta_casa();


CREATE or REPLACE FUNCTION fn_restriccion_disjunta_chalet()
RETURNS TRIGGER
LANGUAGE 'plpgsql'
AS $$
Begin 
   if EXISTS (SELECT id_inmueble from casa WHERE id_inmueble = new.id_inmueble) THEN
        RAISE notice 'No se puede insertar en chalet porque ya exixte en casa';
        RETURN NULL;
   end if;
   RETURN new;
End;
$$;
CREATE or REPLACE Trigger restriccion_disjunta_chalet
BEFORE INSERT or UPDATE on PUBLIC.chalet
for EACH row EXECUTE FUNCTION fn_restriccion_disjunta_chalet();

-- Restriccion de update









