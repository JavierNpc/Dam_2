public class Calculartriangulo implements Runnable{
    
    private Integer altura;
    private Integer base;

    
    public Calculartriangulo(Integer altura, Integer base) {
        this.altura = altura;
        this.base = base;
    }



    @Override
    public void run() {
       System.out.println((base*altura)/2+" cm2") ;
       
        
    }
}
