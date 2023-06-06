package pl.javastart.task;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class VehicleReader {

    public Queue<Vehicle> readVehicles(String fileName) throws IOException {
        Queue<Vehicle> vehiclesQueue = new LinkedList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] strings = line.split(";");
                String type = strings[0];
                String brand = strings[1];
                String model = strings[2];
                int year = Integer.parseInt(strings[3]);
                Double mileage = Double.valueOf(strings[4]);
                String vinNumber = strings[5];
                vehiclesQueue.add(new Vehicle(type, brand, model, year, mileage, vinNumber));
            }
        }
        return vehiclesQueue;
    }
}
