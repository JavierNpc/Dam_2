package com.facturacion;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bson.Document;
import org.bson.conversions.Bson;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.InsertOneModel;
import com.mongodb.client.model.Updates;
import com.mongodb.client.model.WriteModel;





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
        Integer[] hora = new Integer[num_horas + 1];
        ArrayList<Document> dias = new ArrayList<>();
        ArrayList<Integer> horas = new ArrayList<>();
   
       

        // horas
            for (int c = 0; c <= num_horas; c++) {
                hora[c] =  random.nextInt(0, 1000);
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

        List<WriteModel<Document>> operaciones = new ArrayList<>();
        
        for (int c = 0; c <= num_contratos; c++) {
          

            contrato[c] = new Document("id", c)
                    .append("clientes", cliente)
                    .append("contador", contador);

            operaciones.add(new InsertOneModel<Document>(contrato[c]));
        }

        collection.bulkWrite(operaciones);

        // Contrato

    }

    //--------------------------------------------------------------------

    public void Factura_De_Coleccion(String nombreColeccion){
        MongoCollection<Document> coleccionVariable = concectar_Coleccion(nombreColeccion);
        Bson filterBusqueda = Filters.eq("id", 1);
        MongoCursor<Document> cursor = coleccionVariable.find().cursor() ;
        int cont = 0;
        while (cursor.hasNext()) {
            cont++;
            Document contrato = cursor.next();
            Document contador = (Document) contrato.get("contador");
            Document consumo = (Document) contador.get("consumo");

          



            /* Gson gson = new GsonBuilder().setPrettyPrinting().create();
            try {
                File arch1 = new File("/home/javmaccas/Escriptori/Dam_2/Acceso_Datos/proyecto/src/main/java/com/facturacion/ach2.json");
                FileWriter fWriter = new FileWriter(arch1);
              
                System.out.println("Buscando Contrato");
                
                String json = gson.toJson(consumo);  
                fWriter.write(json);
                System.out.print(json);

            } catch ( Exception e) {
                System.out.println("\nNo existe esa colecion\n");
            } */

            /* for ( Document contador  : (Iterable<Document>) contrato.get("contador")){
                for( Document consumo : (Iterable<Document>) contador.get("consumo")){
                    ArrayList<Document> dias = new ArrayList<>();
                    dias.add(consumo);
                    for( Document  dia : dias){
                        ArrayList<Document> horas = new ArrayList<>();
                        horas.add(dia);
                        System.out.println(horas.toString());
                    }
                }
            }
 */
        }
        
        System.out.println(cont);


       
        


    }





    //--------------------------------------------------------------------



    public void update_nombre_contrato_coleccion(String nombreColeccion, int id, String newNombre) {

        MongoCollection<Document> col = concectar_Coleccion(nombreColeccion);
        Bson filterBusqueda = Filters.eq("id", id);
        Bson filterRemplazo = Updates.set("clientes.nombre", newNombre);


        try {

            col.updateOne(filterBusqueda, filterRemplazo);

        } catch ( Exception e) { 
            System.out.println("\nNo existe esa colecion\n");
        }
      
    }

    public void delete_nombre_contrato_coleccion(String nombreColeccion, String nombre) {

        MongoCollection<Document> col = concectar_Coleccion(nombreColeccion);
        FindIterable<Document> doc = null ;
        Bson filterBusqueda = Filters.eq("clientes.nombre", nombre);
   
        try {

            col.deleteOne(filterBusqueda);

        } catch ( Exception e) { 
            System.out.println("\nNo existe esa colecion\n");
        }
      
    }

    public void bucar_contrato_coleccion(String nombreColeccion, int id_contrato) {

        MongoCollection<Document> col = concectar_Coleccion(nombreColeccion);
       
        FindIterable<Document> doc = null ;
        Bson filter = Filters.eq("id", id_contrato);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            File arch1 = new File("/home/javmaccas/Escriptori/Dam_2/Acceso_Datos/proyecto/src/main/java/com/facturacion/ach1.json");
            FileWriter fWriter = new FileWriter(arch1);
          
            doc = col.find(filter);

            System.out.println("Buscando Contrato");
            for (Document document : doc) {
                String json = gson.toJson(document);  
                fWriter.write(json);
                System.out.print(json);
            }
   
        } catch ( Exception e) {
            System.out.println("\nNo existe esa colecion\n");
        }
      
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


    public void bucar_dato_contrato_coleccion(String nombreColeccion, int id_contrato) {

        MongoCollection<Document> col = concectar_Coleccion(nombreColeccion);
       
        FindIterable<Document> doc = null ;
        Bson filter = Filters.eq("id", id_contrato);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            File arch1 = new File("/home/javmaccas/Escriptori/Dam_2/Acceso_Datos/proyecto/src/main/java/com/facturacion/ach1.json");
            FileWriter fWriter = new FileWriter(arch1);
          
            doc = col.find(filter);

            System.out.println("Buscando Contrato");
            for (Document document : doc) {
                String json = gson.toJson(document);  
                fWriter.write(json);
                System.out.print(json);
            }
   
        } catch ( Exception e) {
            System.out.println("\nNo existe esa colecion\n");
        }
    }
      

    
    //--------------------------------------------------------------

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
