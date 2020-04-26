package modelo;


public class Cita {
    private int numero;
    private String fecha;
    private String hora;
    private String medico;
    private String paciente;
    private int consultorio;
    private String estado;
    private String observaciones;
    
    public Cita(){}
    
    public Cita( String fecha, String hora, String medico, String paciente, int consultorio, String estado, String observaciones){
        this.fecha = fecha;
        this.hora = hora;
        this.medico = medico;
        this.paciente = paciente;
        this.consultorio = consultorio;
        this.estado = estado;
        this.observaciones = observaciones;
    }
    
    public int getNumero(){
        return this.numero;
    }
    
    public void setNumero(int numero){
        this.numero = numero;
    }
    
    public String getFecha(){
        return this.fecha;
    }
    
    public void setFecha(String fecha){
        this.fecha = fecha;
    }
    
    public String getHora(){
        return this.hora;
    }
    
    public void setHora(String hora){
        this.hora = hora;
    }
    
    public String getMedico(){
        return this.medico;
    }
    
    public void setMedico(String medico){
        this.medico = medico;
    }
    
    public String getPaciente(){
        return this.paciente;
    }
    
    public int getConsultorio(){
        return this.consultorio;
    }
    
    public void setConsultorio(int consultorio){
        this.consultorio = consultorio;
    }
    
    public String getEstado(){
        return this.estado;
    }
    
    public void setEstado(String estado){
        this.estado = estado;
    }
    
    public String getObservaciones(){
        return this.observaciones;
    }
    
    public void setObservaciones(String observaciones){
        this.observaciones = observaciones;
    }
    
}
