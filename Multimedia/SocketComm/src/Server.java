import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {
    private ServerSocket server = null;
    private Socket client = null;
    int port = 0;

    
    public Server(int port) {
        this.port = port;
    }


    @Override
    public void run() {
        InputStream is = null;
        InputStreamReader isr = null;
        BufferedReader reader = null;
        PrintWriter pw = null;
        OutputStream os = null;

        System.out.println("Info = server launching");


        try {
            server = new ServerSocket(port);
        } catch (IOException e) {
            System.out.println("ERROR : No es posibli aceder al socket del puerto :"+ port);
            return;
        }
        
        while (true) {
            try {
                client = server.accept();
                System.out.println("Server : connection establecida");

                is = client.getInputStream();
                isr = new InputStreamReader(is);
                reader = new BufferedReader(isr);


                //Read mes
                System.out.println("SERVIDOR : Esperando");
                String mes = reader.readLine();
                System.out.println("SERVIDOR : Message recibido");

               // Get answer
				String answer = getAnswer(mes);
				
				// Write answer
				os = client.getOutputStream();
	            pw = new PrintWriter(os);
	            pw.write(answer);
	            pw.flush();
				System.out.println("SERVER: Message sent");
	            
	            // Close handlers
				pw.close();
				os.close();
				reader.close();
				isr.close();
				is.close();
				client.close();



            } catch (Exception e) {
                System.out.println("ERROR: Failed connecting to client");
                e.printStackTrace();
            }
        }
       
    }

    private String getAnswer(String message) {
        return new StringBuilder(message).reverse().toString();
    }
}
