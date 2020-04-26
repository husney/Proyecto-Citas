package modelo;

public class Medico {
    private String identificacion;
    private String nombres;
    private String apellidos;
    
    public Medico(){};
    
    public Medico(String identificacion, String nombres, String apellidos){
        this.identificacion = identificacion;
        this.nombres = nombres;
        this.apellidos = apellidos;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    
    
    @Override
    public String toString(){
        return this.nombres +" "+ this.apellidos ;
    }
}
