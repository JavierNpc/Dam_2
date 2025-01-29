-- Restriccion numero equipos

CREATE or REPLACE FUNCTION numequipos_liga_fn()
RETURNS TRIGGER
LANGUAGE 'plpgsql'
AS $body$
Begin 

    update liga set  numequipos = numequipos+1 where nombre_pais = new.nombre_pais AND nivel = new.nivel  ;

    RETURN new;
   
End;
$body$;

CREATE or REPLACE Trigger numequipos_liga
AFTER INSERT on PUBLIC.equipo
for EACH row EXECUTE FUNCTION numequipos_liga_fn();

