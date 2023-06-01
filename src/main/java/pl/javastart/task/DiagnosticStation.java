package pl.javastart.task;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class DiagnosticStation {
    private static final int EXIT = 0;
    private static final int CREATE_VEHICLE = 1;
    private static final int GET_NEW_VEHICLE = 2;
    private final String vehiclesTxt = "src/main/java/resources/vehicles.txt";
    private final Queue<Vehicle> vehiclesQueue = new LinkedList<>();

    void mainLoop() {
        int option;
        VehicleReader vehicleReader = new VehicleReader();
        vehicleReader.readVehicles(vehiclesTxt, vehiclesQueue);
        do {
            printOptions();
            option = readOption();
            evaluateOption(option);
        } while (option != EXIT);
    }

    private void printOptions() {
        System.out.println("Dostępne opcje:");
        System.out.println(" > Wyjście z programu - " + EXIT);
        System.out.println(" > Dodaj nowy pojazd - " + CREATE_VEHICLE);
        System.out.println(" > Pobierz kolejny pojazd i wyświetl info - " + GET_NEW_VEHICLE);
    }

    private int readOption() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Wybierz opcję:");
        int option = scanner.nextInt();
        scanner.nextLine();
        return option;
    }

    private void evaluateOption(int option) {
        VehicleWriter vehicleWriter = new VehicleWriter();
        if (option == EXIT) {
            saveAndExit(vehicleWriter);
        } else if (option == CREATE_VEHICLE) {
            Vehicle vehicle = makeNewVehicle();
            vehiclesQueue.offer(vehicle);
        } else if (option == GET_NEW_VEHICLE) {
            if (vehiclesQueue.isEmpty()) {
                System.out.println("Brak pojazdów w kolejce");
                vehicleWriter.saveVehicles(vehiclesTxt, vehiclesQueue);
                System.out.println("Zamykam program");
                System.exit(EXIT);
            } else {
                System.out.println("Pojazd: ");
                Vehicle peek = vehiclesQueue.poll();
                System.out.println(peek);
            }
        } else {
            System.err.println("Nieznana opcja");
        }
    }

    private void saveAndExit(VehicleWriter vehicleWriter) {
        vehicleWriter.saveVehicles(vehiclesTxt, vehiclesQueue);
        System.out.println("Zapis pojazdów");
        System.out.println("Zamykam program");
    }

    private Vehicle makeNewVehicle() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj typ pojazdu");
        String type = scanner.nextLine();
        System.out.println("Podaj markę");
        String brand = scanner.nextLine();
        System.out.println("Podaj model");
        String model = scanner.nextLine();
        System.out.println("Podaj rocznik");
        int year = scanner.nextInt();
        System.out.println("Podaj przebieg");
        Double mileage = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Podaj numer VIN");
        String vinNumber = scanner.nextLine();
        return new Vehicle(type, brand, model, year, mileage, vinNumber);
    }
}
