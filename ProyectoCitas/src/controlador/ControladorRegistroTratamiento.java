package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

public class ControladorRegistroTratamiento implements ActionListener{
    
    //modelo.Tratamiento tratamiento;
    modelo.GestorTratamiento gestorTratamiento;
    vista.RegistroTratamientos regTratamiento;
    
    public ControladorRegistroTratamiento(vista.RegistroTratamientos vista){
      gestorTratamiento = new modelo.GestorTratamiento();
      this.regTratamiento = vista;
      
      this.regTratamiento.btnRegistrar.addActionListener(this);
      this.regTratamiento.btnNuevo.addActionListener(this);
      
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
      if(e.getSource() == this.regTratamiento.btnRegistrar){
          registrar();
          limpiar();
      }else if(e.getSource() == this.regTratamiento.btnNuevo){
          limpiar();
      }
      
    }
    
    public void registrar(){
        SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");
        String fechaAsignado = formato.format(this.regTratamiento.chooserFechaAsignacion.getDate());
        String fechaInicio = formato.format(this.regTratamiento.chooserFechaInicio.getDate());
        String fechaFin = formato.format(this.regTratamiento.chooserFechaFinal.getDate());
        String descripcion = this.regTratamiento.txtDescripcion.getText();
        String observaciones = this.regTratamiento.txtObservaciones.getText();
        String paciente = this.regTratamiento.txtIdentificacionPaciente.getText();
        
        gestorTratamiento.registrar(new modelo.Tratamiento(fechaAsignado, fechaInicio, fechaFin, descripcion, observaciones, paciente));
        
    }
    
    public void limpiar(){
        this.regTratamiento.chooserFechaAsignacion.setDate(null);
        this.regTratamiento.chooserFechaInicio.setDate(null);
        this.regTratamiento.chooserFechaFinal.setDate(null);
        this.regTratamiento.txtDescripcion.setText(null);
        this.regTratamiento.txtObservaciones.setText(null);
        this.regTratamiento.txtIdentificacionPaciente.setText(null);
    }
    
}
