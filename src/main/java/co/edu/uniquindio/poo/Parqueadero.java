package co.edu.uniquindio.poo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Parqueadero{
    
    private Puesto [][] puestos;
    private List <Ingreso> ingresos;
    private List <Salida> salidas;
    private Map<String, Double> tarifas;

    /**
     * Método para crear la matriz del parqueadero
     */
    public Parqueadero(byte filas, byte columnas , double tarifaCarro , double tarifaMotoClasica , double tarifaMotoHibrida){
        puestos = new Puesto[filas][columnas];
        for(byte i = 0; i < filas; i++){
            for(byte j = 0; j < columnas; j++){
                puestos [i][j] = new Puesto();
            }    
        }
        ingresos = new ArrayList<>();
        salidas = new ArrayList<>();
        tarifas = new HashMap<>();
        tarifas.put("carro", tarifaCarro);
        tarifas.put("moto clásica", tarifaMotoClasica);
        tarifas.put("moto híbrida", tarifaMotoHibrida);
    }

    /**
     * Método para registar el ingreso de un vehiculo
     * @param fila
     * @param columna
     * @param vehiculo
     * @param momentoIngreso
     */
    public void registrarIngreso (Byte fila, Byte columna, Vehiculo vehiculo, LocalDateTime momentoIngreso){
        if(!puestos[columna][fila].getOcupado()){
            puestos[columna][fila].asignarPuesto(vehiculo);
            Ingreso ingreso = new Ingreso(fila, columna, vehiculo, momentoIngreso);
            ingresos.add(ingreso);
        } else{
            System.out.println("El puesto (" + fila + ", " + columna + ") está ocupado.");
        }
    }

    /**
     *  Método para registar la salida de un vehiculo
     * @param fila
     * @param columna
     * @param momentoSalida
     */
    public void registrarSalida(byte fila, byte columna, LocalDateTime momentoSalida) {
        if (puestos[columna][fila].getOcupado()) {
            Vehiculo vehiculo = puestos[columna][fila].getVehiculoEstacionado();
            LocalDateTime momentoIngreso = null;
            for (Ingreso ingreso : ingresos) {
                if (ingreso.getVehiculo().equals(vehiculo)) {
                    momentoIngreso = ingreso.getMomentoIngreso();
                    break;
                }
            }
            if (momentoIngreso != null) {
                double tarifa = tarifas.get(vehiculo.obtenerTipo());
                Salida salida = new Salida(vehiculo, momentoIngreso, momentoSalida, tarifa);
                salidas.add(salida);
                puestos[columna][fila].liberarPuesto();
                System.out.println("Salida registrada: " + salida.getVehiculo().getPlaca() + " Costo total: " + salida.getCostoTotal());
            }
        } else {
            System.out.println("El puesto está libre.");
        }
    }

    /**
     *   Método para configurar las tarifas
     * @param tipo
     * @param nuevaTarifa
     */
    public void configurarTarifas(String tipo, double nuevaTarifa){
        tarifas.put(tipo, nuevaTarifa);
    }

    /**
     * Método para obtener el propietario de un puesto
     * @param fila
     * @param columna
     * @return
     */
    public String obtenerPropietarioEnPuesto(Byte fila, Byte columna) {
        if (puestos[columna][fila].getOcupado()) {
            return puestos[columna][fila].getVehiculoEstacionado().getPropietario();
        }
        return "Puesto vacío";
    }

    /**
     * Método para generar un reporte de vehiculos por día
     * @param dia
     */
    public void generarReporteVehiculosDiario(LocalDate dia){
        System.out.println("Reporte de vehiculos - " + dia);
        for(Ingreso ingreso : ingresos){
            if(ingreso.getMomentoIngreso().toLocalDate().isEqual(dia)){
                System.out.println("Vehiculo: " + ingreso.getVehiculo().getPlaca() + " Modelo: " + ingreso.getVehiculo().getModelo() + " Propietario: " + ingreso.getVehiculo().getPropietario() + " Puesto: (" + ingreso.getColumna() + " , " + ingreso.getFila() + ") " + "Momento de ingreso: "  + ingreso.getMomentoIngreso());
            }
        }
    }

    /**
     *  Método para generar un reporte de vehiculos por mes
     * @param mes
     * @param ano
     */
    public void generarReporteVehiculosMensual(byte mes, int ano){
        System.out.println("Reporte de Vehículos - Mes: " + mes + ", Año: " + ano);
        for (Ingreso ingreso : ingresos){
            if (ingreso.getMomentoIngreso().getMonthValue() == mes && ingreso.getMomentoIngreso().getYear() == ano){
                System.out.println("Vehiculo: " + ingreso.getVehiculo().getPlaca() + " Modelo: " + ingreso.getVehiculo().getModelo() + " Propietario: " + ingreso.getVehiculo().getPropietario() + "Puesto: (" + ingreso.getColumna() + " , " + ingreso.getFila() + ") " + "Momento ingreso: " + ingreso.getMomentoIngreso());
            }
        }
    }

    /**
     * Método para generar un reporte de dinero por dia
     * @param dia
     */
    public void generarReporteDineroDiario(LocalDate dia) {
        double totalRecaudado = 0;
        Map<String, Double> recaudacionPorTipo = new HashMap<>();
        recaudacionPorTipo.put("carro", 0.0);
        recaudacionPorTipo.put("moto clásica", 0.0);
        recaudacionPorTipo.put("moto híbrida", 0.0);
    
        for (Salida salida : salidas) {
            if (salida.getMomentoSalida().toLocalDate().isEqual(dia)) {
                totalRecaudado += salida.getCostoTotal();
                String tipo = salida.getVehiculo().obtenerTipo();
                recaudacionPorTipo.put(tipo, recaudacionPorTipo.get(tipo) + salida.getCostoTotal());
            }
        }
    
        System.out.println("Reporte de Dinero Recaudado - " + dia);
        System.out.println("Total Recaudado: " + totalRecaudado);
        for (Map.Entry<String, Double> entry : recaudacionPorTipo.entrySet()){
            if (entry.getValue() > 0) {
                System.out.println("Total Recaudado por " + entry.getKey() + ": " + entry.getValue());
            }
        }
    }

    /**
     * Método para generar un reporte de dinero por mes
     * @param mes
     * @param ano
     */
    public void generarReporteDineroMensual(byte mes, int ano){
        double totalRecaudado = 0;
        Map<String, Double> recaudacionPorTipo = new HashMap<>();
        recaudacionPorTipo.put("carro", 0.0);
        recaudacionPorTipo.put("moto_clasica", 0.0);
        recaudacionPorTipo.put("moto_hibrida", 0.0);

        for (Salida salida : salidas) {
            if (salida.getMomentoSalida().getMonthValue() == mes && salida.getMomentoSalida().getYear() == ano){
                totalRecaudado += salida.getCostoTotal();
                String tipo = salida.getVehiculo().obtenerTipo();
                recaudacionPorTipo.put(tipo, recaudacionPorTipo.get(tipo) + salida.getCostoTotal());
            }
        }

        System.out.println("Reporte de Dinero Recaudado - Mes: " + mes + ", Año: " + ano);
        System.out.println("Total Recaudado: " + totalRecaudado);
        System.out.println("Total Recaudado: " + totalRecaudado);
        for (Map.Entry<String, Double> entry : recaudacionPorTipo.entrySet()) {
            if (entry.getValue() > 0) {
                System.out.println("Total Recaudado por " + entry.getKey() + ": " + entry.getValue());
            }
        }
    }
}