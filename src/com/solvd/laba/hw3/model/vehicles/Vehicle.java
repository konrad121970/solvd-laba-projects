package com.solvd.laba.hw3.model.vehicles;

import com.solvd.laba.hw3.model.interfaces.Displayable;
import com.solvd.laba.hw3.model.interfaces.Maintainable;

import java.time.LocalDate;
import java.util.Objects;

public class Vehicle implements Maintainable, Displayable {
    protected String make;
    protected String model;
    protected String registrationPlate;
    private int numberOfSeats;
    private LocalDate nextMaintenance;
    private LocalDate lastMaintenance;

    public Vehicle(String make, String model, int numberOfSeats, String registrationPlate) {
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
    public String toString() {
        return "Vehicle [make=" + make + ", model=" + model + "]";
    }

    @Override
    public void scheduleMaintenance(LocalDate date) {
        this.nextMaintenance = date;
    }

    @Override
    public void doMaintenance() {
        this.lastMaintenance = LocalDate.now();
    }

    // TODO:
    @Override
    public void display() {

    }

    @Override
    public void showDetails() {

    }
}
