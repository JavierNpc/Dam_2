import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Ej_Examen {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
    
        try {
            System.out.println("Que directorio queires ver?");
            String ruta = "carpeta"; //sc.nextLine();
            
            Process pb = new ProcessBuilder("ls", "/home/javmaccas/"+ruta).start();
            System.out.println("");
           
            InputStream st = pb.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(st,"UTF-8"));
            
            while (br.readLine() != null) {
              
                System.out.println("  · "+br.readLine());

            }
            System.out.println("");
            
            br.close();
        } catch (IOException e) {
           
            e.printStackTrace();
        }

          
           
    
    }
}
