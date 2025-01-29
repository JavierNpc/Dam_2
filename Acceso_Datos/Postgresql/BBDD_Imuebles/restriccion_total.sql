CREATE or REPLACE FUNCTION fn_total_casa()
RETURNS TRIGGER
LANGUAGE 'plpgsql'
AS $$
Begin 

    -- Han cambiado mi clave primaria?
    if(OLD.id_inmueble<>new.id_inmueble)THEN
        --si viene de update en cascada
        if (PG_TRIGGER_DEPTH()>1)THEN
            RETURN NEW;
        ELSE
            --Si, no viene de una actualizacaion en cascada
            if not EXISTS (SELECT id_inmueble FROM casa WHERE OLD.id_inmueble = id_inmueble ) THEN
                RAISE NOTICE 'No puedes actualizar este data porqeu no se encuentra en la tabla casa ';
                RETURN null;
            ELSE
                RETURN NEW;
            END IF;
        END IF;
    ELSE
        RETURN new;
    end if;
    
End;
$$;
CREATE or REPLACE Trigger total_casa
BEFORE UPDATE of id_inmueble on chalet 
for EACH row EXECUTE FUNCTION fn_total_casa();

-- LO MISMO EN CASA
""
