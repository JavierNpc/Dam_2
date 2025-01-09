public class App {
     
    static int puerto = 1234;

    public static void main(String[] args) {
        
        Conexion conect = new Conexion(puerto);
        conect.Conectar();
    }
}
