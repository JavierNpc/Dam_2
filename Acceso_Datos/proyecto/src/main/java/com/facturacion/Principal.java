package com.facturacion;


import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bson.Document;

import com.mongodb.ConnectionString;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;

public class Principal {

	public void Ejemplo() {

		try {

			// PASO 1: Conexión al Server de MongoDB Pasandole el host y el puerto
			// ConnectionString connectionString = new
			// ConnectionString("mongodb://mati:mati@localhost:27017/?authSource=admin");
			ConnectionString connectionString = new ConnectionString("mongodb://mati:mati@localhost:27017");
			MongoClient mongoClient = MongoClients.create(connectionString);
			// PASO 2: Conexión a la base de datos

			MongoDatabase db = mongoClient.getDatabase("GestioneErgetica");
			System.out.println("Conectado");

			// PASO 3.0: Creamos una coleccion para trabajar con ella si no existiera
			// previamente

			// Nombre de la colección que queremos verificar
			String nombreColeccion = "Contratos";
			MongoIterable<String> colecciones = db.listCollectionNames();
			boolean existe = false;
			for (String coleccion : colecciones)
				if (coleccion.equals(nombreColeccion)) {
					existe = true;
					break;
				}

			if (existe)
				System.out.println("La colección " + nombreColeccion + " ya existe.");
			else {
				System.out.println("La colección " + nombreColeccion + " no existe y la creo a continuación.");
				db.createCollection(nombreColeccion);
			}

			// PASO 3.1: Obtenemos una coleccion para trabajar con ella
			MongoCollection<Document> collection = db.getCollection(nombreColeccion);

			

			Document cliente = new Document("nombre", "Javier").append("Apellido", "Maceda");
			 
			for (int i = 0; i < 30; i++) {
				Document contador = new Document("id",13214 ).append("fecha", new Date()).append("dia", i).append("consumo", 1);
			}
			

			// Crear el documento de auditoría con los accesos y los elementos generales
			// accedidos
			Document contratos = new Document("id", 1).append("contador", cliente);

			// Insertar el documento en la colección

			
			collection.insertOne(contratos);
			System.out.println("Documento de auditoría insertado con éxito!");

			// Consultar el documento insertado
			// Document encontrado = collection.find(new Document("usuario",
			// "usuario123")).first();
			FindIterable<Document> documentos = collection.find(new Document("usuario", "usuario123"));
			int i=0;
			for (Document encontrado : documentos) {				
				if (encontrado != null) {//Este if es por si hacemos una búsqueda sencilla de un documento 
					i++;
					
					System.out.println("\nDocumento encontrado: "+ i);
					//System.out.println(encontrado.toJson());
					String usuario = encontrado.getString("usuario");
					System.out.println("Usuario: " + usuario);

					// Acceder a los accesos (con sus elementos accedidos)
					for (Object acceso : encontrado.getList("accesos", Document.class)) {
						Document accesoDoc = (Document) acceso;
						Date fecha = accesoDoc.getDate("fecha");
						String ip = accesoDoc.getString("ip");
						String protocolo = accesoDoc.getString("protocolo");
						List<String> elementosAccedidos = accesoDoc.getList("elementosAccedidos", String.class);

						System.out.println("Acceso - Fecha: " + fecha + ", IP: " + ip + ", Protocolo: " + protocolo);
						System.out.println(
								"Elementos Accedidos en este acceso: " + String.join(", ", elementosAccedidos));
					}

					// Acceder al listado general de elementos accedidos
					List<String> elementosGenerales = encontrado.getList("elementosAccedidosGenerales", String.class);
					System.out.println(
							"Listado general de elementos accedidos: " + String.join(", ", elementosGenerales));
				}
			}

		} finally {

		}

	}
}
