package p08_Vehicles;

public class Truck extends Vehicle {
    public Truck(Double fuelQuantity, Double fuelConsumption) {
        super(fuelQuantity, fuelConsumption);
    }

    @Override
    public void setFuelConsumption(Double fuelConsumption) {
        super.setFuelConsumption(fuelConsumption + 1.6);
    }

    @Override
    public void refuel(double liters) {
        super.setFuelQuantity(super.getFuelQuantity() + liters*0.95);
    }
}
