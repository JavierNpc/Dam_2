package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexionPostgres {
    
    String BBDD ;
    String url;
    String user;
    String password;
    Connection conexion = null;


    public ConexionPostgres(String bBDD, String url, String user, String password) {
        BBDD = bBDD;
        this.url = url + BBDD;
        this.user = user;
        this.password = password;
    }


    public void Conectar_Con_BBDD(){

       
        
        try {
            Class.forName("org.postgresql.Driver");
            conexion = DriverManager.getConnection(url,user,password);
            System.out.println("Conexion Establecida");
            
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    public void Sentencia_Select(String sentencia){

        Statement st;
        try {
            st = conexion.createStatement();
            ResultSet rs = st.executeQuery(sentencia);
        
            while (rs.next()) {
                String line = rs.getString(1);
                System.out.println(line);
            }
        } catch (SQLException e) {
           
            e.printStackTrace();
        }

        
    }

}