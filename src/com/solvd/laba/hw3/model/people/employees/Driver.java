package com.solvd.laba.hw3.model.people.employees;

import com.solvd.laba.hw3.model.exceptions.InvalidEmployeeDataException;
import com.solvd.laba.hw3.model.exceptions.InvalidPersonDataException;
import com.solvd.laba.hw3.model.interfaces.Transportable;
import com.solvd.laba.hw3.model.people.Employee;
import com.solvd.laba.hw3.model.vehicles.TaxiVehicle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Driver extends Employee implements Transportable {
    private static final Logger LOGGER = LogManager.getLogger(Driver.class);
    private static int driversCount;
    private TaxiVehicle taxiVehicle;


    public Driver(String firstName, String lastName, Integer age, String phoneNumber, TaxiVehicle taxiVehicle, Integer salary) throws InvalidPersonDataException, InvalidEmployeeDataException {
        super(firstName, lastName, phoneNumber, age, salary);
        this.taxiVehicle = taxiVehicle;

        driversCount++;
    }


    public static int getDriversCount() {
        return driversCount;
    }


    public void driveFromTo(String startLocation, String endLocation) {
        LOGGER.info("I am en route to " + endLocation + "!. I started my journey from " + startLocation + ".");
    }

    public void driveFromTo(String startLocation, String endLocation, String dateTime) {
        LOGGER.info("I am en route to " + endLocation + "!. I started my journey from " + startLocation + " at " + dateTime + ".");
    }


    public TaxiVehicle getVehicle() {
        return taxiVehicle;
    }

    public void setVehicle(TaxiVehicle vehicle) {
        this.taxiVehicle = vehicle;
    }

    @Override
    public void giveRaise() {
        this.salary += 100;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + driversCount;
        return result;
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
                "\nAssigned vehicle brand:" + this.getVehicle().getMake());
    }

    @Override
    public void move(String source, String destination) {
        LOGGER.info(this.firstName + ": I am en route to " + source + "!. I started my journey from " + destination + ".");
    }

    @Override
    public Double getTimeOfArrival(Double distanceToGo) {
        return distanceToGo % 2;
    }
}
