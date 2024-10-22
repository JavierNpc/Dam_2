import java.util.ArrayList;
import java.util.Random;

public class Filosofo implements Runnable {
    public Palillos palillo_derecha;
    public Palillos palillo_izquierda;


    public Filosofo(Palillos palillo_derecha, Palillos palillo_izquierda) {
        this.palillo_derecha = palillo_derecha;
        this.palillo_izquierda = palillo_izquierda;
    }

    public void run() {

        String miNombre = Thread.currentThread().getName();
        Random generador = new Random();
        while (true) {
            // Comer 
            // Intentar coger palillos
            System.out.println("\n"+miNombre + " comiendo...");
            esperarTiempoAzar(miNombre, (1 + generador.nextInt(5)) * 1000);
            // Pensando... 
            // Recordemos soltar los palillos
            System.out.println("\n"+miNombre + " pensando...");
            esperarTiempoAzar(miNombre, (1 + generador.nextInt(5)) * 1000);
        } 
    }

    private synchronized void esperarTiempoAzar(String miNombre, int milisegs) {
        System.out.println(" Va ha esperar: "+milisegs/1000+" seg");

        try {
            Thread.sleep(milisegs);
        } catch (InterruptedException e) {
            System.out.println(miNombre + " interrumpido!!. Saliendo...");
            return;
        }
       
    }

    @Override
    public String toString() {
        return "Filosofo [palillo_derecha=" + palillo_derecha + ", palillo_izquierda=" + palillo_izquierda
                + ", getClass()=" + getClass() + "]";
    }


    

}