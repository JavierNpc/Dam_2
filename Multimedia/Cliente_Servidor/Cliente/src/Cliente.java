import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Cliente implements Runnable {
	String host = "";
	int port = 0;
	Socket servidor = null;
	DataInputStream in = null;
	DataOutputStream out = null;

	final String errorMSG = "ERROR";

	public Cliente(String host, int port) {
		this.host = host;
		this.port = port;
	}

	@Override
	public void run() {
		String enviar;
		String recibido;

		try {
			Scanner sc = new Scanner(System.in);
			boolean cond1 = true;
			boolean cond2 = true;
		
			connect();
			in = new DataInputStream(servidor.getInputStream());
			out = new DataOutputStream(servidor.getOutputStream());

			
			do {

				
				// .. Respuesta del servidor ....................
				recibido = in.readUTF();
				if (!recibido.equalsIgnoreCase("Exit")) {
					System.out.println(recibido);
				}
				// ..............................................
				
				

				// .. Respuesta del servidor ....................
				recibido = in.readUTF();
				if (!recibido.equalsIgnoreCase("Exit")) {
					System.out.println(recibido);
				}
				// ..............................................
				
				
			
				// -- Mensaje al Cliente ------------------------
				
					System.out.print("  ->  ");
					out.writeUTF(enviar = sc.nextLine());
					
			
				// ----------------------------------------------
				

			} while (!recibido.equalsIgnoreCase("Exit"));

			sc.close();
			in.close();
			out.close();
			

		} catch (IOException e) {
			System.out.println("\nEl cliente se ha desconectado\n ");
		}

	}

	public boolean connect() {
		try {
			servidor = new Socket(host, port);
			System.out.println("\nCLIENTE: Connectado");
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static void limpiarConsola() {  
		System.out.print("\033[H\033[2J");  
		System.out.flush();  
	} 

}