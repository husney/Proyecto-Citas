package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorRegistroMedico implements ActionListener{

    vista.RegMedico registroMedico;
    modelo.GestorMedico gestorMedico;
    modelo.Medico medico;
    
    public ControladorRegistroMedico(vista.RegMedico vista){
        medico = new modelo.Medico();
        gestorMedico = new modelo.GestorMedico();
        this.registroMedico = vista;
        
        this.registroMedico.btnNuevo.addActionListener(this);
        this.registroMedico.btnRegistrar.addActionListener(this);
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.registroMedico.btnRegistrar){
            registrarMedico();
        }else if(e.getSource() == this.registroMedico.btnNuevo){
            limpiar();
        }
    }
    
    public void registrarMedico(){
        String identificacion = this.registroMedico.txtIdentificacion.getText();
        String nombres = this.registroMedico.txtNombres.getText();
        String apellidos = this.registroMedico.txtApellidos.getText();
        
        gestorMedico.registrarMedico(new modelo.Medico (identificacion, nombres, apellidos));
    }

    public void limpiar(){
        this.registroMedico.txtIdentificacion.setText(null);
        this.registroMedico.txtNombres.setText(null);
        this.registroMedico.txtApellidos.setText(null);
        this.registroMedico.txtIdentificacion.grabFocus();
    }
    
}
