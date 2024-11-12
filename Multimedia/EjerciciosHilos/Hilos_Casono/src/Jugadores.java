public class Jugadores implements Runnable{
    private String nombre;
    private Integer dinero_J = 1000;
    private int num_jugador = dar_Numero();


   

    // Getter y Setter

    public int getDinero_J() {
        return dinero_J;
    }

    public void setDinero_J(int dinero_J) {
        this.dinero_J = dinero_J;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    //toString

    @Override
    public String toString() {
        return "\nJugadores [nombre=" + nombre + ", dinero_J=" + dinero_J + "]";
    }

    @Override
    public void run() {
        
    }


    private Integer dar_Numero(){
        int num = (int) (Math.random()*36);
        return num ;
        
    }
}
