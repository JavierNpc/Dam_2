package com.facturacion;

public class Main {
    public static void main(String[] args) {
       
        ConexionMongo mongo = new ConexionMongo("mati","mati", "localhost", 27017, "GestioneEnegetica" );

        mongo.Conectar_Con_BBDD();

        mongo.insertar_Collecion("Enero",100);

       // mongo.eliminar_Collecion("Enero");

       mongo.bucar_contrato_coleccion("Enero", 1);
       
       mongo.Desconectar();

        



    }
}