package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ControladorConsultaConsultorios implements ActionListener{

    
    modelo.gestorConsultorio gestorConsultorio;
    vista.ConsultarConsultorios consultaConsultorios;
    
    public ControladorConsultaConsultorios(vista.ConsultarConsultorios vista){
    
        gestorConsultorio = new modelo.gestorConsultorio();
        this.consultaConsultorios = vista;
        
        this.consultaConsultorios.btnBuscar.addActionListener(this);
        this.consultaConsultorios.btnCerrar.addActionListener(this);
        this.consultaConsultorios.btnReporte.addActionListener(this);
        buscar();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.consultaConsultorios.btnBuscar){
            buscar();
        } else if (e.getSource() == this.consultaConsultorios.btnReporte){
            reporte();
        }
    }
    
    

    public void buscar(){
        String parametro ="";
        String valor = "";
        
        if(this.consultaConsultorios.rdbNombre.isSelected() || this.consultaConsultorios.rdbNumero.isSelected()){
            if(this.consultaConsultorios.rdbNumero.isSelected()){
                parametro = "conNumero";
                valor = this.consultaConsultorios.txtBuscar.getText();
            }else if(this.consultaConsultorios.rdbNombre.isSelected()){
                parametro = "conNombre";
                valor = this.consultaConsultorios.txtBuscar.getText();
            }
        }
        
        this.consultaConsultorios.tabla.setModel(gestorConsultorio.buscarConsultorio(parametro, valor));
        
    }
    
    public void reporte(){
        gestorConsultorio.generarReporte();
    }
    
}
