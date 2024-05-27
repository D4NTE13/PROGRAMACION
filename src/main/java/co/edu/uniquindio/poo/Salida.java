package co.edu.uniquindio.poo;

import java.time.Duration;
import java.time.LocalDateTime;

public class Salida{
    
    private Vehiculo vehiculo;
    private LocalDateTime momentoIngreso;
    private LocalDateTime momentoSalida;
    private double tarifa;
    private double costoTotal;

    /**
     * Método constructor para la clase Puesto
     * @param vehiculo
     * @param momentoIngreso
     * @param momentoSalida
     * @param tarifa
     * @param costoTotal
     */
    public Salida(Vehiculo vehiculo, LocalDateTime momentoIngreso, LocalDateTime momentoSalida, double tarifa){
        this.vehiculo = vehiculo;
        this.momentoIngreso = momentoIngreso;
        this.momentoSalida = momentoSalida;
        this.tarifa = tarifa;
        this.costoTotal = calcularCosto();
    }

    /**
     * Métodos getter de cada atributo
     * @return
     */
    public Vehiculo getVehiculo(){
        return vehiculo;
    }

    public LocalDateTime getMomentoIngreso(){
        return momentoIngreso;
    }

    public LocalDateTime getMomentoSalida(){
        return momentoSalida;
    }

    public double getTarifa(){
        return tarifa;
    }

    public double getCostoTotal(){
        return costoTotal;
    }

    /**
     * Método para clacular el costo por las horas en el establecimiento
     * @return
     */
    public double calcularCosto(){
        Duration duracion  = Duration.between(momentoIngreso, momentoSalida);
        Long horas = duracion.toHours();
        return horas * tarifa;
    }
}
