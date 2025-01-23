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

    if NOT EXISTS (SELECT * from piezas_componentes WHERE nombre_pieza = new.nombre_pieza )THEN
        RAISE EXCEPTION 'No se puede crear una pieza sin asignarle un componente';
    End if;
    RETURN new;
End;
$body$;


CREATE CONSTRAINT TRIGGER restriccion_piezas_fnc
AFTER INSERT on PUBLIC.piezas
DEFERRABLE INITIALLY DEFERRED 
for EACH row EXECUTE FUNCTION restriccion_piezas_fnc();



CREATE OR REPLACE FUNCTION restriccion_componente_fnc()
RETURNS TRIGGER
LANGUAGE 'plpgsql'
as $body$
DECLARE
 consulta INT;
BEGIN

    if NOT EXISTS (SELECT * from piezas_componentes WHERE c_nombre_producto = new.c_nombre_producto )THEN
        RAISE EXCEPTION 'No se puede crear un componente sin asignarle una pieza';
    End if;

  
    RETURN new;
End;
$body$;


CREATE CONSTRAINT TRIGGER restriccion_componente
AFTER INSERT on PUBLIC.componentes
DEFERRABLE INITIALLY DEFERRED 
for EACH row EXECUTE FUNCTION restriccion_componente_fnc();

-- ·  Delete  · --


CREATE OR REPLACE FUNCTION delete_piezas_componentes_fnc()
RETURNS TRIGGER
LANGUAGE 'plpgsql'
as $body$
DECLARE
 consulta INT;
BEGIN

    if (SELECT count(*) from piezas_componentes WHERE nombre_pieza = new.nombre_pieza )THEN
        RAISE EXCEPTION 'No se puede crear una pieza sin asignarle un componente';
    End if;

   
    
    RETURN new;
   
    
End;
$body$;


CREATE or REPLACE TRIGGER delete_piezas_componentes
AFTER DELETE on PUBLIC.piezas_componentes
for EACH row EXECUTE FUNCTION delete_piezas_componentes_fnc();

INSERT INTO piezas_componentes VALUES("comp","p1")


--- Restriccion 1 --- Total y Disjunta

