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
import recursos.Conexion;

public class gestorPaciente {
    private recursos.Conexion con;
    
    public gestorPaciente(){
        con = new Conexion();
    }
    
    private PreparedStatement ps;
    private Connection c;
    
    public void registrarPaciente(Paciente paciente){
        ps = null;
        c = null;
        String sql = "INSERT INTO pacientes (pacIdentificacion, pacNombre, pacApellidos, pacFechaNacimiento, pacGenero)"
                   + "VALUES (?,?,?,?,?)";
        
        try {
            c = con.getConexion();
            ps = c.prepareStatement(sql);
            ps.setString(1, paciente.getIdentificacion());
            ps.setString(2, paciente.getNombre());
            ps.setString(3, paciente.getApellidos());
            ps.setString(4, paciente.getFechaNacimiento());
            ps.setString(5, paciente.getGenero());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Registrado Correctamente");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en el Registro");
            e.printStackTrace();
        }finally{
            try {
                c.close();
            } catch (Exception e) {
            }
        }
        
    }
    
    public DefaultTableModel buscar(String parametro, String valor){
        ps = null;
        c  = null;
        ResultSet rs;
        ResultSetMetaData rsMd;
        DefaultTableModel modelo  = new DefaultTableModel(){
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
                
            };
        
        String where = "";
         if(!parametro.isEmpty() && !valor.isEmpty()){
            where = "WHERE "+parametro+ " = '"+valor+"'";
        } 
        
        String sql = "SELECT * FROM pacientes "+where;
        System.out.println(sql);
        
        try {
            c = con.getConexion();
            ps = c.prepareStatement(sql);
            rs = ps.executeQuery();
            rsMd = rs.getMetaData();
            
            String titulos[] = {"Identificacion", "Nombres", "Apellidos", "Fecha de Nacimiento", "Genero"};
            modelo.setColumnIdentifiers(titulos);
            
            
            while(rs.next()){
                Object resultados[] = new Object[rsMd.getColumnCount()];
                
                for(int i = 0; i < rsMd.getColumnCount(); i++){
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
        String path = "src/reportes/ReportesPacientes.jasper";
        
        try {
            c = con.getConexion();
            report = (JasperReport) JRLoader.loadObjectFromFile(path);
            JasperPrint jprint = JasperFillManager.fillReport(path,null,c);
            
            JasperViewer vista = new JasperViewer(jprint,false);
            
            vista.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            vista.setVisible(true);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        
    }
}
