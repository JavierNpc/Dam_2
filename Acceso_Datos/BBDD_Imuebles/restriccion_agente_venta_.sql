
--Restriccion en agente

CREATE or REPLACE FUNCTION fn_restricion_agente_venta()
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

CREATE CONSTRAINT Trigger restriccion_agente_venta
AFTER INSERT or UPDATE on PUBLIC.agente
DEFERRABLE INITIALLY DEFERRED
for EACH row EXECUTE FUNCTION fn_restricion_agente_venta();


INSERT INTO agente VALUES (234567890,'Marcos')

Begin;
    INSERT INTO agente VALUES ('234567890','Marcos');
    INSERT INTO venta VALUES ('123456789','098765432','234567890', 400);
COMMIT;

DELETE FROM "venta" WHERE "dni_agente"='234567890'

ROLLBACK
