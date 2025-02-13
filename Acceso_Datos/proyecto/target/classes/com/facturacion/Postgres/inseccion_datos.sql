-- Insertar 10 clientes
INSERT INTO Clientes (nombre, apellido) VALUES 
('Juan', 'Pérez'),
('María', 'González'),
('Carlos', 'López'),
('Ana', 'Martínez'),
('Luis', 'Fernández'),
('Elena', 'Torres'),
('Pedro', 'Gómez'),
('Laura', 'Díaz'),
('Roberto', 'Ruiz'),
('Carmen', 'Sánchez');

Begin;

-- Insertar 10 contadores con datos ficticios
INSERT INTO Contador (id_contador, dias) VALUES
(1, ARRAY[ ROW(ARRAY[8,3])::horas_dia, ROW(ARRAY[8,7])::horas_dia ]),
(2, ARRAY[ ROW(ARRAY[8,3])::horas_dia, ROW(ARRAY[8,7])::horas_dia ]),
(3, ARRAY[ ROW(ARRAY[8,3])::horas_dia, ROW(ARRAY[8,7])::horas_dia ]),
(4, ARRAY[ ROW(ARRAY[8,3])::horas_dia, ROW(ARRAY[8,7])::horas_dia ]),
(5, ARRAY[ ROW(ARRAY[8,3])::horas_dia, ROW(ARRAY[8,7])::horas_dia ]),
(6, ARRAY[ ROW(ARRAY[8,3])::horas_dia, ROW(ARRAY[8,7])::horas_dia ]),
(7, ARRAY[ ROW(ARRAY[8,3])::horas_dia, ROW(ARRAY[8,7])::horas_dia ]),
(8, ARRAY[ ROW(ARRAY[8,3])::horas_dia, ROW(ARRAY[8,7])::horas_dia ]),
(9, ARRAY[ ROW(ARRAY[8,3])::horas_dia, ROW(ARRAY[8,7])::horas_dia ]),
(10,ARRAY[ ROW(ARRAY[8,3])::horas_dia, ROW(ARRAY[8,7])::horas_dia ]);

-- Insertar 10 relaciones Cliente-Contador
INSERT INTO Cliente_contador (mes, nombre, apellido, id_contador) VALUES
('Enero', 'Juan', 'Pérez', 1),
('Febrero', 'María', 'González', 2),
('Marzo', 'Carlos', 'López', 3),
('Abril', 'Ana', 'Martínez', 4),
('Mayo', 'Luis', 'Fernández', 5),
('Junio', 'Elena', 'Torres', 6),
('Julio', 'Pedro', 'Gómez', 7),
('Agosto', 'Laura', 'Díaz', 8),
('Septiembre', 'Roberto', 'Ruiz', 9),
('Octubre', 'Carmen', 'Sánchez', 10);



COMMIT;
