public class HilosCalculador implements Runnable {

    @Override
    public void run() {
        Thread hilo = Thread.currentThread();
        String miNombre = hilo.getName();
        int num = 0;
        while (num < 5) {
            System.out.println("\nCalculando "+miNombre+" ...");
            try {
                long tiempo = (long) (1000 * Math.random() * 3);
                if (tiempo > 2000) {
                    System.out.println("Terminando: " + miNombre +" Iteracion: "+ num + " Tiempo: "+ tiempo);
                    System.out.println();
                    hilo.join();
                }

                Thread.sleep(tiempo);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Calculado y reiniciando. " + miNombre);
            num++;
        }
     
        System.out.println("Hilo terminado: " + miNombre);
        System.out.println("");
        
    }

}
