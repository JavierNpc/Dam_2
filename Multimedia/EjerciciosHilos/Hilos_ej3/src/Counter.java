public class Counter {
    public int count;


    // Contrutor
    public Counter(){
        this.count=0;
    }

    // Getters y Setters
    public int getCount() {
        return count;
    }
    
    
    //Funciones

    protected synchronized void incrementar(){
        
        count = count + 1 ;
    }


  
}

