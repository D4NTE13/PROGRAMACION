package co.edu.uniquindio.poo;

public class Puesto{
    
    private boolean ocupado;
    private Vehiculo vehiculoEstacionado;

    /**
     * Método constructor para la clase Puesto
     */
    public Puesto(){
        this.ocupado = false;
        this.vehiculoEstacionado = null;
    }

    /**
     * Métodos getter de cada atributo
     * @return
     */
    public Boolean getOcupado(){
        return ocupado;
    }

    public Vehiculo getVehiculoEstacionado(){
        return vehiculoEstacionado;
    }

    /**
     * Método para asignar un puesto a un vehiculo
     * @param vehiculo
     */
    public void asignarPuesto(Vehiculo vehiculo){
        this.ocupado = true;
        this.vehiculoEstacionado = vehiculo;
    }

    /**
     *  Método para liberar un puesto
     */
    public void liberarPuesto(){
        this.ocupado = false;
        this.vehiculoEstacionado = null;
    }
}
