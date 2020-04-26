package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

public class ControladorConsultaCitas implements ActionListener{
    
    modelo.Cita cita;
    modelo.GestorCita gestorCita;
    vista.ConsultarCitas consultarCitas;

    public ControladorConsultaCitas(vista.ConsultarCitas vista){
        cita = new modelo.Cita();
        gestorCita = new modelo.GestorCita();
        this.consultarCitas = vista;
        
        this.consultarCitas.btnBuscar.addActionListener(this);
        this.consultarCitas.btnBuscarFecha.addActionListener(this);
        buscar();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.consultarCitas.btnBuscarFecha || e.getSource() == this.consultarCitas.btnBuscar){
            buscar();
        }
        
    }
    
    public void buscar(){
        String parametro = "";
        String valor = "";
        valor = this.consultarCitas.txtBusqueda.getText();
        
        
        if(this.consultarCitas.rdbFecha.isSelected()){
            parametro = "citFecha";
            SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");
            valor = formato.format(this.consultarCitas.chooserFecha.getDate());
        } else if(this.consultarCitas.rdbConsultorio.isSelected()){
            parametro = "citConsultorio";
        } else if(this.consultarCitas.rdbMedico.isSelected()){
            parametro = "citMedico";
        } else if(this.consultarCitas.rdbPaciente.isSelected()){
            parametro = "paciente";
        }
        
        this.consultarCitas.tabla.setModel(gestorCita.buscarCitas(parametro, valor));
        
    }
    
}
