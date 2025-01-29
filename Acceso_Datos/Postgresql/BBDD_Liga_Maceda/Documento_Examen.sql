-- Active: 1726053968786@@127.0.0.1@5432@prueba_examen
--- TABLAS ------------------------------------------------------------
CREATE TABLE pais(
    nombre_pais VARCHAR(255) PRIMARY KEY,
    m2 INTEGER NOT NULL,
    CONSTRAINT pais_m2_check CHECK ((m2 >= 1000))
);

CREATE TABLE liga(
    nivel VARCHAR(9) ,
    nombre_pais VARCHAR(255) ,
    numequipos SERIAL NOT NUll,
    PRIMARY KEY (nivel,nombre_pais),
    CONSTRAINT fa_nombre_pais FOREIGN KEY (nombre_pais) REFERENCES pais (nombre_pais) on delete CASCADE on update CASCADE
);

CREATE TABLE equipo(
    nombre_equipo VARCHAR(255) PRIMARY KEY,
    nivel VARCHAR(9) NOT NULL,
    nombre_pais VARCHAR(255) NOT NULL,
    localidad VARCHAR(255) NOT NULL,
    anyofundacion VARCHAR(255) NOT NULL,
    CONSTRAINT fa_nombre_pais_liga FOREIGN KEY (nombre_pais,nivel) REFERENCES liga (nombre_pais,nivel)  on delete CASCADE on update CASCADE 
);

CREATE TABLE tecnico(
    dni_tecnico VARCHAR(9) PRIMARY KEY,
    nombre_tecnico VARCHAR(255) not null
);

CREATE TABLE r3(
    dni_tecnico VARCHAR(9),
    nombre_equipo VARCHAR(255),
    funcion VARCHAR(255) NOT NULL,
    PRIMARY key (dni_tecnico, nombre_equipo),
    CONSTRAINT fa_r3_dni FOREIGN KEY (dni_tecnico) REFERENCES tecnico (dni_tecnico)  on delete CASCADE on update CASCADE ,
    CONSTRAINT fa_r3_nombre_equipo FOREIGN KEY (nombre_equipo) REFERENCES equipo (nombre_equipo) on delete CASCADE on update CASCADE
);

CREATE TABLE equiposborrados(
    id SERIAL PRIMARY KEY,
    nombre_equipo VARCHAR(255),
    fecha_borrado DATE NOT NULL,
    localidad VARCHAR(255) NOT NULL,
    anyofundacion VARCHAR(255) NOT NULL
);

---------------------------------------------------------------

--- RESTRICCIONES ---------------------------------------------

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

-- Restriccion numero equipos

CREATE or REPLACE FUNCTION numequipos_liga_fn()
RETURNS TRIGGER
LANGUAGE 'plpgsql'
AS $body$
Begin 
update liga set numequipos = numequipos+1 where nombre_pais = new.nombre_pais AND nivel = new.nivel ;
RETURN new;
End;
$body$;
CREATE or REPLACE Trigger numequipos_liga
AFTER INSERT on PUBLIC.equipo
for EACH row EXECUTE FUNCTION numequipos_liga_fn();

-- Restriccion insertar en equiposborrados despues delete en equipos

CREATE or REPLACE FUNCTION ins_equipobrrado_fn()
RETURNS TRIGGER
LANGUAGE 'plpgsql'
AS $body$
Begin 
insert into equiposborrados (nombre_equipo,fecha_borrado,localidad,anyofundacion) values (old.nombre_equipo, now(), OLD.localidad, old.anyofundacion);
RETURN new;
End;
$body$;
CREATE or REPLACE Trigger ins_equipobrrado
AFTER DELETE on PUBLIC.equipo
for EACH row EXECUTE FUNCTION ins_equipobrrado_fn();

--Restriccion : en tecnico ( no se puede insertar un tecnico sin un equipo)
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
---------------------------------------------------------------

--- PRUEBAS ---------------------------------------------------


-- Insetar un pais
INSERT INTO public.pais(nombre_pais, m2) VALUES('España',900) ;-- Comprobacion del CHECK (m2>=1000)

INSERT INTO public.pais(nombre_pais, m2) VALUES('España',1000); 


-- Insetar dos liga

insert into liga (nivel, nombre_pais) VALUES (1, 'Francia'); -- Comprobacion de calve Ajena
insert into liga (nivel, nombre_pais) VALUES (1, 'España'); -- liga 1
insert into liga (nivel, nombre_pais) VALUES (2, 'España'); -- liga 2

 -- Dato: el numero de equipos tiene un default de 0, se va llenado cada vez que crees un equpo nuevo.

-- Insetar dos equipos  

insert into equipo (nombre_equipo, nivel, nombre_pais, localidad, anyofundacion) values ('TGH', '1', 'España', 'Valencia', '2002');
insert into equipo (nombre_equipo, nivel, nombre_pais, localidad, anyofundacion) values ('RDF', '2', 'España', 'Madrid', '2005');


-- Insetar un tecnico


insert into tecnico (dni_tecnico, nombre_tecnico) values ('123456789', 'Javier'); --No ira porqu necesita estar en un equipo

Begin;
    insert into tecnico (dni_tecnico, nombre_tecnico) values ('123456789', 'Javier'); --No ira porqu necesita estar en un equipo
    insert into contratado (dni_tecnico, nombre_equipo, funcion) values ('123456789', 'TGH', 'arquitecto');
COMMIT;

ROLLBACK;




---------------------------------------------------------------

