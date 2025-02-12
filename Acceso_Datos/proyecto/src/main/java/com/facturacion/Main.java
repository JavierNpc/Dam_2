package com.facturacion;

public class Main {
    public static void main(String[] args) {
     
        ConexionMongo mongo = new ConexionMongo("mati","mati", "localhost", 27017, "GestioneEnegetica" );
        Facturas facturas = new Facturas("mati","mati", "localhost", 27017, "GestioneEnegetica" );

        // ConexionMongo mongo = new ConexionMongo( "localhost", 27017, "GestioneEnegetica" ); //   Casa
        
        mongo.Conectar_Con_BBDD();

        mongo.eliminar_Collecion("Enero");

        mongo.insertar_Collecion("Enero",100);

        /*  mongo.update_nombre_contrato_coleccion("Enero", 1, "Dani");

        mongo.delete_nombre_contrato_coleccion("Enero", "Dani");  

        mongo.Factura_De_Coleccion("Enero");


        mongo.Desconectar(); */

        facturas.Conectar_Con_BBDD();

        facturas.crear_factura_coleccion("Enero");


        //·   POSTGRES                                          


        



    }
}