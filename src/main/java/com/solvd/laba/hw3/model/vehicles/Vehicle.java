package com.solvd.laba.hw3.model.vehicles;

import com.solvd.laba.hw3.model.exceptions.InvalidNextMaintenanceDateException;
import com.solvd.laba.hw3.model.exceptions.InvalidNumberOfSeatsException;
import com.solvd.laba.hw3.model.interfaces.Displayable;
import com.solvd.laba.hw3.model.interfaces.Maintainable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.util.Objects;

public abstract class Vehicle implements Maintainable, Displayable {
    private static final Logger LOGGER = LogManager.getLogger(Vehicle.class);
    protected String make;
    protected String model;
    protected String registrationPlate;
    private int numberOfSeats;
    private LocalDate nextMaintenance;
    private LocalDate lastMaintenance;

    public Vehicle(String make, String model, int numberOfSeats, String registrationPlate) throws InvalidNumberOfSeatsException {
        if (numberOfSeats < 1 || numberOfSeats > 300) {
            throw new InvalidNumberOfSeatsException("Number of seats should be a number between 1 and 300!");
        }
        this.make = make;
        this.model = model;
        this.numberOfSeats = numberOfSeats;
        this.registrationPlate = registrationPlate;
    }

    public LocalDate getNextMaintenance() {
        return nextMaintenance;
    }

    public void setNextMaintenance(LocalDate nextMaintenance) {
        this.nextMaintenance = nextMaintenance;
    }

    public LocalDate getLastMaintenance() {
        return lastMaintenance;
    }

    public void setLastMaintenance(LocalDate lastMaintenance) {
        this.lastMaintenance = lastMaintenance;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public String getRegistrationPlate() {
        return registrationPlate;
    }

    public void setRegistrationPlate(String registrationPlate) {
        this.registrationPlate = registrationPlate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return numberOfSeats == vehicle.numberOfSeats && Objects.equals(make, vehicle.make) && Objects.equals(model, vehicle.model) && Objects.equals(registrationPlate, vehicle.registrationPlate) && Objects.equals(nextMaintenance, vehicle.nextMaintenance) && Objects.equals(lastMaintenance, vehicle.lastMaintenance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(make, model, registrationPlate, numberOfSeats, nextMaintenance, lastMaintenance);
    }

    @Override
    public void scheduleMaintenance(LocalDate date) {
        LocalDate now = LocalDate.now();
        if (date.isBefore(now)) {
            throw new InvalidNextMaintenanceDateException("Invalid maintenance schedule date: Cannot schedule maintenance in the past.");
        }
        this.nextMaintenance = date;
    }

    @Override
    public void doMaintenance() {
        this.lastMaintenance = LocalDate.now();
    }


    // TODO:
    @Override
    public void display() {
        LOGGER.info("Vehicle [make=" + this.make + ", model=" + this.model + "]");
    }

    @Override
    public void showDetails() {
        LOGGER.info("Vehicle{" +
                "make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", registrationPlate='" + registrationPlate + '\'' +
                ", numberOfSeats=" + numberOfSeats +
                ", nextMaintenance=" + nextMaintenance +
                ", lastMaintenance=" + lastMaintenance +
                '}');
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", registrationPlate='" + registrationPlate + '\'' +
                ", numberOfSeats=" + numberOfSeats +
                ", nextMaintenance=" + nextMaintenance +
                ", lastMaintenance=" + lastMaintenance +
                '}';
    }
}


