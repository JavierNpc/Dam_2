import java.util.ArrayList;

public class Casino {
    private int dinero_C = 50000;

    public int num_crupier = dar_Numero();
   
    public static ArrayList<Jugadores> apuesta_normal = new ArrayList<>();
    public static ArrayList<Jugadores> apuesta_pares = new ArrayList<>();
    public static ArrayList<Jugadores> martingala = new ArrayList<>(); 

    Casino(ArrayList<Jugadores> apuesta_normal, ArrayList<Jugadores> apuesta_pares, ArrayList<Jugadores> martingala){
        this.apuesta_normal = apuesta_normal;
        this.apuesta_pares = apuesta_pares;
        this.martingala = martingala;
    }

    private Integer dar_Numero(){
        int num = (int) (Math.random()*36);
        return num ;
        
    }
    

    
    
   

    
}
