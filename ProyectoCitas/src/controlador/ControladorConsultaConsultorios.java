package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ControladorConsultaConsultorios implements ActionListener{

    modelo.Consultorio consultorio;
    modelo.gestorConsultorio gestorConsultorio;
    vista.ConsultarConsultorios consultaConsultorios;
    
    public ControladorConsultaConsultorios(vista.ConsultarConsultorios vista){
        consultorio = new modelo.Consultorio();
        gestorConsultorio = new modelo.gestorConsultorio();
        this.consultaConsultorios = vista;
        
        this.consultaConsultorios.btnBuscar.addActionListener(this);
        this.consultaConsultorios.btnCerrar.addActionListener(this);
        buscar();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.consultaConsultorios.btnBuscar){
            buscar();
        }
    }
    
    

    public void buscar(){
        String parametro ="";
        String valor = this.consultaConsultorios.txtBuscar.getText();
        
        if(this.consultaConsultorios.rdbNumero.isSelected()){
            parametro = "conNumero";
        }else if(this.consultaConsultorios.rdbNombre.isSelected()){
            parametro = "conNombre";
        }
        
        this.consultaConsultorios.tabla.setModel(gestorConsultorio.buscarConsultorio(parametro, valor));
        
    }
    
}
