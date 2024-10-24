import java.util.ArrayList;
import java.util.Random;

/* 
Filosofo 4 : 16
Filosofo 2 : 14
Filosofo 3 : 15
Filosofo 1 : 13
Filosofo 0 : 12 
*/


public class Filosofo implements Runnable {
    private Palillos palillo_derecha;
    private Palillos palillo_izquierda;
    
    String miNombre;


    public Filosofo(Palillos palillo_derecha, Palillos palillo_izquierda) {
        this.palillo_derecha = palillo_derecha;
        this.palillo_izquierda = palillo_izquierda;
    }

    @Override
    public void run() {
        
        int id = Integer.valueOf((int) Thread.currentThread().getId());
        miNombre = Thread.currentThread().getName();
        Random generador = new Random();
        System.out.println(miNombre+" : "+id);
        

        while (true) {
            switch (id) {
                case 12:
                    if(palillo_derecha.isActivo() == true && palillo_izquierda.isActivo() == true ){
                        System.out.println("\n"+miNombre + " comiendo...");
                        esperarTiempoAzar(miNombre, (1 + generador.nextInt(5)) * 1000);
                        palillo_derecha.setActivo(false);
                        palillo_izquierda.setActivo(false);
                        System.out.println("\n"+miNombre + " pensando...");
                        esperarTiempoAzar(miNombre, (1 + generador.nextInt(5)) * 1000); 
                    }
                    break;
                case 13:
                    if(palillo_derecha.isActivo() == true && palillo_izquierda.isActivo() == true ){
                        System.out.println("\n"+miNombre + " comiendo...");
                        esperarTiempoAzar(miNombre, (1 + generador.nextInt(5)) * 1000);
                        palillo_derecha.setActivo(false);
                        palillo_izquierda.setActivo(false);
                        System.out.println("\n"+miNombre + " pensando...");
                        esperarTiempoAzar(miNombre, (1 + generador.nextInt(5)) * 1000); 
                    }
                    break;
                case 14:
                    if(palillo_derecha.isActivo() == true && palillo_izquierda.isActivo() == true ){
                        System.out.println("\n"+miNombre + " comiendo...");
                        esperarTiempoAzar(miNombre, (1 + generador.nextInt(5)) * 1000);
                        palillo_derecha.setActivo(false);
                        palillo_izquierda.setActivo(false);
                        System.out.println("\n"+miNombre + " pensando...");
                        esperarTiempoAzar(miNombre, (1 + generador.nextInt(5)) * 1000); 
                    }
                    break;
                case 15:
                    if(palillo_derecha.isActivo() == true && palillo_izquierda.isActivo() == true ){
                        System.out.println("\n"+miNombre + " comiendo...");
                        esperarTiempoAzar(miNombre, (1 + generador.nextInt(5)) * 1000);
                        palillo_derecha.setActivo(false);
                        palillo_izquierda.setActivo(false);
                        System.out.println("\n"+miNombre + " pensando...");
                        esperarTiempoAzar(miNombre, (1 + generador.nextInt(5)) * 1000); 
                    }
                    break;
                case 16:
                    if(palillo_derecha.isActivo() == true && palillo_izquierda.isActivo() == true ){
                        System.out.println("\n"+miNombre + " comiendo...");
                        esperarTiempoAzar(miNombre, (1 + generador.nextInt(5)) * 1000);
                        palillo_derecha.setActivo(false);
                        palillo_izquierda.setActivo(false);
                        System.out.println("\n"+miNombre + " pensando...");
                        esperarTiempoAzar(miNombre, (1 + generador.nextInt(5)) * 1000); 
    
                    }
                    break;
            }
        
           

            palillo_derecha.setActivo(true);
            palillo_izquierda.setActivo(true);


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
        return miNombre+ " [p_der = " + palillo_derecha.getNum_palillo() + ",  p_izq = " + palillo_izquierda.getNum_palillo() +"]";
    }


    

}