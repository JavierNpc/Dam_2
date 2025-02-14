package com.facturacion;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

import com.facturacion.Mongo.ConexionMongo;
import com.facturacion.Mongo.Facturas;
import com.facturacion.Postgres.Conexion_Postgres;

public class Main {
    public static void main(String[] args) {
     
       /*  ConexionMongo mongo = new ConexionMongo("mati","mati", "localhost", 27017, "GestioneEnegetica" );
        Facturas facturas = new Facturas("mati","mati", "localhost", 27017, "GestioneEnegetica" );

        // ConexionMongo mongo = new ConexionMongo( "localhost", 27017, "GestioneEnegetica" ); //   Casa
        
        mongo.Conectar_Con_BBDD();

        mongo.eliminar_Collecion("Enero");

        mongo.insertar_Collecion("Enero",100);

        mongo.update_nombre_contrato_coleccion("Enero", 1, "Dani");

        mongo.delete_nombre_contrato_coleccion("Enero", "Dani");  

        mongo.Factura_De_Coleccion("Enero");


        mongo.Desconectar(); 

        facturas.Conectar_Con_BBDD();

        facturas.crear_factura_coleccion("Enero");
 */

        //·   POSTGRES                                          

        
            

    /*    Conexion_Postgres postgres = new Conexion_Postgres("GestionEnergetica_post","mati","mati");

        postgres.ConectarConBBDD();
        //postgres.inertarDatos(2);
        postgres.inseccionMasiva(5);

     */
        inseccionMasiva(100);
       
       
     
        
    }  

    public static void inseccionMasiva(int numero_archivos){

        String consulta = "SELECT cont(*) as total FROM cliente_contador_enero ";
        String rutaSQL_linux = "/proyecto/src/main/java/com/facturacion/Postgres/datos_sql.sql";
        String rutaSQL_win = "src\\main\\java\\com\\facturacion\\Postgres\\datos_sql.sql" ;
        File sql_datos = new File(rutaSQL_win);
        int num_contratos = 0;

        ArrayList<String> dnis = new ArrayList<>();

        dnis = generarDNIs(numero_archivos+1);

       /*  try {
            PreparedStatement pstmt = conexion.prepareStatement(consulta);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                num_contratos = rs.getInt("total");
            }
        } catch (SQLException e) {
           
            e.printStackTrace();
        }
 */
        int num_archivos = num_contratos + numero_archivos;
       
        try {
            FileWriter  fWriter = new FileWriter(sql_datos);
            BufferedWriter bw = new BufferedWriter(fWriter);

            for (int i = num_contratos; i <= num_archivos; i++) {
                String dni = dnis.get(i);
                String nombre = "javier "+i;
                String apellido = "maceda "+i;
                bw.write("INSERT INTO clientes (dni,nombre, apellido) VALUES ("+dni+",'"+nombre+"','"+apellido+"');");
                bw.newLine();
            }

            bw.newLine();
            bw.write("Begin;");
            bw.newLine();

            for (int i = num_contratos; i <= num_archivos; i++) {
                int id_contador = i;
                String dias = "ARRAY[ ROW(ARRAY[8,3])::horas_dia, ROW(ARRAY[8,7])::horas_dia ]";
                bw.write("INSERT INTO contador (id_contador, dias) VALUES ( "+id_contador+" ,"+dias+");");
                bw.newLine();
            }

            bw.newLine();

            for (int i = num_contratos; i <= num_archivos; i++) {
                String dni =  dnis.get(i);
                int id_contador = i;
                bw.write("INSERT INTO cliente_contador_enero ( id_contador) VALUES ('"+dni+"',"+id_contador+");");
                bw.newLine();
            }

            bw.write("Commit;");
            bw.newLine();
            bw.close();
        }catch (IOException e) {
            e.printStackTrace();
        }

       /*  try {
            Statement stmt = conexion.createStatement();
            String sql = new String(Files.readAllBytes(Paths.get(rutaSQL)));
            stmt.execute(sql); 
        } catch (IOException | SQLException e) {
            System.out.println("Error al cargar los datos");
            e.printStackTrace();
        } */
    }
    
    private static  ArrayList<String> generarDNIs(int cantidad) {
        ArrayList<String> dnis = new ArrayList<>();
        Random random = new Random();
        String letrasDNI = "TRWAGMYFPDXBNJZSQVHLCKE"; // Letras válidas para DNI en España

        for (int i = 0; i < cantidad; i++) {
            int numeroDNI = 10000000 + random.nextInt(90000000); // Número entre 10000000 y 99999999
            char letra = letrasDNI.charAt(numeroDNI % 23); // Obtener la letra según el módulo 23
            dnis.add(numeroDNI + "" + letra); // Guardar en el ArrayList
        }

        return dnis;
    }


}