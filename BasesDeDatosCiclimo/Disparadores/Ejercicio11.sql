-- Active: 1726053968786@@127.0.0.1@5432@ciclismo
CREATE TABLE Grupo(
    nomgrup VARCHAR(50) PRIMARY key,
    num_participantes INTEGER
);

CREATE TABLE Usuario(
    nomUsuario VARCHAR(50) PRIMARY key
    
)

CREATE TABLE UsuarioGrupo(
    nomgrup VARCHAR(50),
    nomUsuario VARCHAR(50),
    Constraint pk_UsuarioGrupo PRIMARY key(nomgrup,nomUsuario),
    Constraint ca_Usuario FOREIGN key(nomUsuario) REFERENCES Usuario(nomUsuario),
    Constraint ca_Grupo FOREIGN key(nomgrup) REFERENCES Grupo(nomgrup)
);

--Triger para numero participantes

CREATE or replace TRIGGER nº_participates_grupo
AFTER INSERT on Usuario
for EACH ROW EXECUTE FUNCTION fn_conatarParticipantes();

CREATE OR REPLACE FUNCTION fn_conatarParticipantes()
RETURNS trigger
LANGUAGE 'plpgsql'
COST 100
VOLATILE NOT LEAKPROOF
AS $BODY$
DECLARE 
    numero INTEGER;
BEGIN
    SELECT count(nomUsuario) into numero FROM UsuarioGrupo WHERE UsuarioGrupo.no  ;
    UPDATE grupo SET nnum_participantes = numero;
RETURN NEW;
END;
$BODY$;

