package modelo;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

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
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al registrar tratamiento");
        }
    }
    
    public DefaultTableModel buscar(String parametro, String valor){
        c = null;
        ps = null;
        ResultSet rs;
        ResultSetMetaData rsMd;
        
        String where = "";
        if(!parametro.isEmpty() && !valor.isEmpty()){
            where = "WHERE "+parametro+ " = '"+valor+"'";
        }
        
        String sql = "SELECT traNumero, traFechaAsignado, traFechaInicio, traFechaFin, traPaciente, traDescripcion, traObservaciones FROM tratamientos "+where+ " ORDER BY traNumero";
        
        DefaultTableModel modelo = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
            
        };
        
        try {
            c = con.getConexion();
            ps = c.prepareStatement(sql);
            rs = ps.executeQuery();
            rsMd = rs.getMetaData();
            
            String titulos[] = {"Numero", "Fecha Asignado6", "Fecha Inicio", "Fecha Fin", "Paciente", "Descripcion", "Observaciones"};
            modelo.setColumnIdentifiers(titulos);
           
            
            
            
            while(rs.next()){
                Object resultados[] = new Object[rsMd.getColumnCount()];
                
                for(int i = 0; i < rsMd.getColumnCount() ; i++){
                    resultados[i] = rs.getObject(i+1);
                }
                modelo.addRow(resultados);
            }
        } catch (Exception e) {
        }
        return modelo;
    }
    
    public void generarReporte(){
        c = null;
        
        JasperReport report = null;
        String path = "src/reportes/ReportesTratamientos.jasper";
        
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
