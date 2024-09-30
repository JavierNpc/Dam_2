public class App  {
    public static void main(String[] args) throws Exception {
     
        Calculartriangulo cal ;

        for (int i = 0; i <=10; i++) {
            cal = new Calculartriangulo(3, i);
            Thread hilo = new Thread(cal);
            hilo.start();
        }
      

    }
}
