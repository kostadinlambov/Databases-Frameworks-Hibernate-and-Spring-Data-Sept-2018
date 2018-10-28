package p08_Vehicles;

import java.text.DecimalFormat;

public abstract class Vehicle {
    private Double fuelQuantity;
    private Double fuelConsumption;

    public Vehicle(Double fuelQuantity, Double fuelConsumption) {
        this.setFuelQuantity(fuelQuantity);
        this.setFuelConsumption(fuelConsumption);
    }

    public Double getFuelQuantity() {
        return this.fuelQuantity;
    }

    protected void setFuelQuantity(Double fuelQuantity) {
        this.fuelQuantity = fuelQuantity;
    }

    public Double getFuelConsumption() {
        return this.fuelConsumption;
    }

    protected void setFuelConsumption(Double fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    public String checkDistance(Double distance) {
        DecimalFormat df = new DecimalFormat("#.##########");

        Double maxDistance = this.getFuelQuantity() / this.getFuelConsumption();

        if (maxDistance >= distance) {
            this.fuelQuantity -= this.fuelConsumption * distance;
            return String.format("%s travelled %s km", this.getClass().getSimpleName(), df.format(distance));
        }

        return String.format("%s needs refueling", this.getClass().getSimpleName());
    }

    public abstract void refuel(double liters);
}
