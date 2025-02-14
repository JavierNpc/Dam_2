package com.facturacion.Mongo;

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
import com.mongodb.client.model.InsertOneModel;
import com.mongodb.client.model.WriteModel;

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

    public void eliminar_Collecion(String nombreColeccion) {
       
        MongoIterable<String> colecciones = db.listCollectionNames();
        boolean existe = false;
        for (String coleccion : colecciones) {
            if (coleccion.equals(nombreColeccion)) {
                existe = true;
                break;
            }
        }
        if(existe){
            collection = db.getCollection(nombreColeccion);
            System.out.println("Eliminando Coleccion "+ nombreColeccion);
            collection.drop();
        }else{
            System.out.println("No existe esa coleccion");
        }

    }


    //--------------------------------------------------------------------------


    public void crear_factura_coleccion(String nombreColecion) {
        eliminar_Collecion(nombreColecion + "_Facturas");
        //Creo la coleccion de el mes de facturas
        db.createCollection(nombreColecion + "_Facturas");
        
        MongoCollection<Document> Contrato = concectar_Coleccion(nombreColecion);
        MongoCollection<Document> facturas = concectar_Coleccion(nombreColecion + "_Facturas");
        MongoCursor<Document> cursor = Contrato.find().iterator();
        
        Random ram = new Random();
        Double[] precio = new Double[24]; // Solo 24 horas en un día
        List<Double> precios = new ArrayList<>(); // Lista para almacenar precios


        // Defino los precios para las facturas
        for (int i = 0; i < 24; i++) {
            precio[i] = 0.01 + (0.09 - 0.01) * ram.nextDouble();
            precios.add(precio[i]);
        }

        ArrayList<Document> facturas_list = new ArrayList<>(); // Defino un arrayList para la inseccion Masiva de datos

        //Recorro los contratos 
        while (cursor.hasNext()) {
            
            Document contrato_collecion = cursor.next(); // 1. Entro en el contrato
            Document contrato = new Document("id", contrato_collecion.get("id")); //. Creo Documento para guardar el id de cada contrato

            Document clientes_contrato = (Document) contrato_collecion.get("clientes"); // 2 Entro en clientes "subcarpeta de un contrato"
            Document clientes = new Document("nombre", clientes_contrato.get("nombre"))
                                .append("apellido", clientes_contrato.get("Apellido")); //. Creo Documento para guardar al Cliente de cada contrato

            Document contador = (Document) contrato_collecion.get("contador"); // 3. Entro en Contador
            Document consumo = (Document) contador.get("consumo"); // 3.1  Entro en consumo
            
            double totalFactura = 0.0;
            double totalConsumo = 0.0;
            int cont = 0;
            int cont_dias = 0;

            List<Document> detallesFactura = new ArrayList<>();

            for (Document dias : (Iterable<Document>) consumo.get("dias")) { // Recorro el documto dias que esta compuesto por arrays
                List<Integer> horaArray = (List<Integer>) dias.get("hora"); // Entro en cada hora de un dia
                double precioDia = 0.0;
                double consumoDia = 0.0;

                for (Integer hora : horaArray) {  // Recorro las horas de un dia
                    consumoDia += hora;
                    precioDia += hora * precio[cont]; // Guardo el precio de un dia 
                    cont = (cont + 1) % 24; // Restringo la cunta para que cuente hasta 24
                }

                totalConsumo += consumoDia;

                totalFactura += precioDia; //sumo todos los dia para sabes el preicio total de la factura del contrato

                detallesFactura.add(new Document("dia", cont_dias).append("monto", precioDia)); // inserto los dias y el precio de ese dia

                cont_dias = (cont_dias + 1) % 30;
            }

            Document factura = new Document("id_contrato", contrato.get("id")) // Creo un documeto con todos los datos para insertarlo en mi coleccion
                    .append("cliente", clientes)
                    .append("detalles", detallesFactura)
                    .append("consumoTotal", totalConsumo +" kw")
                    .append("precioTotal", totalFactura + " €");


            facturas_list.add(factura); //  Lo inseto en un Array Para hacer la inserccion masiva de datos

           //facturas.insertOne(factura); // Lo inseto en mi coleccion
        }

        facturas.insertMany(facturas_list);

    }
}