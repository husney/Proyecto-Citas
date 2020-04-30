package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import javax.swing.DefaultComboBoxModel;


public class ControladorRegistroCita implements ActionListener{
    
    private String idMedico;
    private int consultorio;
    modelo.Cita cita;
    modelo.GestorCita gestorCita;
    vista.RegistroCita registroCita;
    
    public ControladorRegistroCita(vista.RegistroCita vista){
        cita = new modelo.Cita();
        gestorCita = new modelo.GestorCita();
        this.registroCita = vista;
        
        this.registroCita.btnGuardar.addActionListener(this);
        this.registroCita.btnNuevo.addActionListener(this);
        this.registroCita.btnCerrar.addActionListener(this);
        llenarMedicos();
        llenarConsultorios();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.registroCita.btnGuardar){
            registrar();
        }else if(e.getSource() == this.registroCita.btnNuevo){
            limpiar();
        }
       
    }
    
    
    public void obtenerIdMedico(modelo.Medico med){
        idMedico = med.getIdentificacion();
    }
    
    public void obtenerConsultorios(modelo.Consultorio con){
        consultorio = con.getNumero();
    }
    
    public void registrar(){
        SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");
        String fechaCita = formato.format(this.registroCita.fechaCita.getDate());
        String hora = this.registroCita.horaCita.getSelectedItem().toString();
        String horaMin = this.registroCita.minutosCita.getSelectedItem().toString();
        String horaCita = hora+":"+horaMin;
        String identificacionMedico = idMedico;
        
        String identificacionPaciente = this.registroCita.txtPaciente.getText();
        int numeroConsultorio = consultorio;
        String estado = this.registroCita.txtEstado.getText();
        String observaciones = this.registroCita.txtObservaciones.getText();
        
        
        gestorCita.registrarCita(new modelo.Cita(fechaCita, horaCita, identificacionMedico, identificacionPaciente, numeroConsultorio, estado, observaciones));
    }
    
    public void llenarMedicos(){
        modelo.GestorMedico medicos = new modelo.GestorMedico();
        DefaultComboBoxModel modelo = new DefaultComboBoxModel(medicos.agregarMedicos());
        this.registroCita.cbxMedicos.setModel(modelo);
        
    }
    
    public void llenarConsultorios(){
        modelo.gestorConsultorio consultorios = new modelo.gestorConsultorio();
        DefaultComboBoxModel modelo = new DefaultComboBoxModel(consultorios.llenarConsultorios());
        this.registroCita.cbxConsultorios.setModel(modelo);
        
    }
    
    public void limpiar(){
        this.registroCita.fechaCita.setDate(null);
        this.registroCita.horaCita.setSelectedIndex(0);
        this.registroCita.minutosCita.setSelectedIndex(0);
        this.registroCita.txtPaciente.setText(null);
        this.registroCita.cbxMedicos.setSelectedIndex(0);
        this.registroCita.cbxConsultorios.setSelectedIndex(0);
        this.registroCita.txtEstado.setText(null);
        this.registroCita.txtObservaciones.setText(null);
        
    }
}
