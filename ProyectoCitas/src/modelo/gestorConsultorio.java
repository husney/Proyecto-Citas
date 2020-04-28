package modelo;
import java.awt.Window;
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

public class gestorConsultorio {
    
    private recursos.Conexion con;
    private Connection c;
    private PreparedStatement ps;
    
    public gestorConsultorio(){
        con = new recursos.Conexion();
    }
    
    public void registrarConsultorio(Consultorio consultorio){
        c = null;
        ps = null;
        String sql = "INSERT INTO consultorios (conNumero, conNombre)"
                   + "VALUES (?,?)";
        
        try {
            c = con.getConexion();
            ps = c.prepareStatement(sql);
            ps.setInt(1, consultorio.getNumero());
            ps.setString(2, consultorio.getNombre());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Consultorio Registrado Correctamente");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en el registro del consultorio");
        }
        
    }
    
    public DefaultTableModel buscarConsultorio(String parametro, String valor){
        
        String where = "";
        
        if(!parametro.isEmpty() && !valor.isEmpty()){
            where = "WHERE "+parametro+ " = '"+valor+"'";
        }
        
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
        
        String sql = "SELECT * FROM consultorios "+where+ "ORDER BY conNumero";
        System.out.println(sql);
        
        String nombres[] = {"Numero", "Nombre"};
        modelo.setColumnIdentifiers(nombres);
        
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
        }
        
        return modelo;
    }
    
    public Vector llenarConsultorios(){
        
        Vector<Consultorio> consultorios = new Vector<Consultorio>();
        Consultorio cons;
        
        c = null;
        ps = null;
        ResultSet rs;
        
        String sql ="SELECT * FROM consultorios";
        
        try {
            c = con.getConexion();
            ps = c.prepareStatement(sql);
            rs = ps.executeQuery();
            cons = new Consultorio();
            cons.setNombre("Seleccione el Consultorio");
            cons.setNumero(0);
            consultorios.add(cons);
            
            while(rs.next()){
                cons = new Consultorio();
                cons.setNumero(rs.getInt("conNumero"));
                cons.setNombre(rs.getString("conNombre"));
                consultorios.add(cons);
            }
            
        } catch (Exception e) {
        }
        return consultorios;
    }
    
    public void generarReporte(){
        c = null;
        
        JasperReport report = null;
        String path = "src/reportes/ReportesConsultorios.jasper";
        
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
