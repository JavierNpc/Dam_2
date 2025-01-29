-- Active: 1726053968786@@127.0.0.1@5432@liga_maceda

CREATE TABLE pais(
    nombre_pais VARCHAR(255) PRIMARY KEY,
    m2 INTEGER NOT NULL
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

-- tablas relacionadas

CREATE TABLE r3(
    dni_tecnico VARCHAR(9),
    nombre_equipo VARCHAR(255),
    funcion VARCHAR(255) NOT NULL,
    PRIMARY key (dni_tecnico, nombre_equipo),
    CONSTRAINT fa_r3_dni FOREIGN KEY (dni_tecnico) REFERENCES tecnico (dni_tecnico)  on delete CASCADE on update CASCADE ,
    CONSTRAINT fa_r3_nombre_equipo FOREIGN KEY (nombre_equipo) REFERENCES equipo (nombre_equipo) on delete CASCADE on update CASCADE
);


-- tabla fuera


CREATE TABLE equiposborrados(
    id SERIAL PRIMARY KEY,
    nombre_equipo VARCHAR(255),
    fecha_borrado DATE NOT NULL,
    localidad VARCHAR(255) NOT NULL,
    anyofundacion VARCHAR(255) NOT NULL
);
