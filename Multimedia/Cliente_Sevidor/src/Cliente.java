import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Cliente {

    String host = "";
	int port = 0;
	Socket socket = null;
	InputStreamReader isr = null;
	BufferedReader bfr = null;
	final String errorMSG = "ERROR";
	
    public Cliente(String host, int port){
    	this.host = host;
    	this.port = port;
    }
    
    public boolean connect() {
        try {
			socket = new Socket(host, port);
			System.out.println("CLIENTE -> Conectado");
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
    }
    
    public boolean send(String message) {
    	try {	        
	        PrintWriter pw = new PrintWriter(socket.getOutputStream());
            pw.println(message);
            pw.flush();  
    		System.out.println("CLIENTE -> Message sent.");          
            return true;	        
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
    }
    
    public String receive() {
		try {
			isr = new InputStreamReader(socket.getInputStream());
	        bfr = new BufferedReader(isr);
	        String ans = bfr.readLine();
			System.out.println("CLIENT: Message received");
	        return ans;
		} catch (IOException e) {
			e.printStackTrace();
			return errorMSG;
		}
    }
    
}