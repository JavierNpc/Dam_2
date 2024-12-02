public class Reloj  {
    
    Double tiempo;

    public Double generarTiempo(){
        tiempo = (Math.random()*(12-5)+5);
        return tiempo;
    }

}
