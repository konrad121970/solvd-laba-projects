package com.solvd.laba.hw3.model.vehicles;

import com.solvd.laba.hw3.model.exceptions.InvalidNumberOfSeatsException;

import java.util.Objects;

public class TaxiVehicle extends Vehicle {
    private double farePerKilometer;
    private double fareCost;

    public TaxiVehicle(String make, String model, String registrationPlate, int numberOfSeats, double farePerKilometer) throws InvalidNumberOfSeatsException {
        super(make, model, numberOfSeats, registrationPlate);
        this.farePerKilometer = farePerKilometer;
    }

    public TaxiVehicle(String make, String model, String registrationPlate, int numberOfSeats) throws InvalidNumberOfSeatsException {
        super(make, model, numberOfSeats, registrationPlate);
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

    public void setFarePerKilometer(Double farePerKilometer) {
        this.farePerKilometer = farePerKilometer;
    }

    public Double calculatePrice(Double distanceInKm) {
        this.fareCost = distanceInKm * farePerKilometer;
        return fareCost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        TaxiVehicle that = (TaxiVehicle) o;
        return Double.compare(farePerKilometer, that.farePerKilometer) == 0 && Double.compare(fareCost, that.fareCost) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), farePerKilometer, fareCost);
    }

    @Override
    public String toString() {
        return "TaxiVehicle [make = " + make + ", model = " + model + "]";
    }
}
