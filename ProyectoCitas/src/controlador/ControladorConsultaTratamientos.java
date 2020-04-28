package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;



public class ControladorConsultaTratamientos implements ActionListener{

    modelo.GestorTratamiento gestorTratamiento;
    vista.ConsultarTratamientos consultaTratamientos;
    
    public ControladorConsultaTratamientos(vista.ConsultarTratamientos vista){
        gestorTratamiento = new modelo.GestorTratamiento();
        this.consultaTratamientos = vista;
        
        this.consultaTratamientos.btnBuscarFecha.addActionListener(this);
        this.consultaTratamientos.btnBuscarTxt.addActionListener(this);
        this.consultaTratamientos.btnReporte.addActionListener(this);
        buscar();
        
    }
    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.consultaTratamientos.btnBuscarFecha || e.getSource() == this.consultaTratamientos.btnBuscarTxt){
            buscar();
        } else if(e.getSource() == this.consultaTratamientos.btnReporte){
            reporte();
        }
    }
    
    public void buscar(){
        
        SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");
        String parametro = "";
        String valor = "";
        
        if(this.consultaTratamientos.rdbFechaAsignacion.isSelected()){
            parametro = "traFechaAsignado";
            valor = formato.format(this.consultaTratamientos.chooserFecha.getDate());
        } else if(this.consultaTratamientos.rdbFechaInicio.isSelected()){
            parametro = "traFechaInicio";
            valor = formato.format(this.consultaTratamientos.chooserFecha.getDate());
        } else if(this.consultaTratamientos.rdbFechaFin.isSelected()){
            parametro = "traFechaFin";
            valor = formato.format(this.consultaTratamientos.chooserFecha.getDate());
        } else if(this.consultaTratamientos.rdbDescripcion.isSelected()){
            parametro = "traDescripcion";
            valor = this.consultaTratamientos.txtBusqueda.getText();
        } else if(this.consultaTratamientos.rdbPaciente.isSelected()){
            parametro = "traPaciente";
            valor = this.consultaTratamientos.txtBusqueda.getText();
        } 
        
     
        this.consultaTratamientos.tabla.setModel(gestorTratamiento.buscar(parametro, valor));
        
        /*int anchos[] = {10,10,10,10,10,10,10};
        for(int i = 0; i < this.consultaTratamientos.tabla.getColumnCount(); i++){
            this.consultaTratamientos.tabla.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
        }*/
        
        
        
    }
    
    public void limpiar(){
        this.consultaTratamientos.chooserFecha.setDate(null);
        this.consultaTratamientos.rdbPaciente.setText(null);
        this.consultaTratamientos.txtBusqueda.setText(null);
        
    }
    
    public void reporte(){
        gestorTratamiento.generarReporte();
    }
    
}