-- Active: 1726053968786@@127.0.0.1@5432@inmuebles

--Borrado

CREATE or REPLACE FUNCTION fn_no_borrado_casa()
RETURNS TRIGGER
LANGUAGE 'plpgsql'
AS $$
Begin 
    RAISE NOTICE 'No puedes eliminar una casa';
    RETURN null;

End;
$$;



CREATE or REPLACE Trigger no_borrado_casa
BEFORE DELETE on casa 
for EACH row EXECUTE FUNCTION fn_no_borrado_casa();


--Resriccion total  de inmuebles
CREATE or REPLACE FUNCTION fn_restriccion_total_casa()
RETURNS TRIGGER
LANGUAGE 'plpgsql'
AS $$
Begin 
   if EXISTS (SELECT id_inmueble from casa WHERE id_inmueble = new.id_inmueble) THEN
        RAISE notice 'No se puede insertar en casa porque ya exixte en chalet';
   end if;

End;
$$;

CREATE or REPLACE Trigger restriccion_total_casa
BEFORE INSERT on PUBLIC.inmueble
for EACH row EXECUTE FUNCTION fn_restriccion_total_casa();

