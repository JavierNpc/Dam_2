import java.util.ArrayList;

public class App {

    static ArrayList<Palillos> palilloList = new ArrayList<>();
    public static void main(String[] args) {
        
        Integer NUM_HILOS = 5;
        Filosofo f[] = new Filosofo[NUM_HILOS];
        Palillos p[] = new Palillos[NUM_HILOS];

        for (int c=0 ; c<NUM_HILOS ; c++){
            p[c]= new Palillos();
            p[c].setNum_palillo(c);
            palilloList.add(p[c]);
           
        
        }

        for (int c=0 ; c<NUM_HILOS ; c++){

            if(c==NUM_HILOS-1){
                f[c] = new Filosofo(palilloList.get(c),palilloList.get(0));
                Thread hilo = new Thread(f[c]);
                hilo.setName("Filosofo "+c);
                hilo.start();
            }else{
                f[c] = new Filosofo(palilloList.get(c),palilloList.get(c+1));
                Thread hilo = new Thread(f[c]);
                hilo.setName("Filosofo "+c);
                hilo.start();
            }
        }
    }      
}
