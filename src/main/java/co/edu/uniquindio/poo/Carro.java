package co.edu.uniquindio.poo;

public class Carro extends Vehiculo{

    /**
     * Método constructor para la clase Carro
     * @param placa
     * @param modelo
     * @param propietario
     */
    public Carro(String placa, String modelo, String propietario){
        super(placa, modelo, propietario);
    }
    
    /**
     * Método abstracto de la clase Vehiculo
     */
    public String obtenerTipo(){
        return "carro";
    }
}