CREATE or REPLACE FUNCTION fn_restricion_borrado_y_creado_discos()
RETURNS TRIGGER
LANGUAGE 'plpgsql'
AS $$
Begin 
    RAISE NOTICE 'No se pude borrar o insertar el esta tabla';
    RETURN null;

End;
$$;


CREATE or REPLACE Trigger restricion_borrado_y_creado_discos
BEFORE INSERT or DELETE on PUBLIC.disco for EACH row
EXECUTE FUNCTION fn_restricion_borrado_y_creado_discos();
