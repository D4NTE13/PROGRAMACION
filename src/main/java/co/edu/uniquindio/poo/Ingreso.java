package co.edu.uniquindio.poo;

import java.time.LocalDateTime;

public class Ingreso{
    
    private byte fila;
    private byte columna;
    private Vehiculo vehiculo;
    private LocalDateTime momentoIngreso;

    /**
     * Método constructor para la clase Ingreso
     * @param fila
     * @param columna
     * @param vehiculo
     * @param momentoIngreso
     */
    public Ingreso(byte fila, byte columna, Vehiculo vehiculo, LocalDateTime momentoIngreso){
        this.fila = fila;
        this.columna = columna;
        this.vehiculo = vehiculo;
        this.momentoIngreso = momentoIngreso;
    }

    /**
     * Métodos getter de cada atributo
     * @return
     */
    public Byte getFila(){
        return fila;
    }

    public Byte getColumna(){
        return columna;
    }

    public Vehiculo getVehiculo(){
        return vehiculo;
    }

    public LocalDateTime getMomentoIngreso(){
        return momentoIngreso;
    }
}