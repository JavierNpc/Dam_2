package com.example;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;



public class ConexionMongo {
    int puerto ;
    String host ;  
    String user;
    String password;
    private String url ;  
    String database;
    MongoClient conexionMongo ;
    MongoDatabase db ;

    public ConexionMongo(String user,String password, String host, int puerto , String database) {
        this.puerto = puerto;
        this.host = host;
        url = "mongodb://"+user+":"+password+"@"+host+":"+puerto;
        this.database = database;
    }
    

    public void Conectar_Con_BBDD(){
        
        conexionMongo = MongoClients.create(url);
        db = conexionMongo.getDatabase(database);
        System.out.println("Conexion establecida");

    }

  

   



  
   
}
