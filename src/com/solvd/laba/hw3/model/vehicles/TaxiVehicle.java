package com.solvd.laba.hw3.model.vehicles;

public class TaxiVehicle extends Vehicle {
    private double farePerKilometer;
    private double fareCost;

    public TaxiVehicle(String make, String model, String registrationPlate, int numberOfSeats, double farePerKilometer) {
        super(make, model, numberOfSeats, registrationPlate);
        this.farePerKilometer = farePerKilometer;
    }

    public double getFareCost() {
        return fareCost;
    }

    public void setFareCost(double fareCost) {
        this.fareCost = fareCost;
    }

    public double getFarePerKilometer() {
        return farePerKilometer;
    }

    public void setFarePerKilometer(double farePerKilometer) {
        this.farePerKilometer = farePerKilometer;
    }

    public Double calculatePrice(Double distanceInKm) {
        this.fareCost = distanceInKm * farePerKilometer;
        return fareCost;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "TaxiVehicle [make = " + make + ", model = " + model + "]";
    }
}
