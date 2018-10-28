package p08_Vehicles;

public class Car extends Vehicle {
    public Car(Double fuelQuantity, Double fuelConsumption) {
        super(fuelQuantity, fuelConsumption);
    }

    @Override
    public void setFuelConsumption(Double fuelConsumption) {
        super.setFuelConsumption(fuelConsumption + 0.9);
    }

    @Override
    public void refuel(double liters) {
        super.setFuelQuantity(super.getFuelQuantity() + liters);
    }
}
