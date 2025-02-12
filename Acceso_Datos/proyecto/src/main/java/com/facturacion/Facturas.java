package com.facturacion;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;

public class Facturas {
    int puerto;
    String host;
    String user;
    String password;
    private String url;
    String database;
    MongoClient conexionMongo;
    MongoDatabase db;
    MongoCollection<Document> collection;
    ArrayList<Double> precios  = new ArrayList<>();
    List<List<Integer>> horasContrato = new ArrayList<>();

    public Facturas(String user, String password, String host, int puerto, String database) {
        this.puerto = puerto;
        this.host = host;
        url = "mongodb://" + user + ":" + password + "@" + host + ":" + puerto;
        this.database = database;
    }

    public Facturas(String host, int puerto, String database) {
        this.puerto = puerto;
        this.host = host;
        url = "mongodb://" + host + ":" + puerto;
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
     
        if (existe){
            System.out.println("La colección " + nombreColeccion + " existe.");
            return collection = db.getCollection(nombreColeccion);
        }else{
           System.out.println("La Coleccion no exixte");
        }
        return null;
    }


    //--------------------------------------------------------------------------


    public void crear_factura_coleccion(String nombreColecion){
        db.createCollection(nombreColecion+"_Facturas");
        MongoCollection<Document> Mes = concectar_Coleccion(nombreColecion);
        MongoCollection<Document> facturas =  concectar_Coleccion(nombreColecion+"_Facturas");
        MongoCursor<Document> cursor = Mes.find().cursor();
        Double factura_contrato = 0.0; 
        Double[] precio = new Double[24];
        Random ram = new Random();
        int cont = 0;

        //Document cliente 
        

        while (cursor.hasNext()) {
            for (int c = 0; c <= 23; c++) {
                precio[c] =  ram.nextDouble(0.01, 0.09);
                precios.add(precio[c]);
            }
            Document contrato_collecion = cursor.next();
            Document contrato = new Document("id",contrato_collecion.get("id"));

            System.out.println(contrato);
           

            Document clientes_contrato = (Document) contrato.get("clientes");
            Document clientes = new Document("nombre",contrato.get("nombre")).append("apellido", contrato.get("apellido"));

           

            Document contador = (Document) contrato.get("contador");
            Document consumo = (Document) contador.get("consumo");

            /* for (Document dias :  (Iterable<Document>)  consumo.get("dias")) {

                List<Integer> horaArray = (List<Integer>) dias.get("hora"); 
                double precioContrato = 0.0 ;
                precioContrato = horaArray.get(cont) * precio[cont];
                Document factura = new Document("factura", precioContrato);
                cont++;
            }  */
        }

       

    }




}
