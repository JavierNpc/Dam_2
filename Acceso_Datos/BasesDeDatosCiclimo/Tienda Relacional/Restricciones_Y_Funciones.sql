-- Active: 1726053968786@@127.0.0.1@5432@tiendarelacional@public

--- Restriccion 0 ---

CREATE OR REPLACE FUNCTION restriccion_piezas_max_insert_fnc()
RETURNS TRIGGER
LANGUAGE 'plpgsql'
as $body$
DECLARE
 consulta INT;
BEGIN

   SELECT count(*) into consulta from piezas_componentes WHERE NEW.c_nombre_producto = c_nombre_producto;

    if(consulta >= 4 ) THEN
        RAISE EXCEPTION 'Un componente no puede tener mas de 4 piezas';
    End if;

    RETURN NEW;
End;
$body$;


CREATE or REPLACE TRIGGER restriccion_precio_insert
BEFORE INSERT on PUBLIC.piezas_componentes
for EACH row EXECUTE FUNCTION restriccion_piezas_max_insert_fnc();

--* ---------------------


CREATE OR REPLACE FUNCTION restriccion_piezas_fnc()
RETURNS TRIGGER
LANGUAGE 'plpgsql'
as $body$
DECLARE
 consulta INT;
BEGIN

   SELECT count(*) into consulta from piezas_componentes WHERE NEW.c_nombre_producto = c_nombre_producto;

    if(consulta >= 4 ) THEN
        RAISE EXCEPTION 'Un componente no puede tener mas de 4 piezas';
    End if;

    RETURN NEW;
End;
$body$;


CREATE TRIGGER restriccion_piezas_fnc
AFTER INSERT on PUBLIC.piezas
DEFERRABLE DEFERRED
for EACH row EXECUTE FUNCTION restriccion_piezas_fnc();


--- Restriccion 1 --- Total y Disjunta

