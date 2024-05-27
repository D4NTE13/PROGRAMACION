package co.edu.uniquindio.poo;

public abstract class Vehiculo{
    
    private String placa;
    private String modelo;
    private String propietario;
    
    /**
     * Método constructor para la clase Vehiculo
     * @param placa
     * @param modelo
     * @param propietario
     */
    public Vehiculo(String placa, String modelo, String propietario){
        this.placa = placa;
        this.modelo = modelo;
        this.propietario = propietario;
    }
    
    /**
     * Métodos getter de cada atributo
     * @return
     */
    public String getPlaca(){
        return placa;
    }

    public String getModelo(){
        return modelo;
    }

    public String getPropietario(){
        return propietario;
    }
    
    /**
     * Método abstracto para las herencias
     */
    public abstract String obtenerTipo();
}