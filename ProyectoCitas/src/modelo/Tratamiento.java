package modelo;


public class Tratamiento {
    private int numero;
    private String fechaAsignado;
    private String fechaInicio;
    private String fechaFin;
    private String descripcion;
    private String observacion;
    private String paciente;
    
    public Tratamiento(){}
    
    public Tratamiento(String fechaAsignado, String fechaInicio, String fechaFin, String descripcion, String observacion, String paciente){
        this.fechaAsignado = fechaAsignado;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.descripcion = descripcion;
        this.observacion = observacion;
        this.paciente = paciente;
    }
    
    
    public int getNumero(){
        return this.numero;
    }
    
    public void setNumero(int numero){
        this.numero = numero;
    }
    
    public String getFechaAsignado(){
        return this.fechaAsignado;
    }
    
    public void setFechaAsignado(String fechaAsignado){
        this.fechaAsignado = fechaAsignado;
    }
    
    public String getFechaInicio(){
        return this.fechaInicio;
    }
    
    public void setFechaInicio(String fechaInicio){
        this.fechaInicio = fechaInicio;
    }
    
    public String getFechaFin(){
        return this.fechaFin;
    }
    
    public void setFechaFin(String fechaFin){
        this.fechaFin = fechaFin;
    }
    
   public String getDescripcion(){
       return this.descripcion;
   }
   
   public void setDescripcion(String descripcion){
       this.descripcion = descripcion;
   }
   
   public String getObservacion(){
       return this.observacion;
   }
   
   public void setObservacion(String observacion){
       this.observacion = observacion;
   }
   
   public String getPaciente(){
       return this.paciente;
   }
   
   public void setPaciente(String paciente){
       this.paciente = paciente;
   }
    
}
