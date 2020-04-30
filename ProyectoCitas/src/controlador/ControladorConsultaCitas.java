package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

public class ControladorConsultaCitas implements ActionListener{
    
    
    modelo.GestorCita gestorCita;
    vista.ConsultarCitas consultarCitas;
  
    public ControladorConsultaCitas(vista.ConsultarCitas vista){
        
        gestorCita = new modelo.GestorCita();
        this.consultarCitas = vista;
        
        this.consultarCitas.btnBuscar.addActionListener(this);
        this.consultarCitas.btnBuscarFecha.addActionListener(this);
        this.consultarCitas.btnReportes.addActionListener(this);
        buscar();
    }
    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.consultarCitas.btnBuscarFecha || e.getSource() == this.consultarCitas.btnBuscar){
            buscar();
        } else if(e.getSource() == this.consultarCitas.btnReportes){
            reportes();
        }
        
    }
    
    public void buscar(){
        String parametro = "";
        String valor = "";
        
        
        if(this.consultarCitas.rdbConsultorio.isSelected() || this.consultarCitas.rdbFecha.isSelected() || this.consultarCitas.rdbMedico.isSelected() || this.consultarCitas.rdbPaciente.isSelected()){
            if(this.consultarCitas.rdbFecha.isSelected()){
                parametro = "citFecha";
                SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");
                valor = formato.format(this.consultarCitas.chooserFecha.getDate());
            } else if(this.consultarCitas.rdbConsultorio.isSelected()){
                parametro = "citConsultorio";
                valor = this.consultarCitas.txtBusqueda.getText();
            } else if(this.consultarCitas.rdbMedico.isSelected()){
                parametro = "citMedico";
                valor = this.consultarCitas.txtBusqueda.getText();
            } else if(this.consultarCitas.rdbPaciente.isSelected()){
                parametro = "paciente";
                valor = this.consultarCitas.txtBusqueda.getText();
            }
        }
        this.consultarCitas.tabla.setModel(gestorCita.buscarCitas(parametro, valor));
        
    }
    
    public void reportes(){
        gestorCita.generarReporte();
    }
    
}
