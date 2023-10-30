package model.vehicles;

public class Engine {
    private String fuelType;
    private String displacement;

    public Engine(String fuelType, String displacement) {
        this.fuelType = fuelType;
        this.displacement = displacement;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getDisplacement() {
        return displacement;
    }

    public void setDisplacement(String displacement) {
        this.displacement = displacement;
    }
}
