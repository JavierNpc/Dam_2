public class Calculartriangulo implements Runnable{
    
    private Double altura;
    private Integer base;

    
    public Calculartriangulo(Double altura, Integer base) {
        this.altura = altura;
        this.base = base;
    }



    @Override
    public void run() {
        Thread hilo = Thread.currentThread();
        Double resultado = (base*altura)/2;
        System.out.println(hilo.getName()+"  ("+base+" * "+altura+" / 2) = "+resultado+" cm2\n") ;
       
        
    }
}
