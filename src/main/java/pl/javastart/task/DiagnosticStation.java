package pl.javastart.task;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class DiagnosticStation {
    private static final int EXIT = 0;
    private static final int CREATE_VEHICLE = 1;
    private static final int GET_NEW_VEHICLE = 2;
    private final String vehiclesTxt = "src/main/resources/vehicles.txt";
    private Queue<Vehicle> vehiclesQueue = new LinkedList<>();

    void mainLoop() {

        VehicleReader vehicleReader = new VehicleReader();
        try {
            vehiclesQueue = vehicleReader.readVehicles(vehiclesTxt);
            boolean exit;
            do {
                printOptions();
                int option = readOption();
                exit = evaluateOption(option);
            } while (!exit);
        } catch (IOException e) {
            System.err.println("Nie można odnaleźć pliku " + vehiclesTxt);
        }
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

    private boolean evaluateOption(int option) {
        if (option == EXIT) {
            saveAndExit();
            return true;
        } else if (option == CREATE_VEHICLE) {
            Vehicle vehicle = makeNewVehicle();
            vehiclesQueue.offer(vehicle);
        } else if (option == GET_NEW_VEHICLE) {
            if (vehiclesQueue.isEmpty()) {
                System.out.println("Brak pojazdów w kolejce");
                saveAndExit();
                return true;
            } else {
                System.out.println("Pojazd: ");
                Vehicle peek = vehiclesQueue.poll();
                System.out.println(peek);
            }
        } else {
            System.err.println("Nieznana opcja");
        }
        return false;
    }

    private void saveAndExit() {
        try {
            VehicleWriter vehicleWriter = new VehicleWriter();
            vehicleWriter.saveVehicles(vehiclesTxt, vehiclesQueue);
            System.out.println("Zapis pojazdów");
            System.out.println("Zamykam program");
        } catch (IOException e) {
            System.err.println("Nie można odnaleźć pliku " + vehiclesTxt);
        }

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
