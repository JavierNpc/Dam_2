import java.util.Random;
import java.util.concurrent.CyclicBarrier;

public class App {
    public final static Integer MAX_JUGADORES = 6;
    static Reloj r;
    static CyclicBarrier barrera = new CyclicBarrier(6, () ->{
        System.out.println("\nTodos han llegado \n");
    });
    
    public static void main(String[] args) throws Exception {
        Jugador j[] = new Jugador[6];
      
        for(int i=0 ; i< MAX_JUGADORES ; i++){
            r = new Reloj();
            Thread hilo = new Thread(j[i] = new Jugador(r, barrera)); 
            hilo.setName("Jugador "+ i);
            hilo.start();
            
        }
     
    }

    
}
