import java.util.concurrent.Semaphore;

public class Lanzador {
    public static void main(String[] args) {
        final int NUMERO_ESCRITORES = 5;
        final int MAX_RECURSOS = 1;
       
        Semaphore semaforo = new Semaphore(MAX_RECURSOS);
        for (int i = 0; i < NUMERO_ESCRITORES; i++) {
            new Thread(new Proceso(semaforo)).start();
        }
       
    }
}
