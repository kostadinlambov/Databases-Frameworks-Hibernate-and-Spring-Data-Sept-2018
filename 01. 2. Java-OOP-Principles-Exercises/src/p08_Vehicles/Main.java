package p08_Vehicles;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] carInput = reader.readLine().split("\\s+");
        Double fuelQuantityCar = Double.parseDouble(carInput[1]);
        Double fuelConsumptionCar = Double.parseDouble(carInput[2]);

        Vehicle car = new Car(fuelQuantityCar, fuelConsumptionCar);

        String[] truckInput = reader.readLine().split("\\s+");
        Double fuelQuantityTruck = Double.parseDouble(truckInput[1]);
        Double fuelConsumptionTruck = Double.parseDouble(truckInput[2]);

        Vehicle truck = new Truck(fuelQuantityTruck, fuelConsumptionTruck);

        int numberOfCommands = Integer.parseInt(reader.readLine());

        for (int i = 0; i < numberOfCommands; i++) {
            String[] token = reader.readLine().split("\\s+");


            switch (token[0]){
                case "Drive":
                    double distance = Double.parseDouble(token[2]);

                    if("Car".equalsIgnoreCase(token[1])){
                        System.out.println(car.checkDistance(distance));
                    }else{
                        System.out.println(truck.checkDistance(distance));
                    }
                    break;
                case "Refuel":
                    double liters = Double.parseDouble(token[2]);
                    if("Car".equalsIgnoreCase(token[1])){
                        car.refuel(liters);
                    }else{
                        truck.refuel(liters);
                    }
                    break;
            }
        }
        System.out.printf("Car: %.2f%n", car.getFuelQuantity());
        System.out.printf("Truck: %.2f", truck.getFuelQuantity());
    }
}
