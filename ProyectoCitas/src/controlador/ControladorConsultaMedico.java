package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class ControladorConsultaMedico implements ActionListener{
    
    vista.ConsultarMedicos consultaMedico;
    modelo.GestorMedico gestorMedico;

    public ControladorConsultaMedico (vista.ConsultarMedicos vista){

        gestorMedico = new modelo.GestorMedico();
        this.consultaMedico = vista;
        
        this.consultaMedico.btnBuscar.addActionListener(this);
        this.consultaMedico.btnCerrar.addActionListener(this);
        this.consultaMedico.btnReporte.addActionListener(this);
        buscar();
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.consultaMedico.btnBuscar){
            buscar();
        } else if(e.getSource() == this.consultaMedico.btnReporte) {
            reporte();
        }
    }
    
    
    public void buscar(){
        String parametro = "";
        String valor = "";
        
        if(this.consultaMedico.rdbIdentificacion.isSelected() || this.consultaMedico.rdbNombre.isSelected() || this.consultaMedico.rdbApellido.isSelected()){
            if(this.consultaMedico.rdbIdentificacion.isSelected()){
                parametro = "medIdentificacion";
                valor = this.consultaMedico.txtBuscar.getText();
            } else if(this.consultaMedico.rdbNombre.isSelected()){
                parametro = "medNombre";
                valor = this.consultaMedico.txtBuscar.getText();
            } else if(this.consultaMedico.rdbApellido.isSelected()){
                parametro = "medApellidos";
                valor = this.consultaMedico.txtBuscar.getText();
            }
        }
        
        this.consultaMedico.tabla.setModel(gestorMedico.buscarMedico(parametro, valor));
        
    }
    
    public void reporte(){
        gestorMedico.generarReporte();
    }
    
    
}
