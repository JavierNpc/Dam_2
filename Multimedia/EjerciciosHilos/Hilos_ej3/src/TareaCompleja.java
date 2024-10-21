public class TareaCompleja implements Runnable {
    Counter c1 ;

    public TareaCompleja(Counter c){
        c1= c;
        
    }

    @Override
    public void run() {
        
        Thread hiloActual = Thread.currentThread();
        String miNombre = hiloActual.getName();
    
        c1.incrementar();
        System.out.println("Finalizado el hilo" + miNombre+". Valor del contador = "+c1.getCount());
    }

}
