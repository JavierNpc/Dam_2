public class Actividad1_hilos  {
    public static void main(String[] args) throws Exception {
     

        Calculartriangulo tCal[] = new Calculartriangulo[11];
        
        for (int i = 1; i <= 10; i++) {
        
            tCal[i] = new Calculartriangulo(3.0,i);
            Thread hilo = new Thread(tCal[i]);
            hilo.setName("Hilo "+i);
            hilo.start();
         
   
        }

       
    }
}
