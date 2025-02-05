package com.facturacion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;



public class ConexionMongo {
    int puerto ;
    String host ;  
    String user;
    String password;
    private String url ;  
    String database;
    MongoClient conexionMongo ;
    MongoDatabase db ;
    MongoCollection<Document> collection ;
    

    public ConexionMongo(String user,String password, String host, int puerto , String database) {
        this.puerto = puerto;
        this.host = host;
        url = "mongodb://"+user+":"+password+"@"+host+":"+puerto;
        this.database = database;
    }
    

    public void Conectar_Con_BBDD(){
        
        conexionMongo = MongoClients.create(url);
        db = conexionMongo.getDatabase(database);
        System.out.println("Conexion BBDD establecida");

    }


    public void Desconectar(){
        if (conexionMongo != null){
            conexionMongo.close();
            System.out.println("Conexion cerrada");
        }

    }


    public void insertar_Collecion(String nombreColeccion, int num_contratos){



        //· Conecxion/Creacion con coleccion
        //------------------------------------------------------


        MongoIterable<String> colecciones = db.listCollectionNames();
        boolean existe = false;
        for (String coleccion : colecciones){
            if (coleccion.equals(nombreColeccion)) {
                existe = true;
                break;
            }
        }
		if (existe)
            System.out.println("La colección " + nombreColeccion + " ya existe.");
        else {
            System.out.println("La colección " + nombreColeccion + " no existe y la creo a continuación.");
            db.createCollection(nombreColeccion);
        }

        collection = db.getCollection(nombreColeccion);
        System.out.println("Conexion Coleccion establecida");


        //------------------------------------------------------

    
        int num = num_contratos;
        Random random = new Random();
        int num_dias = 29;
        Document[] contrato = new Document[num+1];
        Document[] dia = new Document[num_dias+1];
        ArrayList<Document> dias = new ArrayList<>();

        //dias
            for (int c = 0; c <= num_dias ; c++) {
                dia[c] = new Document("M", random.nextInt(0,1000)).append("N", random.nextInt(0,1000));
                dias.add(dia[c]); 
            }
        //dias
        
        //consumos
        
            Document consumos = new Document("dias", dias);
            
        //consumos

        //cliente

            Document cliente = new Document("nombre", "Javier").append("Apellido", "Maceda");

        //cliente

        //contador
        
            Document contador = new Document("id_contador", "contador_X").append("consumo", consumos);
            
        //contador
      
        //Contrato
            for (int c = 0; c <= num_contratos ; c++) {
            
                contrato[c] = new 
                    Document("Id", c)
                    .append("Clientes", cliente)
                    .append("Contador", contador);

                collection.insertOne(contrato[c]);
            }

        //Contrato

    }

    public void bucar_contrato(int id){


    }


    public void eliminar_Collecion(String nombreColeccion) {

        //· Conecxion con coleccion
        //------------------------------------------------------
        MongoIterable<String> colecciones = db.listCollectionNames();
        boolean existe = false;
        for (String coleccion : colecciones){
            if (coleccion.equals(nombreColeccion)) {
                existe = true;
                break;
            }
        }

		if (!existe)
            System.out.println("La colección " + nombreColeccion + " no existe");
        else{
            collection = db.getCollection(nombreColeccion);
            System.out.println("Conexion Coleccion establecida");
        }
        //------------------------------------------------------

        

      
       


    }





 /*
            Enerro ( Collection)
                !contrato:[
                    -id:
                    -cliente: (Docummento)
                        ·nombre:
                        ·Apellido:
                    -contador: (Docummento)
                        ·id:
                        ·dias: (Array) (24)
                            .consumos: (Array) (Ramdom)
                                0:
                                    Wh:
                                1:  
                                    Wh:
                                ...
                    
                ]
        */



  
   
}
