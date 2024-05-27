package co.edu.uniquindio.poo;

public class Moto extends Vehiculo{

    private String tipo;
    private int velocidadMaxima;

    /**
     * Método constructor para la clase Moto
     * @param placa
     * @param modelo
     * @param propietario
     * @param tipo
     * @param velocidadMaxima
     */
    public Moto(String placa, String modelo, String propietario, String tipo, int velocidadMaxima){
        super(placa, modelo, propietario);
        this.tipo = tipo;
        this.velocidadMaxima = velocidadMaxima;
    }

    /**
     * Métodos getter de cada atributo
     * @return
     */
    public int getVelocidadMaxima(){
        return velocidadMaxima;
    }

    /**
     * Método abstracto de la clase Vehiculo
     */
    public String obtenerTipo(){
        return tipo.equalsIgnoreCase("moto clásica") ? "moto clásica" : "moto híbrida";
    }
}
