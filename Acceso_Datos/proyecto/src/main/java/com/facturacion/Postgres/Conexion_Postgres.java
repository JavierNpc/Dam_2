package com.facturacion.Postgres;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

    public void inertarDatos(int num_contratos) {

        PreparedStatement sentencia_clientes = null;
       
        for (int i = 0; i < num_contratos; i++) {

            String Datos_Clientes = ("Javier "+i+","+"Maceda "+i);
            
            try {
                sentencia_clientes = conexion.prepareStatement(EscribirSentecia("Clientes", Datos_Clientes));
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
            
            PreparedStatement sentenciaAltaProducto = conexion.prepareStatement(sqlAltaProducto, PreparedStatement.RETURNS_GENERATED_KEYS); 
            sentenciaAltaProducto.setString(1, nombreProducto); 
            sentenciaAltaProducto.setFloat(2, precioProducto); sentencia.executeUpdate();
        
            
            PreparedStatement sentenciaRelacionProducto =
                                conexion.prepareStatement(sqlRelacionProducto);
            sentenciaRelacionProducto.setInt(1, idProducto);
            sentenciaRelacionProducto.setInt(2, idCategoria);
            sentenciaRelacionProducto.executeUpdate();
            
            // Valida la transacción
            conexion.commit();
            } catch (SQLException sqle) {
            sqle.printStackTrace();
            } finally {
            if (sentencia != null)
                try {
                sentencia.close();
                resultado.close();
                } catch (SQLException sqle) {
                sqle.printStackTrace();
                }
            }

        }
    }

    /**
     * @param tabla   = String
     * @param valores = String : La forma de colocarlos es = "Javier Maceda Castro".
     *                Se guarda en un ( String[] valores ) y hace las particiones a
     *                traves de .split(" ")
     * @return
     *         sentecia = "Insert into "tu_tabla" values (valores[0], valores[1],
     *         ...)
     */
    private String EscribirSentecia(String tabla, String valores) {
        String[] valores_lista = valores.split(",");

        switch (valores_lista.length) {
            case 1:
                return "INSERT INTO " + tabla + " (nombre, apellido) VALUES ('" + valores_lista[0] + "')";
            case 2:
                return "INSERT INTO " + tabla + " (nombre, apellido) VALUES ('" + valores_lista[0] + "','"
                        + valores_lista[1] + "')";
            case 3:
                return "INSERT INTO " + tabla + " (nombre, apellido) VALUES ('" + valores_lista[0] + "','"
                        + valores_lista[1] + "','" + valores_lista[2] + "')";
            case 4:
                return "INSERT INTO " + tabla + " (nombre, apellido) VALUES ('" + valores_lista[0] + "','"
                        + valores_lista[1] + "','" + valores_lista[2] + "','" + valores_lista[3] + "')";
            default:
                return null;
        }
    }
}
