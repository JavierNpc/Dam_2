import java.util.ArrayList;

public class Ganador {
    
    Double t ;
    static ArrayList<Double> lista = new ArrayList<>();

    public Ganador(Double t){
        this.t = t;
        lista.add(t);
    }

    public void ganador(){
  /*       Double gandor = 0.0;
        for (int i = 1; i < lista.size(); i++) {
            if(lista.get(i-1)<lista.get(i)){  
                if (lista.get(i)<=10) {
                    gandor = lista.get(i);
                }  
                
            }
        }
        return gandor; */
        for (Double iterable_element : lista) {
            System.out.println(iterable_element);
        }
    }

}
