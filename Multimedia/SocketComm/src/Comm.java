import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Comm {
    private  final static String host = "localhost";
    private static final int port = 1337;
    
    public static void main(String[] args) throws Exception {

        //Servidor
        Server srv = new Server(port);
        Thread tServer = new Thread(srv);
        tServer.run();
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
           e.printStackTrace();
        }


        //parte Cliente
        Client c = new Client(host, port);
        if (!c.connect()) {
            System.out.println("Error al intentar conectarse al servidor");
            return;
        }
        c.send("Hola mundo");
        String res = c.receive();
        System.out.println(res);

    }

}

    class Client {
        String host = "";
        int port = 0;
        private Socket socket = null;
        private InputStreamReader isr = null;
        private BufferedReader brf = null;
        final String errorMSg = "CLIENT ERROR";

        public Client(String host, int port) {
            this.host = host;
            this.port = port;
        }

       

        public boolean connect(){
            try {
                socket= new Socket(host,port);
                System.out.println("CLIENT connected");
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        }

        public String receive(){
            try {
                isr = new InputStreamReader(socket.getInputStream());
                brf = new BufferedReader(isr);
                String res = brf.readLine() ;
                System.out.println("CLIENTE : Mensaje enviado");
               
                isr.close();
                brf.close();
                return res;

            } catch (Exception e) {
                e.printStackTrace();
            }
            return "ERROR: No se ha enviado el mesaje";
        }

        public boolean send(String mens){

            try {
                PrintWriter pw = new PrintWriter(socket.getOutputStream());
                pw.println(mens);
                pw.flush();
                System.out.println("Client: Mesage Enviado");
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        
        }
        



      
        
    }

