public class App {
    
	private static final int port = 1337;
	private static final String host = "localhost";
	
	public static void main(String[] args) {
		// Launch server
		Servidor srv = new Servidor(port);
		Thread tServer = new Thread(srv);
		tServer.start();
		
		
		//Create client
		Cliente c = new Cliente(host, port);
		if(!c.connect()) {
			System.out.println("ERROR: Can't connect to server.");
			return;
		} 


		c.send("Hola sladbdbbd");
        c.send("dvsb");
        c.send("Javier");
        c.receive();
      
	}
}

