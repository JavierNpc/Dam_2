import java.io.IOException;
import java.util.Scanner;


public class RuneTime {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        boolean condicion = false;
        try {
            Process pb = Runtime.getRuntime().exec("konsole");
            do {
                System.out.println("Quieres terminar este proceso? Si o No");
                String res = sc.nextLine();
                if (res.equalsIgnoreCase("no")) { 
                    condicion = true;
                } else if (res.equalsIgnoreCase("Si")) {
                    System.out.println("El PID del proceso es : "+pb.pid()+"\n");
                    System.out.println("La informacion del proceso es : "+pb.info());
                    System.out.println("Destruyendo el proceso");
                    contar(3);
                    pb.destroy();
                    condicion = true;
                } else {
                    contar(2);
                }
            } while (condicion != true);
           
          
            System.out.println("Esperando ha que cierres el proceso");
            int resultado = pb.waitFor();  
            int resultado2 = pb.exitValue();
            System.out.println(resultado==resultado2);
    
            System.out.println(pb.toString()+" cerrado, Adios ;)");
            
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        sc.close();
        
        
        
    }

    private static void contar(int c)  {
        System.out.print("[");
        for (int i = 0; i <= c; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
            System.out.print("==");
        }
        limpiarPanatalla();
    }

    public static void limpiarPanatalla() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}
