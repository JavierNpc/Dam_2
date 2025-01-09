import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Conexion {
    public static Socket client ;
    public int puerto = 0;
        
        public Conexion (int puerto){
            this.puerto = puerto;
        }
    
    public void Conectar() {
        try {
            ServerSocket servidor = new ServerSocket(puerto);
            while (true) {
                System.out.println("Esperando Cliente");
                client = servidor.accept();
                System.out.println("conexion conseguida");
                Sevidor serv = new Sevidor(client);
                Thread tServ = new Thread(serv);
                tServ.start();
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        


    }
}
