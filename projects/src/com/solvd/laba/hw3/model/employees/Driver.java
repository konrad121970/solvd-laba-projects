package com.solvd.laba.hw3.model.employees;

import com.solvd.laba.hw3.model.vehicles.Vehicle;

public class Driver extends Employee {
    private static int driversCount;
    private Vehicle vehicle;

    public Driver(String firstName, String lastName, Integer age, String phoneNumber, Vehicle vehicle, Integer salary) {
        super(firstName, lastName, phoneNumber, age, salary);
        this.vehicle = vehicle;

        driversCount++;
    }


    public static int getDriversCount() {
        return driversCount;
    }


    public void driveFromTo(String startLocation, String endLocation) {
        System.out.println("I am en route to " + endLocation + "!. I started my journey from " + startLocation + ".");
    }

    public void driveFromTo(String startLocation, String endLocation, String dateTime) {
        System.out.println("I am en route to " + endLocation + "!. I started my journey from " + startLocation + " at " + dateTime + ".");
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public void displayInfo() {
        System.out.println("Driver Info: Name: " + firstName + " Last Name: " + lastName);
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
        return super.equals(obj) && driversCount == otherDriver.driversCount;
    }

    @Override
    public String toString() {
        return "Driver [name=" + firstName + ", lastName=" + lastName + "]";
    }
}
