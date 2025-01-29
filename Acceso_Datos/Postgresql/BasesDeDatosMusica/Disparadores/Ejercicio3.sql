SELECT g1.nombre, count(g1.cod) FROM grupo g1, club c1 
WHERE c1.cod_gru = g1.cod GROUP BY g1.cod ORDER BY g1.cod


-- En esta sentencia un grupo solo puede tener un club asignado


CREATE or REPLACE Trigger grupo_unico
BEFORE INSERT on club
for EACH row EXECUTE FUNCTION fn_grupo_unico();
  
CREATE or REPLACE FUNCTION fn_grupo_unico()
RETURNS TRIGGER
LANGUAGE 'plpgsql'
AS $$
Begin 
    if (SELECT count(g1.cod) FROM grupo g1, club c1 WHERE new.cod_gru = g1.cod ) > 1 THEN
        RAISE NOTICE 'No puede haber grupos con mas de un club';
        RETURN NULL;
    end if;
return NEW;
End;
$$;

INSERT INTO club VALUES('19','Val','España',543,7);

