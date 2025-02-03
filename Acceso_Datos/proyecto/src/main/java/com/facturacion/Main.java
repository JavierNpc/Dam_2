package com.facturacion;

public class Main {
    public static void main(String[] args) {
       
        ConexionMongo mongo = new ConexionMongo("mati","mati", "localhost", 27017, "GestioneEnegetica" );

        mongo.Conectar_Con_BBDD();

        mongo.Conectar_Collection("Contratos");

        
        



    }
}