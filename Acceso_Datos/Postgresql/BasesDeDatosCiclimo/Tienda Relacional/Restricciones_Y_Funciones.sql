-- Active: 1726053968786@@127.0.0.1@5432@tiendarelacional@public


-- ·  Delete pieza / comp  · --


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

--·  Total  ·--

CREATE OR REPLACE FUNCTION InsertarProductoComp_fnc()
RETURNS TRIGGER
LANGUAGE 'plpgsql'
as $body$
BEGIN

    IF EXISTS (SELECT * FROM componentes WHERE c_nombre_producto = new.nombre_producto ) or EXISTS (SELECT * FROM formulas WHERE f_nombre_producto = new.nombre_producto )
    THEN
    RETURN NEW;

    ELSE
        RAISE EXCEPTION 'Debe de estar en una de las dos tablas. Formulas o Componentes';
    END IF;

End;
$body$;


CREATE CONSTRAINT TRIGGER InsertarProductoComp
AFTER INSERT on PUBLIC.productos
DEFERRABLE INITIALLY DEFERRED
for EACH row EXECUTE FUNCTION InsertarProductoComp_fnc();



--· Disjunta ·--                                   OKEY


CREATE OR REPLACE FUNCTION disjunta_Comp_fnc() --*OK
RETURNS TRIGGER
LANGUAGE 'plpgsql'
as $body$
BEGIN

    if (new.c_nombre_producto in (SELECT f_nombre_producto FROM formulas)) THEN
        RAISE NOTICE 'No se pude ingresar, El producto ya esta en la tabla formulas';
        return NULL;
    END if;

    RETURN NEW;
  
End;
$body$;

CREATE or REPLACE TRIGGER disjunta_Comp
BEFORE INSERT or UPDATE on PUBLIC.componentes
for EACH row EXECUTE FUNCTION disjunta_Comp_fnc();

-- -------------------------


CREATE OR REPLACE FUNCTION disjunta_Form_fnc() --* OK
RETURNS TRIGGER
LANGUAGE 'plpgsql'
as $body$
BEGIN

    if (new.f_nombre_producto in (SELECT c_nombre_producto FROM componentes))THEN
        RAISE NOTICE 'No se pude ingresar, El producto ya esta en la tabla componanetes';
        return NULL;
    END if;

    RETURN NEW;

End;
$body$;


CREATE or REPLACE TRIGGER disjunta_Form
BEFORE INSERT or UPDATE on PUBLIC.formulas
for EACH row EXECUTE FUNCTION disjunta_Form_fnc();



--·  Minimo 1 pieza componente ·--


CREATE OR REPLACE FUNCTION restriccion_min_componente_fnc()
RETURNS TRIGGER
LANGUAGE 'plpgsql'
as $body$
BEGIN

    if EXISTS( SELECT 1 from piezas_componentes WHERE NEW.c_nombre_producto = pc_nombre_producto) THEN
        RETURN NEW;
    ELSE
        RAISE EXCEPTION 'Un componente debe puede tener 1 pieza minimo';
    End if;

End;
$body$;


CREATE CONSTRAINT TRIGGER restriccion_min_componente
AFTER INSERT on PUBLIC.componentes
DEFERRABLE INITIALLY DEFERRED
for EACH row EXECUTE FUNCTION restriccion_min_componente_fnc();


CREATE OR REPLACE FUNCTION restriccion_min_componente_pieza_fnc()
RETURNS TRIGGER
LANGUAGE 'plpgsql'
as $body$
BEGIN

    if EXISTS (SELECT 1 from piezas_componentes WHERE NEW.nombre_pieza = pc_nombre_producto) THEN
        RETURN NEW;
    ELSE
        RAISE EXCEPTION 'Una pieza debe puede tener 1 componente minimo';
    End if;

End;
$body$;


CREATE CONSTRAINT TRIGGER restriccion_min_componente_pieza
AFTER INSERT on PUBLIC.piezas
DEFERRABLE INITIALLY DEFERRED
for EACH row EXECUTE FUNCTION restriccion_min_componente_pieza_fnc();