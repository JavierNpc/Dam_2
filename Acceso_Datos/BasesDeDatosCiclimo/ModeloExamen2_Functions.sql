
CREATE TRIGGER SOLO_UN_LIDER_TG()
BEFORE UPDATE participantes at nombre_eq
DEFERRABLE DEFERRED
for EACH row EXECUTE SOLO_UN_LIDER()

CREATE OR REPLACE FUNCTION SOLO_UN_LIDER()
RETURNS trigger
LANGUAGE 'plpgsql' 
as $body$ 
BEGIN
    if EXISTS (SELECT * from equipo WHERE equipo.dorsal = OLD.dorsal) THEN
        RAISE NOTICE "Este ciclista es el lider del equipo no se puede modificar";
        RETURN null;
    end if;
RETURN new;
END;
$body$
;	





Insert into participantes VALUES(1,'Javier','Maceda','18-09-2004','TGV');
INSERT into equipo VALUES('TGV','Audi','Español','10000',1);

BEGIN;
    INSERT into equipo VALUES('TGV','Audi','Español','10000',1),
    Insert into participantes VALUES(1,'Javier','Maceda','18-09-2004','TGV'),
    Insert into participantes VALUES(2,'Alex','Rusu','14-06-2005','TGV')
COMMIT;

ROLLBACK;