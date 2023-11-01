package com.solvd.laba.hw2.vehicles;

public class Car {
    private final String make;
    private final String model;
    private Engine engine;
    private String registrationPlate;

    public Car(String make, String model, Engine engine, String registrationPlate) {
        this.make = make;
        this.model = model;
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
