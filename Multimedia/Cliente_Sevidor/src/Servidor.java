import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;



class Servidor implements Runnable{
    private ServerSocket server = null; //Permite la conexion con otros dispositvos para recibir e interpretar mensages
    private Socket client = null; // Objto que nos permite contactar con un servidor remoto ( proporciona flujos de entrada y Salida)
    int port = 0;

    //Contructor (Envia el puerto que se va  a utilizar para la conexión)
    
	public Servidor(int port) {
		this.port = port;
	}
	
    
	@Override
	public void run(){

        InputStream is = null;
        InputStreamReader isr = null;
        BufferedReader bf = null;
        PrintWriter pw = null;
        OutputStream os = null;
        Thread tCLiente = null;
        
        System.out.println("Servidor Lanzandose");

        try {
        	server = new ServerSocket(port);
            client = server.accept(); // Espera al cliente para inicar una conexion
        
            System.out.println("Conexion Establecida\n");
        } catch (IOException e) {
        	System.out.println("ERROR: Unable to open socket on TCP " + port);
        	return;
        }
        
        
        while (true){
        	try {
				
                //Lectores y Escritores
                is = client.getInputStream(); // Esta linea lee la informacion del cliente
	            isr = new InputStreamReader(is);
	            bf = new BufferedReader(isr);
	            
                // Protocolo de mi programa
                String numero = bf.readLine();

                System.out.println(numero);



               /*  switch (Integer.parseInt(numero)) {
                    case 1:
                        Binario();
                        break;
                    case 2:
                        
                        break;
                    case 3:
                        
                        break;
                    case 4:
                        
                        break;
                    
                    default:
                        System.out.println("Comando no encontrado");
                        break;
                }
 */
                                
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
                
                
                
    private void Binario() {
       
       
    }

	
}