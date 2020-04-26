package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ControladorConsultaMedico implements ActionListener{
    
    vista.ConsultarMedicos consultaMedico;
    modelo.Medico medico;
    modelo.GestorMedico gestorMedico;

    public ControladorConsultaMedico (vista.ConsultarMedicos vista){
        medico = new modelo.Medico();
        gestorMedico = new modelo.GestorMedico();
        this.consultaMedico = vista;
        
        this.consultaMedico.btnBuscar.addActionListener(this);
        this.consultaMedico.btnCerrar.addActionListener(this);
        buscar();
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.consultaMedico.btnBuscar){
            buscar();
        }
    }
    
    
    public void buscar(){
        String parametro = "";
        String valor = this.consultaMedico.txtBuscar.getText();
        
        if(this.consultaMedico.rdbNombre.isSelected()){
            parametro = "medIdentificacion";
        } else if(this.consultaMedico.rdbNombre.isSelected()){
            parametro = "medNombre";
        } else if(this.consultaMedico.rdbApellido.isSelected()){
            parametro = "medApellidos";
        }
        
        this.consultaMedico.tabla.setModel(gestorMedico.buscarMedico(parametro, valor));
        
    }
    
    
    
}
