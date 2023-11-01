package com.solvd.laba.hw2.customer;

public class Customer {
    private static int customersCount;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Double availableBalance;

    public Customer(String firstName, String lastName, String phoneNumber, Double availableBalance) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.availableBalance = availableBalance;

        customersCount++;
    }

    public void pay(Double cash){
        System.out.println("I have just spent " + cash + "USD!");
        this.availableBalance -= cash;
    }

    // Method Overloading
    public void pay(Double cash, String message){
        System.out.println("I have just spent " + cash + "USD!" + " " + message);
        this.availableBalance -= cash;
    }

    public static int getCustomersCount() {
        return customersCount;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Double getAvailableBalance() {
        return availableBalance;
    }

    public void getAvailableBalance(Double cash) {
        this.availableBalance = availableBalance;
    }


}
