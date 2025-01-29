
CREATE or REPLACE FUNCTION fn_transaccion_casa_chalet_a_imueble()
RETURNS TRIGGER
LANGUAGE 'plpgsql'
AS $$
Begin 
   if EXISTS (SELECT dni_agente from venta WHERE venta.dni_agente = new.dni_agente) THEN
        RETURN new;
    ELSE
        RAISE EXCEPTION 'No se puede insertar un agente sin una venta';
        RETURN NULL;
   end if;
End;
$$;

CREATE CONSTRAINT Trigger transaccion_casa_chalet_a_imueble
AFTER INSERT 
DEFERRABLE INITIALLY DEFERRED
for EACH row EXECUTE FUNCTION fn_transaccion_casa_chalet_a_imueble();
