package modelo;
import java.sql.*;
import javax.swing.JOptionPane;

public class GestorTratamiento {
    
    recursos.Conexion con;
    private Connection c;
    private PreparedStatement ps;
    
    public GestorTratamiento(){
        con = new recursos.Conexion();
    }
    
    
    public void registrar(Tratamiento tratamiento){
        c = null;
        ps = null;
        
        String sql = "INSERT INTO tratamientos (traFechaAsignado, traFechaInicio, traFechaFin, traDescripcion, traObservaciones, traPaciente)"
                   + "VALUES (?,?,?,?,?,?)";
        
        
        try {
            c = con.getConexion();
            ps = c.prepareStatement(sql);
            ps.setString(1, tratamiento.getFechaAsignado());
            ps.setString(2, tratamiento.getFechaInicio());
            ps.setString(3, tratamiento.getFechaFin());
            ps.setString(4, tratamiento.getDescripcion());
            ps.setString(5, tratamiento.getDescripcion());
            ps.setString(6, tratamiento.getPaciente());
            ps.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Tratamiento registrado correctamente");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al registrar tratamiento");
        }
    }
}
