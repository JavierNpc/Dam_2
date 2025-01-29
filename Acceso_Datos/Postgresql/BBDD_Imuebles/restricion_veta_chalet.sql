CREATE or REPLACE FUNCTION fn_restricion_venta_chalet()
RETURNS TRIGGER
LANGUAGE 'plpgsql'
AS $$
Begin 
   if EXISTS (SELECT precio_estimado from chalet WHERE id_inmueble = new.id_inmueble and chalet.precio_estimado>new.precio_venta) THEN
        RAISE notice 'No se puede vender un chalet por un menor precio del estimado';
        RETURN NULL;
   end if;
   RETURN new;
End;
$$;

CREATE or REPLACE Trigger restricion_venta_chalet
BEFORE INSERT or UPDATE on PUBLIC.venta
for EACH row EXECUTE FUNCTION fn_restricion_venta_chalet();

