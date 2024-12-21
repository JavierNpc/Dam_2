import java.util.ArrayList;

public class App {

	private static final int port = 5000;
	private static final String host = "127.0.0.1";

	public static void main(String[] args) {

		

		int num = contarTexto("hola que hace ahora? y mañana que haras ?");
		System.out.println( num);
		

		/*
		 * // Launch server
		 * Servidor srv = new Servidor(port);
		 * Thread tServer = new Thread(srv);
		 * tServer.start();
		 */

	}

	public static Integer contarTexto(String men) {

		String texto = men.replace(",", "")
					.replace("'", "")
					.replace("(", "")
					.replace(")", "")
					.replace(".", "")
					.replace("?", "")
					.replace("  ", " ")
					;

		String[] palabras = texto.split(" ");

		return palabras.length;

	}

}
