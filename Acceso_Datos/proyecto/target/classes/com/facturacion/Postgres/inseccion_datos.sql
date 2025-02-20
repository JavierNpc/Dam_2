-- Insertar 10 clientes
INSERT INTO Clientes (dni,nombre, apellido) VALUES 
(123459,'Juan', 'Pérez'),
(123458,'María', 'González'),
(123457,'Carlos', 'López'),
(123456,'Ana', 'Martínez'),
(123455,'Luis', 'Fernández'),
(123454,'Elena', 'Torres'),
(123453,'Pedro', 'Gómez'),
(123452,'Laura', 'Díaz'),
(123451,'Roberto', 'Ruiz'),
(123450,'Carmen', 'Sánchez');

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
INSERT INTO Cliente_contador_enero (dni, id_contador) VALUES
(123459, 1),
(123458, 2),
(123457, 3),
(123456, 4),
(123455, 5),
(123454, 6),
(123453, 7),
(123452, 8),
(123451, 9),
(123450, 10);



COMMIT;



