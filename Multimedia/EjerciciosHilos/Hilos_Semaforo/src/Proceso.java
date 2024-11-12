import java.util.concurrent.Semaphore;

public class Proceso implements Runnable {
    private String name;
    private int tipo;
    private Semaphore semaforo;

    public Proceso(String name, int tipo, Semaphore semaforo) {
        this.name = name;
        this.tipo = tipo;
        this.semaforo = semaforo;
    }

    public void leer(){
        System.out.println(this.name + " intentando leer");

        try {
            semaforo.acquire(); // Inicio seccción critica

            System.out.println(this.name + " leyendo");
            Thread.sleep((long)(Math.random()*20));
            System.out.println(this.name + " Ya he leido");

        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            semaforo.release(); // fin de la sección critica
        }
    }

    private void escribir() {
        System.out.println(this.name + " intentando escribir");

        try {
            semaforo.acquire();

            System.out.println(this.name + " escribiendo");
            Thread.sleep((long) (Math.random() * 50));
            System.out.println(this.name + " Ya he escrito");

        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } finally {
            semaforo.release();
        }
    }


    @Override
    public void run() {
        if(tipo == 0){
            leer();
        } else {
            escribir();
        }
    }
            
               
}
