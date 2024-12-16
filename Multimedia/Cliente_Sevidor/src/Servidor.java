import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

class Servidor implements Runnable {
    private ServerSocket server = null;
    private Socket client = null;
    int port = 0;

    public Servidor(int port) {
        this.port = port;
    }

    @Override
    public void run() {

        InputStream is = null;
        InputStreamReader isr = null;
        BufferedReader bf = null;
        PrintWriter pw = null;
        OutputStream os = null;
        boolean cond = true;

        System.out.println("INFO: Server launching...");

        try {
            server = new ServerSocket(port);
        } catch (IOException e) {
            System.out.println("ERROR: Unable to open socket on TCP " + port);
            return;
        }

        while (cond != false) {
            try {
                client = server.accept();
                System.out.println("OK: Conection stablished!");

                is = client.getInputStream();
                isr = new InputStreamReader(is);
                bf = new BufferedReader(isr);

                // Read message
                System.out.println("\nSERVER: Waiting...");
                String message = bf.readLine();

                System.out.println("SERVER: Message received\n");
                while (true) {
                    // switch
                    switch (message) {
                        case "/o":
                            System.out.println("\nSERVER: Waiting2...");
                            String message2 = bf.readLine();

                            String answer = getAnswer(message);

                            os = client.getOutputStream();
                            pw = new PrintWriter(os);
                            pw.write(answer);
                            pw.flush();
                            System.out.println(answer);

                            break;
                        case "/p":
                            pw.write("arroz");
                            pw.flush();

                        default:
                            cond = false;
                            break;
                    }
                }

                // Close handlers
               
               

            } catch (IOException e) {
                System.out.println("ERROR: Failed connecting to client");
                e.printStackTrace();
            }
        }
    }

    private String getAnswer(String message) {
        return new StringBuilder(message).reverse().toString();
    }
}
