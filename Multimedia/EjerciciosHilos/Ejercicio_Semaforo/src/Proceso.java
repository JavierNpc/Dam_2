import java.util.concurrent.Semaphore;

public class Proceso implements Runnable {
    private String name;
    private int numero;
    private Semaphore semaforo;

    public Proceso(Integer numero ,Semaphore semaforo) {
        this.semaforo = semaforo;
        this.numero = numero;
    }


   

    private void sumar() {
        try {
            semaforo.acquire();
        
            int al = (int)(Math.random()*(10-5)+5);
            System.out.println();
            System.out.println("Sumar : " +al);
            
            for(int i = 0 ; i<=al ; i++ ){
                System.out.println(name+" :"+numero);
                numero= numero +1;
            }
            
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } finally {
            semaforo.release();
        }
    }


    @Override
    public void run() {
       this.name = Thread.currentThread().getName();
        sumar();
    }
            
               
}