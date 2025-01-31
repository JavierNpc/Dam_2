package com.example;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;



public class ConexionMongo {
    int puerto ;
    String host ;  
    String database;
    MongoClient conexionMongo ;
    MongoDatabase db ;

    public ConexionMongo(int puerto, String host , String database) {
        this.puerto = puerto;
        this.host = host;
        this.database = database;
    }
    

    public void Conectar_Con_BBDD(){
        conexionMongo = new MongoClient(host, puerto);
        db = conexionMongo.getDatabase(database);
        System.out.println("Conexion establecida");

    }

  

   



  
   
}
