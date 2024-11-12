import java.util.concurrent.Semaphore;

public class Lanzador {
    public static void main(String[] args) {
        final int NUMERO_LECTORES = 2;
        final int NUMERO_ESCRITORES = 5;
        final int MAX_RECURSOS = 1;
        Semaphore semaforo = new Semaphore(MAX_RECURSOS);
        for (int i = 0; i < NUMERO_ESCRITORES; i++) {
            new Thread(new Proceso("Escritor" + i, 1, semaforo)).start();
        }
        for (int i = 0; i < NUMERO_LECTORES; i++) {
            new Thread(new Proceso("Lector" + i, 0, semaforo)).start();
        }
    }
}
