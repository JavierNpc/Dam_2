import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


class Servidor {
    private ServerSocket server = null;
    public Socket conexion = null;
    int port = 0;
    

    public Servidor(int port) {
        this.port = port;
    }

    public void Conexion(){
        int cont= 0;
        try {
            server = new ServerSocket(port);
            
            while (true) {
                cont++;
                System.out.println("\nEsperando a cliente");
                Socket conexion = server.accept(); //. Espera a una conexion
                System.out.println("Conexion recibida");
                ServidorConexion s = new ServidorConexion(conexion);
                Thread hilo = new Thread(s);
                hilo.setName("Servdor "+cont);
                hilo.start();
                
            }


        } catch (IOException e) {
            System.out.println("ERROR: Unable to open socket on TCP " + port);
            return;
        }

        
    }

  

}