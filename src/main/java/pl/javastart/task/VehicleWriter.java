package pl.javastart.task;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Queue;

public class VehicleWriter {

    public void saveVehicles(String fileName, Queue<Vehicle> vehiclesQueue) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            for (Vehicle vehicle : vehiclesQueue) {
                String type = vehicle.getType();
                String brand = vehicle.getBrand();
                String model = vehicle.getModel();
                int year = vehicle.getYear();
                Double mileage = vehicle.getMileage();
                String vinNumber = vehicle.getVinNumber();
                bufferedWriter.write(type + ";" + brand + ";" + model + ";" + year + ";" + mileage + ";" + vinNumber);
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            System.err.println("Nie można odnaleźć pliku " + fileName);
        }
    }
}
