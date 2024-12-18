public class App {

	private static final int port = 5000;
	private static final String host = "192.168.1.46";

	public static void main(String[] args) {

		// Create client
		Cliente c = new Cliente(host, port);
		Thread cliente = new Thread(c);
		cliente.start();


/* 
		 // Launch server
		Servidor srv = new Servidor(port);
		Thread tServer = new Thread(srv);
		
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
