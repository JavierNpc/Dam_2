INSERT INTO clientes (nombre, apellido) VALUES (' javier 1','maceda 1');
INSERT INTO clientes (nombre, apellido) VALUES (' javier 1','maceda 1');
INSERT INTO clientes (nombre, apellido) VALUES (' javier 1','maceda 1');
INSERT INTO clientes (nombre, apellido) VALUES (' javier 1','maceda 1');
INSERT INTO clientes (nombre, apellido) VALUES (' javier 1','maceda 1');
INSERT INTO clientes (nombre, apellido) VALUES (' javier 1','maceda 1');

Begin;
INSERT INTO contador (id_contador, dias) VALUES ( 0 ,ARRAY[ ROW(ARRAY[8,3])::horas_dia, ROW(ARRAY[8,7])::horas_dia ]);
INSERT INTO contador (id_contador, dias) VALUES ( 1 ,ARRAY[ ROW(ARRAY[8,3])::horas_dia, ROW(ARRAY[8,7])::horas_dia ]);
INSERT INTO contador (id_contador, dias) VALUES ( 2 ,ARRAY[ ROW(ARRAY[8,3])::horas_dia, ROW(ARRAY[8,7])::horas_dia ]);
INSERT INTO contador (id_contador, dias) VALUES ( 3 ,ARRAY[ ROW(ARRAY[8,3])::horas_dia, ROW(ARRAY[8,7])::horas_dia ]);
INSERT INTO contador (id_contador, dias) VALUES ( 4 ,ARRAY[ ROW(ARRAY[8,3])::horas_dia, ROW(ARRAY[8,7])::horas_dia ]);
INSERT INTO contador (id_contador, dias) VALUES ( 5 ,ARRAY[ ROW(ARRAY[8,3])::horas_dia, ROW(ARRAY[8,7])::horas_dia ]);

INSERT INTO cliente_contador_enero (nombre, apellido, id_contador) VALUES (' javier 1','maceda 1',0);
INSERT INTO cliente_contador_enero (nombre, apellido, id_contador) VALUES (' javier 1','maceda 1',1);
INSERT INTO cliente_contador_enero (nombre, apellido, id_contador) VALUES (' javier 1','maceda 1',2);
INSERT INTO cliente_contador_enero (nombre, apellido, id_contador) VALUES (' javier 1','maceda 1',3);
INSERT INTO cliente_contador_enero (nombre, apellido, id_contador) VALUES (' javier 1','maceda 1',4);
INSERT INTO cliente_contador_enero (nombre, apellido, id_contador) VALUES (' javier 1','maceda 1',5);
Commit;
