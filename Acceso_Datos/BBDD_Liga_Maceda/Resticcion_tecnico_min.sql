--Restriccion  : en tecnico ( no se puede insertar un tecnico sin un equipo)

CREATE or REPLACE FUNCTION restricion_tecnico_fn()
RETURNS TRIGGER
LANGUAGE 'plpgsql'
AS $$
Begin 
   if EXISTS (SELECT * from contratado WHERE dni_tecnico = new.dni_tecnico ) THEN
        RETURN new;
    ELSE
        RAISE EXCEPTION 'No se puede insertar un agente sin un equipo';
        RETURN NULL;
   end if;
End;
$$;

CREATE CONSTRAINT Trigger restricion_tecnico
AFTER INSERT or UPDATE on PUBLIC.tecnico
DEFERRABLE INITIALLY DEFERRED
for EACH row EXECUTE FUNCTION restricion_tecnico_fn();

