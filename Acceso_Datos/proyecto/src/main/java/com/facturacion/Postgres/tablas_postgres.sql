CREATE table Clientes(
    dni VARCHAR(255) PRIMARY KEY,
	nombre VARCHAR(255) ,
	apellido VARCHAR(255),
);

CREATE  Type horas_dia as (
	horas NUMERIC[2]
);

CREATE FUNCTION check_mediciones_tamanio(medicion_diaria horas_dia[]) 
RETURNS BOOLEAN AS $$
DECLARE
    dia horas_dia;
BEGIN
    -- Recorrer el array de medicion_diaria
    FOREACH dia IN ARRAY medicion_diaria LOOP
        -- Si algún día no tiene 24 mediciones, retorna FALSO
        IF array_length(dia.horas, 1) <> 2 THEN
            RETURN FALSE;
        END IF;
    END LOOP;
    RETURN TRUE;
END;
$$ LANGUAGE plpgsql IMMUTABLE;


CREATE  Type horas_dia as (
	horas NUMERIC[2]
);

CREATE table Contador(
	id_contador Integer PRIMARY KEY,
	dias horas_dia[30]
	CONSTRAINT check_dias CHECK (array_length(dias, 1) = 2), -- 7 días exactos
    CONSTRAINT check_horas CHECK (check_mediciones_tamanio(dias)) -- Cada día debe tener 24 horas
);

Create table Cliente_contador_Enero(
	dni VARCHAR(255),
	id_contador Integer,
	PRIMARY KEY (dni,id_contador),
	constraint fk_cliente FOREIGN key (dni) REFERENCES Clientes(dni) on delete cascade on update cascade,
	constraint fk_contador FOREIGN key (id_contador) REFERENCES Contador(id_contador)on delete cascade on update cascade

);







CREATE OR REPLACE FUNCTION contador_existe_fn()
RETURNS trigger
LANGUAGE 'plpgsql' 
as $body$ 
BEGIN

    if not EXISTS (SELECT * from Cliente_contador WHERE id_contador = new.id_contador) THEN
        Raise EXCEPTION 'No puede haber un contador sin un cliente';
    end if;

    RETURN new;

END;
$body$;	


CREATE CONSTRAINT TRIGGER contador_existe
After INSERT on Contador
DEFERRABLE INITIALLY DEFERRED
for EACH row EXECUTE FUNCTION contador_existe_fn();


INSERT INTO Contador (id_contador, dias) 
VALUES (11, ARRAY[ ROW(ARRAY[8,3])::horas_dia, ROW(ARRAY[8,7])::horas_dia ]);





