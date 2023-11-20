package com.solvd.laba.hw3.model.vehicles;

import com.solvd.laba.hw3.model.exceptions.InvalidNumberOfSeatsException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

public class TaxiVehicle extends Vehicle {
    private static final Logger LOGGER = LogManager.getLogger(TaxiVehicle.class);
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
        if (fareCost < 0) {
            LOGGER.error("Invalid fare cost: cannot be negative");
            return;
        }
        this.fareCost = fareCost;
    }

    public double getFarePerKilometer() {
        return farePerKilometer;
    }

    public void setFarePerKilometer(Double farePerKilometer) {
        if (farePerKilometer <= 0) {
            LOGGER.error("Invalid fare per kilometer: must be greater than 0.");
            return;
        }
        this.farePerKilometer = farePerKilometer;
    }

    public Double calculatePrice(Double distanceInKm) {
        if (distanceInKm < 0) {
            LOGGER.error("Invalid distance: must be greater than or equal to 0.");
            return null;
        }
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