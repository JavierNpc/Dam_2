public class Actividad1_hilos  {
    public static void main(String[] args) throws Exception {
     

        Calculartriangulo tCal[] = new Calculartriangulo[11];
        
        for (int i = 1; i <= 10; i++) {
          
            tCal[i] = new Calculartriangulo(10,i);
            Thread hilo = new Thread(tCal[i]);
            hilo.setName("Hilo "+i);
            hilo.start();
   
        }

        HilosCalculador hCal[] = new HilosCalculador[5];
        for (int i = 0; i < 5; i++) {
          
            hCal[i] = new HilosCalculador();
            Thread hilo = new Thread(hCal[i]);
            hilo.setName("Hilo "+i);
            System.out.println("GetName: "+hilo.getName());
            hilo.start();

            if (i==0){
                hilo.setPriority(Thread.MAX_PRIORITY);
            }else hilo.setPriority(Thread.MIN_PRIORITY);
            
        }
    }
}
