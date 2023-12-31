package com.solvd.laba.hw3.model.people.employees;

import com.solvd.laba.hw3.common.enums.DriverStatusType;
import com.solvd.laba.hw3.common.exceptions.InvalidEmployeeDataException;
import com.solvd.laba.hw3.common.exceptions.InvalidPersonDataException;
import com.solvd.laba.hw3.common.interfaces.Transportable;
import com.solvd.laba.hw3.model.people.Employee;
import com.solvd.laba.hw3.model.vehicles.Taxi;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Driver extends Employee implements Transportable {
    private static final Logger LOGGER = LogManager.getLogger(Driver.class);
    private static int driversCount;
    private double drivenDistance;
    private Taxi taxi;
    private DriverStatusType driverStatusType;

    public Driver() {
    }

    public Driver(String firstName, String lastName, Integer age, String phoneNumber, Taxi taxi, Integer salary) throws InvalidPersonDataException, InvalidEmployeeDataException {
        super(firstName, lastName, phoneNumber, age, salary);
        driverStatusType = DriverStatusType.AVAILABLE;
        if (taxi != null) {
            this.taxi = taxi;
            driversCount++;
        } else LOGGER.warn("Failed to add Driver! Taxi vehicle cannot be null in Driver class!");
    }

    // Driver without TaxiVehicle Constructor
    public Driver(String firstName, String lastName, Integer age, String phoneNumber, Integer salary) throws InvalidPersonDataException, InvalidEmployeeDataException {
        super(firstName, lastName, phoneNumber, age, salary);
        this.taxi = null;
        driversCount++;
    }


    public static int getDriversCount() {
        return driversCount;
    }

    public DriverStatusType getDriverStatus() {
        return driverStatusType;
    }

    public void endRide() {
        driverStatusType = DriverStatusType.AVAILABLE;
    }

    public void goOnBrake() {
        driverStatusType = DriverStatusType.ON_BREAK;
    }

    public Taxi getVehicle() {
        return taxi;
    }

    public void setVehicle(Taxi vehicle) {
        this.taxi = vehicle;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + driversCount;
        return result;
    }

    @Override
    protected int giveBonus() {
        return (int) (drivenDistance * 0.1);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Driver otherDriver = (Driver) obj;
        return super.equals(obj) && driversCount == driversCount;
    }

    @Override
    public String toString() {
        return "Driver [name=" + firstName + ", lastName=" + lastName + "]";
    }

    @Override
    public void display() {
        LOGGER.info("Driver's name: " + this.firstName + "surname " + this.lastName);
    }

    @Override
    public void showDetails() {
        LOGGER.info("Details for Driver: \nName: " + this.firstName +
                "\nLast Name: " + this.lastName +
                "\nAge: " + this.age +
                "\nSalary: " + this.salary +
                "\nAssigned vehicle brand: ");
        //this.getVehicle().getMake())

    }

    @Override
    public void move(String source, String destination, Double distanceInKm) {
        LOGGER.info("I am en route to " + destination + "!. I started my journey from " + source + ".");
        driverStatusType = DriverStatusType.UNAVAILABLE;
        drivenDistance += distanceInKm;
    }

    @Override
    public Double getTimeOfArrival(Double distanceToGo) {
        return distanceToGo % 2;
    }
}
