package com.solvd.laba.hw2.employees;

import com.solvd.laba.hw2.vehicles.Car;

import java.math.BigDecimal;

public class Driver {
    private static int driversCount;
    private String firstName;
    private String lastName;
    private int age;
    private String phoneNumber;
    private Car car;
    private BigDecimal salary;


    public Driver(String firstName, String lastName, int age, String phoneNumber, Car car, BigDecimal salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.car = car;
        this.salary = salary;

        driversCount++;
    }

    public void driveFromTo(String startLocation, String endLocation){
        System.out.println("I am en route to " + endLocation + "!. I started my journey from " + startLocation + ".");
    }
    public void driveFromTo(String startLocation, String endLocation, String dateTime){
        System.out.println("I am en route to " + endLocation + "!. I started my journey from " + startLocation + " at " + dateTime + "." );
    }
    public static int getDriversCount() {
        return driversCount;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }
}
