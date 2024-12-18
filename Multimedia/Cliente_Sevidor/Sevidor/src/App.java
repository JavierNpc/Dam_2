public class App {

	private static final int port = 5000;
	private static final String host = "127.0.0.1";

	public static void main(String[] args) {

		
	

		// Launch server
		Servidor srv = new Servidor(port);
		Thread tServer = new Thread(srv);
		tServer.start();

		/* // Create client
		Cliente c = new Cliente(host, port);
		Thread cliente = new Thread(c);
		Thread cliente2 = new Thread(c);

		try {
			
			tServer.start();
			Thread.sleep(1000);
			cliente.start();

			cliente.join();

			cliente2.start();
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}  */

	

	}


}
