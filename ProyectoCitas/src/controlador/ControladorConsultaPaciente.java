package controlador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.JTable;

public class ControladorConsultaPaciente implements ActionListener{
    
   
    private modelo.gestorPaciente gestorPaciente;
    private modelo.Paciente paciente;
    private vista.ConsPacienteInternalFrame consultaPacienteVista;
    
    public ControladorConsultaPaciente(vista.ConsPacienteInternalFrame vista){
     gestorPaciente = new modelo.gestorPaciente();
     paciente = new modelo.Paciente();
     this.consultaPacienteVista = vista;
     
     this.consultaPacienteVista.btnBuscar.addActionListener(this);
     this.consultaPacienteVista.btnCerrar.addActionListener(this);
     buscar();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.consultaPacienteVista.btnBuscar){
            buscar();
        }
    }
    
    public void buscar(){
        String parametro = "";
        String valor = this.consultaPacienteVista.txtBuscar.getText();
        
        if(this.consultaPacienteVista.rdbIdentificacion.isSelected()){
            parametro = "pacIdentificacion";
        } else if(this.consultaPacienteVista.rdbNombre.isSelected()){
            parametro = "pacNombre";
        } else if(this.consultaPacienteVista.rdbApellido.isSelected()){
            parametro = "pacApellidos";
        } else if(this.consultaPacienteVista.rdbGenero.isSelected()){
            parametro = "pacGenero";
        }
        
        
        
        this.consultaPacienteVista.tabla.setModel(gestorPaciente.buscar(parametro, valor));
        
        int anchos[] = {50,50,50,80,40};
        for(int i = 0; i < this.consultaPacienteVista.tabla.getColumnCount(); i++){
            this.consultaPacienteVista.tabla.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
        }
      
    }
    
    
    
    
}
