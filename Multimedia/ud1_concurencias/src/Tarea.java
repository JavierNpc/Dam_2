public class Tarea extends Thread{
   
    @Override
    public void run(){
        for (int i = 0 ; i<500 ; i++){
            System.out.println("\n"+Thread.currentThread().getName()+"\n"+ i);
        }
    }

}
