
-- 6. Crea una restricción que evite que se modifique el nombre de un disco.

--Update

CREATE or REPLACE FUNCTION fn_nombre_modificado_update()
RETURNS TRIGGER
LANGUAGE 'plpgsql'
AS $$
Begin 
    RAISE NOTICE 'No se pude modificar el nobre de un artista';
    RETURN null;

End;
$$;


CREATE or REPLACE Trigger nombre_modificado_update 
BEFORE UPDATE of nombre on PUBLIC.artista for EACH row
EXECUTE FUNCTION fn_nombre_modificado_update();


UPDATE artista SET nombre = 'papu' WHERE dni = '1111111111'