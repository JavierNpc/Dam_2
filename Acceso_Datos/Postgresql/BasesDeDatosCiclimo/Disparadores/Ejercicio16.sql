--a



--g
    --4
    CREATE OR REPLACE FUNCTION fn_conatarParticipantes()
    RETURNS trigger
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE NOT LEAKPROOF
    AS $BODY$
    DECLARE 
        ver_etapa_dorsal INTEGER;
        curetapa cursor set (SELECT dorsal from etapa)
    BEGIN
        LOOP
            FETCH cuetapa INTO ver_etapa_dorsal;
            exit WHERE NOT FOUND;
            UPDATE ciclista c0 set premio = premio +100 *(SELECT count(*) FROM ciclista c1
            WHERE c0.dorsal = c1.dorsal)
            WHERE dorsal = ver_etapa_dorsal;
            Raise NOTICE 'el ciclista col el dorsal % ha ganado';
        end LOOP;
        CLOSE curetapa;
    end;

       
    RETURN NEW;
    END;
    $BODY$;



  