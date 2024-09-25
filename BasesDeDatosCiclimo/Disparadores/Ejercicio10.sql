CREATE or REPLACE Trigger edadCrez
BEFORE INSERT on ciclista
for EACH row EXECUTE FUNCTION fn_edadCrez();

 CREATE OR REPLACE FUNCTION fn_edadCrez()
RETURNS trigger
LANGUAGE 'plpgsql'
COST 100
VOLATILE NOT LEAKPROOF
AS $BODY$
DECLARE
    num INTEGER;
BEGIN

    SELECT count(*) into num FROM equipo e1 INNER JOIN ciclista USING (nomeq)
        WHERE e1.nomeq = new.nomeq 
        GROUP BY e1.nomeq;

    UPDATE equipo set nºciclistas_equipo = num WHERE nomeq = new.nomeq;
    
RETURN NEW;
END;
$BODY$; 

CREATE OR REPLACE FUNCTION fn_edadCrez()
RETURNS trigger
LANGUAGE 'plpgsql'
COST 100
VOLATILE NOT LEAKPROOF
AS $BODY$
DECLARE
    cursor1 CURSOR for SELECT count(*) FROM equipo e1 INNER JOIN ciclista USING (nomeq)
        WHERE e1.nomeq = new.nomeq ;
       
    num INTEGER;
BEGIN

    OPEN cursor1;
    LOOP
        FETCH cursor1 into num;
        EXIT WHEN NOT FOUND;
        CLOSE cursor1;
    end LOOP;

    UPDATE equipo set nºciclistas_equipo = num WHERE nomeq = new.nomeq;
    
RETURN NEW;
END;
$BODY$;


SELECT count(*) as num ,e1.nomeq FROM equipo e1, ciclista c1 
WHERE e1.nomeq = c1.nomeq 
GROUP BY e1.nomeq
ORDER BY e1.nomeq

INSERT INTO ciclista VALUES (103,'Javier Maceda',25,'Banesto')
