package com.example;

public class Main  {

  
    public static void main(String[] args) {
        String BBDD = "ciclismo";
        String url = "jdbc:postgresql://localhost:5432/";
        String user = "mati";
        String password = "mati";
    
      
       /*  ConexionPostgres postgres = new ConexionPostgres(BBDD, url, user, password);

        postgres.Conectar_Con_BBDD();

        postgres.Sentencia_Select("Select * from maillot"); */

        ConexionMongo mongo = new ConexionMongo("mati","mati", "localhost", 27017, "basedatos1" );

        mongo.Conectar_Con_BBDD();
        

    }
}




/* 
Connection conexion = null;

try {
    Class.forName("org.postgresql.Driver");
    conexion = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ciclismo","mati", "mati");
    System.out.println("conexion Establecida");

    Statement st = conexion.createStatement();
    ResultSet rs = st.executeQuery("Select * from maillot");
    
    while (rs.next()) {
        String line = rs.getString(1);
        System.out.println(line);
    }
    


} catch (ClassNotFoundException cnfe) {
    cnfe.printStackTrace();
} catch (SQLException sqle) {
    sqle.printStackTrace();
}finally{
    try {
        conexion.close();
        conexion = null;
    } catch (SQLException sqle) {
        sqle.printStackTrace();
    }
}
 */