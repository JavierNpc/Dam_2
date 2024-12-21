public class App {

	private static final int port = 5000;
	private static final String host = "localhost";

	public static void main(String[] args) {

		// Create client
		Cliente c = new Cliente(host, port);
		Thread cliente = new Thread(c);
		cliente.start();


	

	}


}
