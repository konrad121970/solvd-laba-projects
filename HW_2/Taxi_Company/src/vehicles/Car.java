package vehicles;

public class Car {
    private Engine engine;
    private String registrationPlate;

    public Car(Engine engine, String registrationPlate) {
        this.engine = engine;
        this.registrationPlate = registrationPlate;
    }
    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public String getRegistrationPlate() {
        return registrationPlate;
    }

    public void setRegistrationPlate(String registrationPlate) {
        this.registrationPlate = registrationPlate;
    }
}
