import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;


public class mostrarEnKonsola {
    static Lector l1 ;
    public static void main(String[] args) throws Exception {
        
        
        try {
           
            Process pb = new ProcessBuilder("konsole").start();
            l1 = new Lector(pb);
            l1.run();
        
        } catch (IOException e) {
            e.printStackTrace();
        }

        
        
       
    }
}
