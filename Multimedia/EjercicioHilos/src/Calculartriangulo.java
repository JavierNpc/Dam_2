public class Calculartriangulo implements Runnable{
    
    private Integer altura;
    private Integer base;

    
    public Calculartriangulo(Integer altura, Integer base) {
        this.altura = altura;
        this.base = base;
    }



    @Override
    public void run() {
        Thread hilo = Thread.currentThread();
        System.out.println(hilo.getName()+" = "+(base*altura)/2+" cm2\n") ;
       
        
    }
}
