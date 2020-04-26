package recursos;
import java.sql.*;


public class Conexion {
    
    Connection con;
    
    public Connection getConexion(){
        con = null;
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/citas", "root", "12345");
            if(con != null){
                System.out.println("Conexión exitosa");
            }
        } catch (Exception e) {
            System.out.println("Error en la conexión");
        }
        
        return con;
    }
    
}
