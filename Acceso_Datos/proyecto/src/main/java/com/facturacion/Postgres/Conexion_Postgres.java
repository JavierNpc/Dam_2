package com.facturacion.Postgres;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.Buffer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

public class Conexion_Postgres {
    private String user = "tu_usuario";
    private String password = "tu_contraseña";
    private String bbdd = "";
    private Connection conexion;

    public Conexion_Postgres(String bbdd, String user, String password) {
        this.bbdd = bbdd;
        this.user = user;
        this.password = password;
    }

    public void ConectarConBBDD() {
        try {
            conexion = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + bbdd, user, password);
            if (conexion != null) {
                System.out.println("Conexión exitosa a PostgreSQL!");
            }
        } catch (SQLException e) {
            System.err.println("Error al conectar: " + e.getMessage());
        }
    }


    public void inseccionMasiva(int numero_archivos){

        String consulta = "SELECT cont(*) as total FROM cliente_contador_enero ";
        String rutaSQL_linux = "/proyecto/src/main/java/com/facturacion/Postgres/datos_sql.sql";
        String rutaSQL_win = "src\\main\\java\\com\\facturacion\\Postgres\\datos_sql.sql" ;
        File sql_datos = new File(rutaSQL_linux);
        int num_contratos = 0;

        ArrayList<String> dnis = new ArrayList<>();

        dnis = generarDNIs(numero_archivos);

        try {
            PreparedStatement pstmt = conexion.prepareStatement(consulta);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                num_contratos = rs.getInt("total");
            }
        } catch (SQLException e) {
           
            e.printStackTrace();
        }

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

        try {
            Statement stmt = conexion.createStatement();
            String sql = new String(Files.readAllBytes(Paths.get(rutaSQL_linux)));
            stmt.execute(sql); 
        } catch (IOException | SQLException e) {
            System.out.println("Error al cargar los datos");
            e.printStackTrace();
        }
    }


    /* public void inertarDatos(int num_contratos) { 
        !No esta actualizado, utilizar este metodo con arrays es muy complejo

        PreparedStatement sentencia_clientes = null;
        PreparedStatement sentenciaAltaProducto = null;
        PreparedStatement sentenciaRelacionProducto = null;
        Random ram = new Random();

        
        Object[][] diasArray = new Object[2][2];
        for (int j = 0; j <=1 ; j++) {
            diasArray[j] = new Object[]{ram.nextInt(0,10), ram.nextInt(0,10)}; // Cada día es [8,3]
        }
     
        for (int i = 0; i < num_contratos; i++) {

            String Datos_Clientes = "INSERT INTO clientes (dni, nombre, apellido) VALUES (?,?)";
            
            try {
                sentencia_clientes = conexion.prepareStatement(Datos_Clientes);
                sentencia_clientes.setString(1, "Javier "+i);
                sentencia_clientes.setString(2, "Javier "+i);
                sentencia_clientes.setString(3, "Maceda "+i);
                sentencia_clientes.executeUpdate();
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            } finally {
                if (sentencia_clientes != null)
                    try {
                        sentencia_clientes.close();
                    } catch (SQLException sqle) {
                        sqle.printStackTrace();
                    }
            }


            try {
                //Inicia transacción
                conexion.setAutoCommit(false);

                String sqlAltaProducto = "INSERT INTO contador (id_contador, dias) VALUES (?,?)";
            
                // ARRAY[ ROW(ARRAY[8,3])::horas_dia, ROW(ARRAY[8,7])::horas_dia ]

                Array dias = conexion.createArrayOf("horas_dia", diasArray);

                System.out.println(dias);
             
                sentenciaAltaProducto = conexion.prepareStatement(sqlAltaProducto); 
                sentenciaAltaProducto.setInt(1, i); 
                sentenciaAltaProducto.setArray(2,  dias);
                sentenciaAltaProducto.executeUpdate();
            
                String sqlRelacionProducto = "INSERT INTO cliente_contador_enero (nombre, apellido, id_contador) VALUES (?, ?, ?)";
          
                sentenciaRelacionProducto = conexion.prepareStatement(sqlRelacionProducto);
                sentenciaRelacionProducto.setString(1, "Javier "+i);
                sentenciaRelacionProducto.setString(2, "Maceda "+i);
                sentenciaRelacionProducto.setInt(3, i);
                sentenciaRelacionProducto.executeUpdate();
                
                // Valida la transacción
                conexion.commit();
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            } finally {
                if (sentenciaAltaProducto != null && sentenciaRelacionProducto != null)
                    try {
                        sentenciaAltaProducto.close();
                        sentenciaRelacionProducto.close();
                    } catch (SQLException sqle) {
                        sqle.printStackTrace();
                    }
            }

        }
    }

    */

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
