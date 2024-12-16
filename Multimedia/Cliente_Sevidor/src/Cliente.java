import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Cliente implements Runnable  {
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
			System.out.println("CLIENT: Connected");
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
    }
    
    public synchronized boolean send(String message) {
    	try {	        
	        PrintWriter pw = new PrintWriter(socket.getOutputStream());
            pw.println(message);
            pw.flush();  
    		System.out.println("CLIENT: Message sent.");          
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
	String mens ;
	@Override
	public void run() {
		do {
			Scanner sc = new Scanner(System.in);
			System.out.print("Escreibe un mensaje -> ");
			mens = sc.nextLine();
			send(mens);
		} while (true);
			
	
	}
	
	
    
}