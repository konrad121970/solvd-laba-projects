package com.solvd.laba.hw3.model.vehicles;

import com.solvd.laba.hw3.common.enums.TaxiStandardType;
import com.solvd.laba.hw3.common.exceptions.InvalidNumberOfSeatsException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

public class Taxi extends Vehicle {
    private static final Logger LOGGER = LogManager.getLogger(Taxi.class);
    private double farePerKilometer;
    private double fareCost;
    private TaxiStandardType taxiStandard;

    public Taxi(String make, String model, String registrationPlate, int numberOfSeats, double farePerKilometer, TaxiStandardType taxiStandard) throws InvalidNumberOfSeatsException {
        super(make, model, numberOfSeats, registrationPlate);
        this.farePerKilometer = farePerKilometer;
        this.taxiStandard = taxiStandard;
    }

    public Taxi(String make, String model, String registrationPlate, int numberOfSeats) throws InvalidNumberOfSeatsException {
        super(make, model, numberOfSeats, registrationPlate);
    }

    public TaxiStandardType getTaxiStandard() {
        return taxiStandard;
    }

    public void setTaxiStandard(TaxiStandardType taxiStandard) {
        this.taxiStandard = taxiStandard;
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
        this.fareCost = distanceInKm * farePerKilometer * taxiStandard.getFareMultiplier();
        return fareCost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Taxi that = (Taxi) o;
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
