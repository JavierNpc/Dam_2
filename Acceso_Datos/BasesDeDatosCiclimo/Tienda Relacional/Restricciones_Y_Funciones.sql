-- Active: 1726053968786@@127.0.0.1@5432@tiendarelacional@public

--- Restriccion 0 ---

CREATE OR REPLACE FUNCTION restriccion_precio_update_fnc()
RETURNS TRIGGER
LANGUAGE 'plpgsql'
as $body$
BEGIN

    if (new.precio < 3) THEN
        Raise NOTICE 'El precio no pude ser menor a 3€ ';
        RETURN NULL;
    END if;

    RETURN NEW;
End;
$body$;


CREATE or REPLACE TRIGGER restriccion_precio_update
BEFORE UPDATE on PUBLIC.productos
for EACH row EXECUTE FUNCTION restriccion_precio_update_fnc();

--* ------------------------------------------

CREATE OR REPLACE FUNCTION restriccion_precio_insert_fnc()
RETURNS TRIGGER
LANGUAGE 'plpgsql'
as $body$
BEGIN

    if (new.precio < 3) THEN
        Raise NOTICE 'El precio no pude ser menor a 3€ ';
        RETURN NULL;
    END if;

    RETURN NEW;
End;
$body$;


CREATE or REPLACE TRIGGER restriccion_precio_insert
BEFORE INSERT on PUBLIC.productos
for EACH row EXECUTE FUNCTION restriccion_precio_insert_fnc();

--- Restriccion 2 ----

CREATE OR REPLACE FUNCTION restriccion_piezas_min_insert_fnc()
RETURNS TRIGGER
LANGUAGE 'plpgsql'
as $body$
BEGIN

    if (new.precio < 3) THEN
        Raise NOTICE 'El precio no pude ser menor a 3€ ';
        RETURN NULL;
    END if;

    RETURN NEW;
End;
$body$;


CREATE or REPLACE TRIGGER restriccion_precio_insert
BEFORE INSERT on PUBLIC.productos
for EACH row EXECUTE FUNCTION restriccion_precio_insert_fnc();
