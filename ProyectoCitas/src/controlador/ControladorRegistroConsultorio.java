package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ControladorRegistroConsultorio implements ActionListener{
    
    modelo.Consultorio consultorio;
    modelo.gestorConsultorio gestorConsultorio;
    vista.RegConsultorio registroConsultorio;
    
    public ControladorRegistroConsultorio(vista.RegConsultorio vista){
        consultorio = new modelo.Consultorio();
        gestorConsultorio = new modelo.gestorConsultorio();
        this.registroConsultorio = vista;
        
        this.registroConsultorio.btnGuardar.addActionListener(this);
        this.registroConsultorio.btnNuevo.addActionListener(this);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.registroConsultorio.btnGuardar){
            registrar();
        }
    }
    
    public void registrar(){
        String nombre = this.registroConsultorio.txtNombre.getText();
        int numero = Integer.parseInt(this.registroConsultorio.txtNumero.getText());
        
        gestorConsultorio.registrarConsultorio(new modelo.Consultorio(numero, nombre));
        
    }
    
    
}
