package modelo;


public class Consultorio {
    
    private int numero;
    private String nombre;
    
    public Consultorio(){}
    
    public Consultorio(int numero, String nombre){
        this.numero = numero;
        this.nombre = nombre;
    }
    
    public int getNumero(){
        return this.numero;
    }
    
    public void setNumero(int numero){
        this.numero = numero;
    }
    
    public String getNombre(){
        return this.nombre;
    }
    
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    
    @Override
    public String toString(){
        return String.valueOf(this.numero);
    }
}
