package pl.javastart.task;

import java.io.*;
import java.util.Queue;

public class VehicleReader {
    private boolean fileExist;

    public boolean isFileExist() {
        return fileExist;
    }

    public void readVehicles(String fileName, Queue<Vehicle> vehiclesQueue) {
        fileExist = true;
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
        } catch (IOException e) {
            fileExist = false;
            System.err.println("Nie można odnaleźć pliku " + fileName);
        }
    }
}
