package p03_Ferrari;

public class Ferrari implements Car {
private String driversName;
private String model = "488-Spider";

 public Ferrari(String driversName){
     this.setDriversName(driversName);
 }

    @Override
    public String useBrakes() {
        return "Brakes!";
    }

    @Override
    public String useGasPedal() {
        return "Zadu6avam sA!";
    }

    @Override
    public String getModel() {
        return this.model;
    }

    @Override
    public String getDriverName() {
        return this.driversName;
    }

    public void setDriversName(String driversName){
        this.driversName = driversName;
    }


    @Override
    public String toString() {
        return String.format("%s/%s/%s/%s", this.model,
                this.useBrakes(), this.useGasPedal(), this.driversName);
    }
}
