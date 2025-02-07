package com.facturacion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.DirectoryStream.Filter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.bson.BsonDocument;
import org.bson.BsonReader;
import org.bson.Document;
import org.bson.codecs.BsonDocumentCodec;
import org.bson.conversions.Bson;
import org.bson.json.JsonWriterSettings;
import org.bson.types.ObjectId;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import com.mongodb.client.model.Filters;





public class ConexionMongo {
    int puerto;
    String host;
    String user;
    String password;
    private String url;
    String database;
    MongoClient conexionMongo;
    MongoDatabase db;
    MongoCollection<Document> collection;

    public ConexionMongo(String user, String password, String host, int puerto, String database) {
        this.puerto = puerto;
        this.host = host;
        url = "mongodb://" + user + ":" + password + "@" + host + ":" + puerto;
        this.database = database;
    }

    public void Conectar_Con_BBDD() {

        conexionMongo = MongoClients.create(url);
        db = conexionMongo.getDatabase(database);
        System.out.println("Conexion BBDD establecida");

    }

    public void Desconectar() {
        if (conexionMongo != null) {
            conexionMongo.close();
            System.out.println("Conexion cerrada");
        }

    }

   

    public void insertar_Collecion(String nombreColeccion, int num_contratos) {

        concectar_Coleccion_O_Crear(nombreColeccion);

        int num = num_contratos;
        Random random = new Random();
        int num_dias = 29;
        int num_horas = 23;
        Document[] contrato = new Document[num + 1];
        Document[] dia = new Document[num_dias + 1];
        Document[] hora = new Document[num_horas + 1];
        ArrayList<Document> dias = new ArrayList<>();
        ArrayList<Document> horas = new ArrayList<>();

        // horas
            for (int c = 0; c <= num_horas; c++) {
                hora[c] = new Document("Wh", random.nextInt(0, 1000));
                horas.add(hora[c]);
            }
        // horas

        // dias
            for (int c = 0; c <= num_dias; c++) {
                dia[c] = new Document("hora", horas);
                dias.add(dia[c]);
            }
        // dias

        // consumos

            Document consumos = new Document("dias", dias);

        // consumos

        // cliente

            Document cliente = new Document("nombre", "Javier").append("Apellido", "Maceda");

        // cliente

        // contador

            Document contador = new Document("id_contador", "contador_X").append("consumo", consumos);

        // contador

        // Contrato
        for (int c = 0; c <= num_contratos; c++) {

            contrato[c] = new Document("Id", c)
                    .append("Clientes", cliente)
                    .append("Contador", contador);

            collection.insertOne(contrato[c]);
        }

        // Contrato

    }

    public void bucar_contrato_coleccion(String nombreColeccion, int id_contrato) {
        MongoCollection<Document> coll =  concectar_Coleccion(nombreColeccion);
        Bson filter = Filters.eq("Id", 1);
        FindIterable<Document> doc = coll.find(filter);



    
      
    }

    public void eliminar_Collecion(String nombreColeccion) {
        // · Conexion/Creacion con coleccion
        MongoCollection<Document> doc =  concectar_Coleccion(nombreColeccion);


    }


    private void concectar_Coleccion_O_Crear(String nombreColeccion){
        
        // · Conexion/Creacion con coleccion

        MongoIterable<String> colecciones = db.listCollectionNames();
        boolean existe = false;
        for (String coleccion : colecciones) {
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

    }

    private MongoCollection<Document> concectar_Coleccion(String nombreColeccion){
        
        // · Conexion

        MongoIterable<String> colecciones = db.listCollectionNames();
        boolean existe = false;
        for (String coleccion : colecciones) {
            if (coleccion.equals(nombreColeccion)) {
                existe = true;
                break;
            }
        }

        if (existe)
            System.out.println("La colección " + nombreColeccion + " ya existe.");
        else {
            System.out.println("La colección " + nombreColeccion + " no existe");
            return null;
        }

        collection = db.getCollection(nombreColeccion);
        System.out.println("Conexion Coleccion establecida");
        return collection;

    }
    /*
     * Enerro ( Collection)
     * !contrato:[
        * -id:
        * -cliente: (Docummento)
            * ·nombre:
            * ·Apellido:
        * -contador: (Docummento)
            * ·id:
            * ·dias: (Array) (24)
                * ·consumos: (Array) (Ramdom)
                    * 0:
                    *    Wh:
                    * 1:
                    *    Wh:
                * ...
        * 
     * ]
     */

}
