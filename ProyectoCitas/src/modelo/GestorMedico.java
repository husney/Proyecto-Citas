package modelo;
import java.sql.*;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class GestorMedico {
    
    private recursos.Conexion con;
    private PreparedStatement ps;
    private Connection c;
    
    
    public GestorMedico(){
        con = new recursos.Conexion();
    }
    
    
 
    public void registrarMedico(Medico medico){
        
        String sql = "INSERT INTO medicos (medIdentificacion, medNombre, medApellidos)"
                   + "VALUES (?,?,?)";
        
        try {
            c = con.getConexion();
            ps = c.prepareStatement(sql);
            ps.setString(1, medico.getIdentificacion());
            ps.setString(2, medico.getNombres());
            ps.setString(3, medico.getApellidos());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Medico Registrado Correctamente");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al registrar medico");
        }
        
        
    }
    
    public DefaultTableModel buscarMedico(String parametro, String valor){
        c = null;
        ps = null;
        ResultSet rs;
        ResultSetMetaData rsMd;
        
        DefaultTableModel modelo = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
            
            
        };
        String where = "";
        if(!parametro.isEmpty() && !valor.isEmpty()){
            where = "WHERE "+parametro+" = '"+valor+"'";
        }
        
        String sql = "SELECT * FROM medicos "+where+ "ORDER BY medNombre";
        System.out.println(sql);
        
        String titulos[] = {"Identificacion", "Nombres", "Apellidos"};
        modelo.setColumnIdentifiers(titulos);
        
        try {
            c = con.getConexion();
            ps = c.prepareStatement(sql);
            rs = ps.executeQuery();
            rsMd = rs.getMetaData();
            
            while(rs.next()){
                Object resultados[] = new Object[rsMd.getColumnCount()];
                
                for(int i = 0; i < rsMd.getColumnCount(); i++){
                    resultados[i] = rs.getObject(i+1);
                }
                modelo.addRow(resultados);
            }
            
        } catch (Exception e) {
            System.out.println("Error en busqueda medico");
        }
        
        return modelo;
    }
    
    public Vector agregarMedicos(){
        Vector<Medico> medicos = new Vector<Medico>();
        Medico med;
        
        c = null;
        ps = null;
        ResultSet rs;
      
        
        String sql = "SELECT  medIdentificacion, medNombre, medApellidos  FROM medicos ORDER BY medNombre";
        System.out.println(sql);
       
        try {
            c = con.getConexion();
            ps = c.prepareStatement(sql);
            rs = ps.executeQuery();
            med = new Medico();
            
            med.setNombres("Seleccione el medico");
            med.setApellidos("");
            med.setIdentificacion("0");
            medicos.add(med);
            
            while(rs.next()){
                med = new Medico();
                med.setIdentificacion(rs.getString("medIdentificacion"));
                med.setNombres(rs.getString("medNombre"));
                med.setApellidos(rs.getString("medApellidos"));
                medicos.add(med);
            }
            
            
        } catch (Exception e) {
            System.out.println("Error de busqueda Medicos");
        }
        
        return medicos;
    }
    
    public void generarReporte(){
         c = null;
         
         JasperReport report = null;
         String path = "src/reportes/ReportesMedicos.jasper";
         
         try {
            c = con.getConexion();
            report = (JasperReport) JRLoader.loadObjectFromFile(path);
            JasperPrint print = JasperFillManager.fillReport(path, null, c);
            JasperViewer vista = new JasperViewer(print, false);
            vista.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            vista.setVisible(true);
            
        } catch (Exception e) {
        }
    }
}
