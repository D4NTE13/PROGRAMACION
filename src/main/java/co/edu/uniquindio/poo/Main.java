package co.edu.uniquindio.poo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Main{
    
    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);
        
        Parqueadero parqueadero = inicializarParqueadero(scanner);

        while (true) {
            // Menú de opciones
            System.out.println("Menú de Opciones:");
            System.out.println("1. Registrar ingreso de vehículo");
            System.out.println("2. Registrar salida de vehículo");
            System.out.println("3. Configurar tarifas por hora");
            System.out.println("4. Generar reporte diario de vehículos");
            System.out.println("5. Generar reporte diario de dinero");
            System.out.println("6. Generar reporte mensual de vehículos");
            System.out.println("7. Generar reporte mensual de dinero");
            System.out.println("8. Obtener propietario de un vehículo en un puesto");
            System.out.println("9. Salir");

            byte opcion = scanner.nextByte();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    registrarIngreso(scanner, parqueadero);
                    break;
                case 2:
                    registrarSalida(scanner, parqueadero);
                    break;
                case 3:
                    configurarTarifas(scanner, parqueadero);
                    break;
                case 4:
                    generarReporteVehiculosDiario(scanner, parqueadero);
                    break;
                case 5:
                    generarReporteDineroDiario(scanner, parqueadero);
                    break;
                case 6:
                    generarReporteVehiculosMensual(scanner, parqueadero);
                    break;
                case 7:
                    generarReporteDineroMensual(scanner, parqueadero);
                    break;
                case 8:
                    obtenerPropietarioEnPuesto(scanner, parqueadero);
                    break;
                case 9:
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
            }
        }
    }

    private static Parqueadero inicializarParqueadero(Scanner scanner) {
        System.out.println("Ingrese el número de filas del parqueadero:");
        byte filas = scanner.nextByte();
        scanner.nextLine();

        System.out.println("Ingrese el número de columnas del parqueadero:");
        byte columnas = scanner.nextByte();
        scanner.nextLine();

        System.out.println("Ingrese la tarifa por hora para carros:");
        double tarifaCarro = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("Ingrese la tarifa por hora para motos clásicas:");
        double tarifaMotoClasica = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("Ingrese la tarifa por hora para motos híbridas:");
        double tarifaMotoHibrida = scanner.nextDouble();
        scanner.nextLine();

        return new Parqueadero(filas, columnas, tarifaCarro, tarifaMotoClasica, tarifaMotoHibrida);
    }

    private static void registrarIngreso(Scanner scanner, Parqueadero parqueadero) {
        System.out.println("Ingrese el tipo de vehículo (carro, moto clasica, moto hibrida):");
        String tipo = scanner.nextLine().toLowerCase();
    
        while (!tipo.equals("carro") && !tipo.equals("moto clasica") && !tipo.equals("moto hibrida")) {
            System.out.println("Tipo de vehículo inválido. Ingrese carro, moto clásica o moto híbrida:");
            tipo = scanner.nextLine().toLowerCase();
        }
    
        System.out.println("Ingrese la placa del vehículo:");
        String placa = scanner.nextLine();
    
        System.out.println("Ingrese el modelo del vehículo:");
        String modelo = scanner.nextLine();
    
        System.out.println("Ingrese el propietario del vehículo:");
        String propietario = scanner.nextLine();
    
        Vehiculo vehiculo;
    
        if (tipo.equals("carro")) {
            vehiculo = new Carro(placa, modelo, propietario);
        } else {
            System.out.println("Ingrese la velocidad máxima de la moto:");
            int velocidadMaxima = scanner.nextInt();
            scanner.nextLine();
            vehiculo = new Moto(placa, modelo, propietario, tipo, velocidadMaxima);
        }
    
        System.out.println("Ingrese la fila del puesto:");
        byte fila = scanner.nextByte();
        scanner.nextLine();
    
        System.out.println("Ingrese la columna del puesto:");
        byte columna = scanner.nextByte();
        scanner.nextLine();
    
        LocalDateTime momentoIngreso = LocalDateTime.now();
    
        parqueadero.registrarIngreso(fila, columna, vehiculo, momentoIngreso);
    }

    private static void registrarSalida(Scanner scanner, Parqueadero parqueadero){
        System.out.println("Ingrese la fila del puesto:");
        byte fila = scanner.nextByte();
        scanner.nextLine();

        System.out.println("Ingrese la columna del puesto:");
        byte columna = scanner.nextByte();
        scanner.nextLine();

        LocalDateTime momentoSalida = LocalDateTime.now();

        parqueadero.registrarSalida(fila, columna, momentoSalida);
    }

    private static void configurarTarifas(Scanner scanner, Parqueadero parqueadero) {
        System.out.println("Ingrese el tipo de vehículo (carro, moto clásica, moto híbrida):");
        String tipo = scanner.nextLine().toLowerCase();

        while (!tipo.equals("carro") && !tipo.equals("moto clásica") && !tipo.equals("moto híbrida")) {
            System.out.println("Tipo de vehículo inválido. Ingrese carro, moto clásica o moto híbrida:");
            tipo = scanner.nextLine().toLowerCase();
        }

        System.out.println("Ingrese la nueva tarifa por hora:");
        double nuevaTarifa = scanner.nextDouble();
        scanner.nextLine();

        parqueadero.configurarTarifas(tipo, nuevaTarifa);
    }

    private static void generarReporteVehiculosDiario(Scanner scanner, Parqueadero parqueadero) {
        System.out.println("Ingrese la fecha (YYYY-MM-DD):");
        String fecha = scanner.next();

        LocalDate dia = LocalDate.parse(fecha);

        parqueadero.generarReporteVehiculosDiario(dia);
    }

    private static void generarReporteDineroDiario(Scanner scanner, Parqueadero parqueadero) {
        System.out.println("Ingrese la fecha (YYYY-MM-DD):");
        String fecha = scanner.next();

        LocalDate dia = LocalDate.parse(fecha);

        parqueadero.generarReporteDineroDiario(dia);
    }

    private static void generarReporteVehiculosMensual(Scanner scanner, Parqueadero parqueadero) {
        System.out.println("Ingrese el mes (1-12):");
        byte mes = scanner.nextByte();
        scanner.nextLine();

        System.out.println("Ingrese el año:");
        int ano = scanner.nextInt();
        scanner.nextLine();

        parqueadero.generarReporteVehiculosMensual(mes, ano);
    }

    private static void generarReporteDineroMensual(Scanner scanner, Parqueadero parqueadero) {
        System.out.println("Ingrese el mes (1-12):");
        byte mes = scanner.nextByte();
        scanner.nextLine();

        System.out.println("Ingrese el año:");
        int ano = scanner.nextByte();
        scanner.nextInt();

        parqueadero.generarReporteDineroMensual(mes, ano);
    }

    private static void obtenerPropietarioEnPuesto(Scanner scanner, Parqueadero parqueadero) {
        System.out.println("Ingrese la fila del puesto:");
        byte fila = scanner.nextByte();
        scanner.nextLine();

        System.out.println("Ingrese la columna del puesto:");
        byte columna = scanner.nextByte();
        scanner.nextLine();

        String propietario = parqueadero.obtenerPropietarioEnPuesto(fila, columna);
        System.out.println("Propietario del vehículo en el puesto [" + columna + "," + fila + "]: " + propietario);
    }
}