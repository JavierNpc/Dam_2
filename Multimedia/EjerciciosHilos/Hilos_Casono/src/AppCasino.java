import java.util.ArrayList;

public class AppCasino {
    
    public static Integer NUM_JUGADORES = 4;
    static ArrayList<Jugadores> apuesta_normal = new ArrayList<>();
    static ArrayList<Jugadores> apuesta_pares = new ArrayList<>();
    static ArrayList<Jugadores> martingala = new ArrayList<>(); 

    static Jugadores[] jugador_n = new Jugadores[NUM_JUGADORES];
    static Jugadores jugador_p[] = new Jugadores[NUM_JUGADORES];
    static Jugadores jugador_m[] = new Jugadores[NUM_JUGADORES];
    static Casino casino ;

    public static void main(String[] args) throws Exception {
       
        for (int i = 0; i < NUM_JUGADORES; i++) {
            jugador_n[i] = new Jugadores();
            jugador_p[i] = new Jugadores();
            jugador_m[i] = new Jugadores();

            jugador_n[i].setNombre(" J_Normal " + i);
            jugador_p[i].setNombre(" J_Pares " + i);
            jugador_m[i].setNombre(" J_Martingala " + i);

           /*  Thread hilo_n = new Thread(jugador_n[i]);
            Thread hilo_p = new Thread(jugador_p[i]);
            Thread hilo_m = new Thread(jugador_m[i]);
        
            hilo_n.setName(" J_Normal " + i);
            hilo_p.setName(" J_Pares " + i);
            hilo_m.setName(" J_Martingala " + i);

            hilo_n.start();
            hilo_p.start();
            hilo_m.start();
 */
            apuesta_normal.add(jugador_n[i]);
            apuesta_pares.add(jugador_p[i]);
            martingala.add(jugador_m[i]); 

        
        }

        casino = new Casino(apuesta_normal, apuesta_pares, martingala);

       

       


       


    }
}

