import java.beans.EventHandler;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.Scanner;

public class Sevidor implements Runnable {
    Socket cliente = null;
    BufferedReader bReader ;
    PrintWriter pWriter;
    public Sevidor(Socket client) {
        this.cliente = client;
    }

    
			
    @Override
    public void run() {
        String mesajeRecibido = "";
        String mandarMesaje = "";
        Scanner sc = new Scanner(System.in);
        try {
            InputStream is = cliente.getInputStream();
            OutputStream os = cliente.getOutputStream();
            InputStreamReader isr = new InputStreamReader(is);
            OutputStreamWriter osw = new OutputStreamWriter(os);
            bReader = new BufferedReader(isr);  // Recibir Mesaje
            pWriter = new PrintWriter(osw); // Enviar Mesaje
            boolean condition = true;
            // Comineza el protocolo
            System.out.println("--- Conexion Establecida ---");

            EnviarMensaje("Bienvenido");

            do{
                System.out.println(mesajeRecibido = RecibirMensaje());

                switch (mesajeRecibido) {
                    case "/time":
                        mandarMesaje = Fecha();
                        EnviarMensaje(mandarMesaje);
                        break;
                    case "/wait":
                        mandarMesaje = Esperar();
                        EnviarMensaje(mandarMesaje);
                        break;
                    case "/quit":
                        condition = false;  
                        EnviarMensaje("Bye");
                        cliente.close();
                        break;
                    default:
                        EnviarMensaje("not_recognized");
                        break;
                }
    
            } while (condition == true);
        } catch (IOException e) {
            System.out.println("erroror");
            e.printStackTrace();
        }
     

        
    }


    public String Esperar(){
        int waitTime = new Random().nextInt(3);
        System.out.println(waitTime);
        try {
            Thread.sleep(waitTime * 1000);
        } catch (InterruptedException e) {
            
            e.printStackTrace();
        }
        return "El cliente h esperado " + waitTime + " segundos";
    }

    public String Fecha(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        return LocalDateTime.now().format(dtf);
        
    }

    public void EnviarMensaje(String mensaje){
        pWriter.println(mensaje);
        pWriter.flush();
        
    }
    
    public String RecibirMensaje(){
         try {
           return bReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
       return null;
    }

    
}
