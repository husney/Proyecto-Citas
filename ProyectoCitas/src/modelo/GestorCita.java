package modelo;

import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class GestorCita {

    recursos.Conexion con;
    private Connection c;
    private PreparedStatement ps;

    public GestorCita() {
        con = new recursos.Conexion();
    }

    public void registrarCita(Cita cita) {
        c = null;
        ps = null;
        
        

        String sql = "INSERT INTO citas (citFecha, citHora, citMedico, Paciente, citConsultorio, citEstado, citObservaciones)"
                + "VALUES (?,?,?,?,?,?,?)";

        System.out.println("Medico: "+ cita.getMedico());
        System.out.println("Consultorio: "+ cita.getConsultorio());
        try {
            c = con.getConexion();
            ps = c.prepareStatement(sql);
            ps.setString(1, cita.getFecha());
            ps.setString(2, cita.getHora());
            ps.setString(3, cita.getMedico());
            ps.setString(4, cita.getPaciente());
            ps.setInt(5, cita.getConsultorio());
            ps.setString(6, cita.getEstado());
            ps.setString(7, cita.getObservaciones());
            System.out.println(sql);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Cita Registrada Correctamente");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en el registro de la cita");
        }

    }
    
    public DefaultTableModel buscarCitas(String parametro, String valor){
        c = null;
        ps = null;
        ResultSet rs;
        ResultSetMetaData rsMd;
        
        String where = "";
        if(!parametro.isEmpty() && !valor.isEmpty()){
            where = "WHERE "+parametro+" = '"+valor+"'";
        }
        
        String sql = "SELECT citNumero, citFecha, citHora, citConsultorio, medicos.medNombre, medicos.medApellidos, pacientes.pacNombre, pacientes.pacApellidos,citEstado, citObservaciones FROM citas INNER JOIN medicos ON citas.citMedico = medicos.medIdentificacion INNER JOIN pacientes\n" +
                     "ON citas.paciente = pacientes.pacIdentificacion "+where;
        
        DefaultTableModel modelo = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        String titulos[] = {"Numero Cita", "Fecha", "Hora", "Consultorio", "Nombre Medico", "Apellido Medico", "Nombre Paciente","Apellido Paciente", "Estado", "Observaciones"};
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
        }
        return modelo;
    }
}
