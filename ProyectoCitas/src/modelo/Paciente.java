package modelo;

public class Paciente {
    
    private String identificacion;
    private String nombre;
    private String apellidos;
    private String fechaNacimiento;
    private String genero;
    
    public Paciente(){}
    
    public Paciente(String identificacion, String nombre, String apellidos, String fechaNacimiento, String genero){
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.genero = genero;
    }
    
    public String getIdentificacion(){
        return this.identificacion;
    }
    
    public void setIdentificacion(String identificacion){
        this.identificacion = identificacion;
    }
    
    public String getNombre(){
        return this.nombre;
    }
    
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    
    public String getApellidos(){
        return this.apellidos;
    }
    
    public void setApellidos(String apellidos){
        this.apellidos = apellidos;
    }
    
    public String getFechaNacimiento(){
        return this.fechaNacimiento;
    }
    
    public void setFechaNacimiento (String fechaNacimiento){
        this.fechaNacimiento = fechaNacimiento;
    }
    
    public String getGenero(){
        return this.genero;
    }
    
    public void setGenero(String genero){
        this.genero = genero;
    }
}
