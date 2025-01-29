-- Active: 1726053968786@@127.0.0.1@5432@prueba_examen


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
    insert into contratado (dni_tecnico, nombre_equipo, funcion) values ('123456789', 'TGH', 'arquitecto');
    insert into tecnico (dni_tecnico, nombre_tecnico) values ('123456789', 'Javier'); --No ira porqu necesita estar en un equipo
COMMIT;


