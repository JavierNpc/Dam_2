public class App {

	private static final int port = 1337;
	private static final String host = "localhost";

	public static void main(String[] args) {
		// Launch server
		Servidor srv = new Servidor(port);
		Thread tServer = new Thread(srv);
		// Create client
		Cliente c = new Cliente(host, port);
		Thread cliente = new Thread(c);


		tServer.start();
		if (!c.connect()) {
			System.out.println("ERROR: Can't connect to server.");
			return;
		}
		cliente.start();

		try {
			cliente.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(c.receive());

		
		

		

	
		
	}
}
