
-- Restriccion insertar en equiposborrados despues delete en equipos

CREATE or REPLACE FUNCTION ins_equipobrrado_fn()
RETURNS TRIGGER
LANGUAGE 'plpgsql'
AS $body$
Begin 

    insert into equiposborrados (nombre_equipo,fecha_borrado,localidad,anyofundacion) values (old.nombre_equipo, now(), OLD.localidad, old.anyofundacion);
    RETURN new;
   
End;
$body$;

CREATE or REPLACE Trigger ins_equipobrrado
AFTER DELETE on PUBLIC.equipo
for EACH row EXECUTE FUNCTION ins_equipobrrado_fn();

