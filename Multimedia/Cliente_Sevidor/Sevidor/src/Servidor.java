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

        DataInputStream in = null;
        DataOutputStream out = null;
        String mensaje;
        int mensajeInt;

        System.out.println("INFO: Server launching...");

        try {
            server = new ServerSocket(port);
        } catch (IOException e) {
            System.out.println("ERROR: Unable to open socket on TCP " + port);
            return;
        }

        while (true) {
            try {
                System.out.println("\n--> Esperando a un cliente ...");
                client = server.accept(); // . Se queda esperando a que se concte un cliente
                System.out.println("--------- OK: Conection stablished! ----------");

                out = new DataOutputStream(client.getOutputStream());
                in = new DataInputStream(client.getInputStream());
                boolean condition = true;

                // . Se incia la conversacion
                do {

                    mensaje = pedirDatos(); // - Se queda esperando al mensaje del cliente [2]
                    switch (mensaje) {
                        case "/a":
                            mensajeInt = pedirDatosInt();
                            String numero = binario(mensajeInt);
                            out.writeUTF("\n- El numero " + mensajeInt + " en binario es = " + numero);
                            break;
                        case "/b":
                            out.writeUTF(" - Has seleccionado la opcion : b ");
                            
                            break;
                        case "/c":
                            out.writeUTF(" - Has seleccionado la opcion : c ");
                            
                            break;
                        case "/d":
                            out.writeUTF(" - Has seleccionado la opcion : d ");
                        
                            break;
                        case "Exit":
                            out.writeUTF("Exit");
                            client.close();
                            condition = false;
                            break;
                        default:
                            out.writeUTF("Esa opcion no esta disponible");
                            break;
                    }
                } while (condition != false);

                System.out.println("Cliente Desconectado");

            } catch (IOException e) {
                System.out.println("ERROR: Failed connecting to client");
                e.printStackTrace();
            }
        }
    }

    public String pedirDatos() {
        try {
            DataOutputStream out = new DataOutputStream(client.getOutputStream());
            DataInputStream in = new DataInputStream(client.getInputStream());
            out.writeUTF("\nIntroduce tu comando "); // . Mensaje al Cliente

            return in.readUTF(); // - Se queda esperando al mensaje del cliente

        } catch (Exception e) {
            System.out.println("No se ha podido madar la informacion");
            return " ";
        }
    }

    public void terminar() {
        try {
            DataOutputStream out = new DataOutputStream(client.getOutputStream());
            DataInputStream in = new DataInputStream(client.getInputStream());
            
            in.readUTF(); // - Se queda esperando al mensaje del cliente

        } catch (Exception e) {
            System.out.println("No se ha podido madar la informacion");
            
        }
    }

    public Integer pedirDatosInt() {
        try {
            DataOutputStream out = new DataOutputStream(client.getOutputStream());
            DataInputStream in = new DataInputStream(client.getInputStream());
            out.writeUTF("Dime un numero"); // . Mensaje al Cliente

            return Integer.parseInt(in.readUTF()); // - Se queda esperando al mensaje del cliente

        } catch (Exception e) {
            System.out.println("No se ha podido madar la informacion");
            return 0;
        }
    }

    public static String binario(int mens) {

        int numero = mens;
        String bin = "";
        boolean cond = true;
        do {
            if (numero % 2 == 0) {
                numero = numero / 2;
                bin = bin + "0";
            } else {
                numero = numero / 2;
                bin = bin + "1";
            }
            if (numero == 0) {
                bin = bin + "0";
                cond = false;
            }
            if (numero == 1) {
                bin = bin + "1";
                cond = false;
            }
        } while (cond != false);

        bin = new StringBuilder(bin).reverse().toString();
        return bin;

    }

}
