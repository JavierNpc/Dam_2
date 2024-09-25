import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Lector extends Thread {
    public Process pb;

    public Lector(Process pb2) {
        pb=pb2;
    }


    @Override
    public void run() {
     
        lectorproceso();
       
        
    }
    
  
    public void lectorproceso(){

        InputStream st = pb.getInputStream();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(st,"UTF-8"))) {
            
            while (br.readLine() != null) {
                System.out.println(br.readLine());
            }
            
            br.close();
        } catch (IOException e) {
           
            e.printStackTrace();
        }
    }
}
