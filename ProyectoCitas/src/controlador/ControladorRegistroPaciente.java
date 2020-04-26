package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import modelo.Paciente;

public class ControladorRegistroPaciente implements ActionListener{

    
    modelo.Paciente paciente;
    modelo.gestorPaciente gestorPaciente;
    vista.RegpacienteInternalFrame registroPaciente;
    
    
    public ControladorRegistroPaciente(vista.RegpacienteInternalFrame vista){
    paciente = new modelo.Paciente();
    gestorPaciente = new modelo.gestorPaciente();
    this.registroPaciente = vista;
    
    vista.btnNuevo.addActionListener(this);
    vista.btnRegistrar.addActionListener(this);
    }
    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
       if(e.getSource() == this.registroPaciente.btnRegistrar){
           registrar();
       }else if(e.getSource() == this.registroPaciente.btnNuevo){
           limpiar();
       }
    }
    
    public void registrar(){
        String identificacion = this.registroPaciente.txtIdentificacion.getText();
        String nombres = this.registroPaciente.txtNombres.getText();
        String apellidos = this.registroPaciente.txtApellidos.getText();
        SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");
        String fechaNacimiento = formato.format(this.registroPaciente.fechaNacimiento.getDate());
        String genero = "";
        if(this.registroPaciente.rdbFemenino.isSelected()){
            genero = "Femenino";
        }else if(this.registroPaciente.rdbMasculino.isSelected()){
            genero = "Masculino";
        }
        gestorPaciente.registrarPaciente(new Paciente(identificacion, nombres, apellidos, fechaNacimiento, genero));
    }
    
    public void limpiar(){
        this.registroPaciente.txtIdentificacion.setText(null);
        this.registroPaciente.txtNombres.setText(null);
        this.registroPaciente.txtApellidos.setText(null);
        this.registroPaciente.fechaNacimiento.setDate(null);
        this.registroPaciente.rdbFemenino.setSelected(true);
        this.registroPaciente.rdbMasculino.setSelected(false);
        this.registroPaciente.txtIdentificacion.grabFocus();
    }
}
