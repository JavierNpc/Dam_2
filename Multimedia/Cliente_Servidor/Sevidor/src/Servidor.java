import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

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
                out.writeUTF("\n--- Conexion con servidor realizada ---");
                do {

                    mensaje = pedirDatos(); // - Se queda esperando al mensaje del cliente [2]
                    switch (mensaje) {
                        case "/a":
                            out.writeUTF("1º Metodo escogido -> Contador de palabras de un texto");
                            mensaje = pedirDatosString();
                            Integer numPalabras = contarTexto(mensaje);
                            out.writeUTF("\nEl texto tine = " + numPalabras);
                            break;
                        case "/b":
                            out.writeUTF("2º Metodo escogido -> Conversion De Entero a Binario");
                            mensajeInt = pedirDatosInt();
                            String numero = binario(mensajeInt);
                            out.writeUTF("\nEl numero " + mensajeInt + " en binario es = " + numero);

                            break;
                        case "/c":
                            out.writeUTF("\n3º Metodo para Generar una Contraseña de una longitud especifica");
                            mensajeInt = pedirDatosInt();
                            String contraseña = generarContraseña(mensajeInt);
                            
                            if (mensajeInt < 5) {
                                out.writeUTF("\nLa contraseña debe tener mas de 5 digitos");
                            }else{
                                out.writeUTF("\nLa contraseña es = " + contraseña);
                            }

                            break;
                        case "/d":
                            out.writeUTF("4º Metodo escogido -> Factorizacion Prima De Entero ");
                            mensajeInt = pedirDatosInt();
                            String factorizado = factorizacionPrima(mensajeInt);
                            out.writeUTF("\nEl numero " + mensajeInt + " factorizado es = " + factorizado);
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

    public String pedirDatosString() {
        try {
            DataOutputStream out = new DataOutputStream(client.getOutputStream());
            DataInputStream in = new DataInputStream(client.getInputStream());
            out.writeUTF("\nDame un texto"); // . Mensaje al Cliente

            return in.readUTF(); // - Se queda esperando al mensaje del cliente

        } catch (Exception e) {
            System.out.println("No se ha podido madar la informacion");
            return " ";
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

    public static String generarContraseña(int num) {
        String contraseña = "";
        int c = 0;
        int aleatorio = 0;
        String[] abced = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "ñ", "o", "p", "q","r", "s", "t", "u", "v", "w", "x", "y", "z" };
        do {
            aleatorio = (int) (Math.random() * (3) + 1);
            switch (aleatorio) {
                case 1:
                    contraseña = contraseña + (int) (Math.random() * (9) + 1);
                    break;
                case 2:
                    contraseña = contraseña + abced[(int) (Math.random() * (27))];
                    break;
                case 3:
                    contraseña = contraseña + abced[(int) (Math.random() * (27))].toUpperCase();
                    break;
            }

            c++;
        } while (c != num);
    
        return contraseña;
    }

    public static String factorizacionPrima(int numero){
        boolean condition = true;
        String numeroF = "";
        int num_2 = 0;
        int num_3 = 0;
        int num_5 = 0;
        int num_7 = 0;
        do {
            if (numero%2 == 0) {
                num_2 = num_2 + 1;
				numero /= 2;
            }else if (numero%3 == 0) {
                num_3 = num_3 + 1;
				numero /= 3;
            }else if (numero%5 == 0) {
                num_5 = num_5 + 1;
				numero /= 5;
            }else if (numero%7 == 0) {
                num_7 = num_7 + 1;
				numero /= 7;
            }else if (numero == 5) {
                num_5 = num_5 + 1;
				condition = false;
            }else if (numero == 7) {
				num_7 = num_7 + 1;
				condition = false;
			}if (numero == 2) {
                num_2 = num_2 + 1;
				condition = false;
            }else if (numero == 1) {
				condition = false;
			}else if (numero == 0) {
				condition = false;
			}
            
        } while (condition != false);


		if (num_2 > 0) {
			if (num_2 !=1) {
				numeroF = numeroF + " . (2^"+num_2+")";	
			}else{
				numeroF = numeroF + " . (2)";	
			}
		}
		if (num_3 > 0) {
			if (num_3 !=1) {
				numeroF = numeroF + " . (3^"+num_3+")";
			}else{
				numeroF = numeroF + " . (3)";	
			}

		}
		if (num_5 > 0) {
			if (num_5 !=1) {
				numeroF = numeroF + " . (5^"+num_5+")";
			}else{
				numeroF = numeroF + " . (5)";	
			}

		}
		if (num_7 > 0) {
			if (num_7 !=1) {
				numeroF = numeroF + " . (7^"+num_7+")";
			}else{
				numeroF = numeroF + " . (7)";	
			}

		}
		

        return  numeroF.replace(".", "*").substring(3);
    }

    public static Integer contarTexto(String men) {

		String texto = men.replace(",", "")
					.replace("'", "")
					.replace("(", "")
					.replace(")", "")
					.replace(".", "")
					.replace("?", "")
					.replace("  ", " ")
					;

		String[] palabras = texto.split(" ");

		return palabras.length;

	}

}
