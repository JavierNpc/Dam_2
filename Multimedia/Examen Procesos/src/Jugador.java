import java.time.Duration;
import java.util.ArrayList;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Jugador implements Runnable {

    Reloj tiempo;
    CyclicBarrier barrera;
    int partidaGanada = 0;
    ArrayList<Double> jugadas = new ArrayList<>();

    public Jugador(Reloj tiempo , CyclicBarrier barrera){
        this.tiempo = tiempo;
        this.barrera = barrera;
    }

    
    @Override
    public synchronized void run() {
        int cont = 0;
         do{ 
            try {
                Double t  = tiempo.generarTiempo();
               
               // Ganador g = new Ganador(t);
                String name = Thread.currentThread().getName();
                System.out.println(name+ " va ha dormir "+ t + " Segundos" );
                Thread.sleep((long)(t*1000));
                System.out.println(name+ " ha despertado");
                System.out.println(name+" ha llegado a la barrera y va ha esperar"); 
                barrera.await();
                //g.ganador();
                
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            } 
            cont++;
        }while (cont !=10); 
    
           
    }
    
}
