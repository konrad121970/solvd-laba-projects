package com.solvd.laba.hw2.employees;

import java.math.BigDecimal;

public class Accountant {
    private static int accountantsCount;
    private String firstName;
    private String lastName;
    private int age;
    private String phoneNumber;
    private BigDecimal salary;

    public Accountant(String firstName, String lastName, int age, String phoneNumber, BigDecimal salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.salary = salary;

        accountantsCount++;
    }

    public static int getAccountantsCount() {
        return accountantsCount;
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

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }
}
