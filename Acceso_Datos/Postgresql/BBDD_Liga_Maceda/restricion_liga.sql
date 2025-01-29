--RESTRICION 5: No pueden eliminarse paises que tengan ligas
CREATE or REPLACE FUNCTION rs_liga_fn()
RETURNS TRIGGER
LANGUAGE 'plpgsql'
AS $body$
Begin 
   if EXISTS (SELECT * FROM liga WHERE nombre_pais = OLD.nombre_pais) THEN
      RAISE NOTICE 'No se puede eliminar este pais porque tiene asignada una liga';
      RETURN NULL;
   end if;
   
   RETURN OLD;
   
End;
$body$;

CREATE or REPLACE Trigger rs_liga
BEFORE DELETE on PUBLIC.pais
for EACH row EXECUTE FUNCTION rs_liga_fn();

