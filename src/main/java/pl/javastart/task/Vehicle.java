package pl.javastart.task;

import java.util.Objects;

public class Vehicle {
    private final String type;
    private final String brand;
    private final String model;
    private final int year;
    private final Double mileage;
    private final String vinNumber;

    public String getType() {
        return type;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public Double getMileage() {
        return mileage;
    }

    public String getVinNumber() {
        return vinNumber;
    }

    @Override
    public String toString() {
        return type + " " + brand + " " + model + " rok: " + year + " przebieg: " + mileage + " VIN: " + vinNumber;
    }

    public Vehicle(String type, String brand, String model, int year, Double mileage, String vinNumber) {
        this.type = type;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.mileage = mileage;
        this.vinNumber = vinNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Vehicle vehicle = (Vehicle) o;
        return year == vehicle.year && Objects.equals(type, vehicle.type)
                && Objects.equals(brand, vehicle.brand) && Objects.equals(model, vehicle.model) && Objects.equals(mileage, vehicle.mileage)
                && Objects.equals(vinNumber, vehicle.vinNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, brand, model, year, mileage, vinNumber);
    }
}
