import java.util.ArrayList;

public class App {

	private static final int port = 5000;

	public static void main(String[] args) {

		// Launch server
		Servidor srv = new Servidor(port);
		
		srv.Conexion();
		

		
		

	}


	public static ArrayList<Integer> factorizacionPrima(int numero){
       	int div = 2;
		ArrayList<Integer> factores = new ArrayList<>();
		int fact = numero;
	   	while (fact>1) {
			if(fact%div == 0){
				factores.add(div);
				fact = fact/div;
			}else{
				div++;
			}
	   	}

	
		for (int i = 0; i < factores.get(factores.size()-1) ; i++) {
			if (factores.contains(i)) {
				System.out.println(i);
			}
			
			
			
		}


 		
        return factores;
    }


}
