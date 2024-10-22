import java.util.ArrayList;

public class App {

    static ArrayList<Palillos> palilloList = new ArrayList<>();
    public static void main(String[] args) {
        
        Integer NUM_HILOS = 5;
        Filosofo f[] = new Filosofo[NUM_HILOS];
        Palillos p[] = new Palillos[NUM_HILOS];

        for (int c=0 ; c<NUM_HILOS ; c++){
            p[c]= new Palillos();
            palilloList.add(p[c]);
            if(c==0){
            
            }else if (c==NUM_HILOS-1){
                f[c] = new Filosofo(palilloList.get(c-1),palilloList.get(0));
            }else{
                f[c-1] = new Filosofo(palilloList.get(c-1),palilloList.get(c));
            }
           
            Thread hilo = new Thread(f[c]);
            hilo.setName("Filosofo "+c+" ");
            hilo.start();
        }
        
        for (int c=0 ; c<NUM_HILOS ; c++){
           
            Thread hilo = new Thread(f[c]);
            hilo.setName("Filosofo "+c+" ");
            hilo.start();
        }
      
        

        
        
        

        
    }

}
