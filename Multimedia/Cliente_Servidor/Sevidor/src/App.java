

public class App {

	private static final int port = 5000;

	public static void main(String[] args) {

		// Launch server
		Servidor srv = new Servidor(port);
		Thread tServer = new Thread(srv);
		tServer.setName("Servidor 1");
		tServer.start();
		

	}



}
