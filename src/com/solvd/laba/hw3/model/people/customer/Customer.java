package com.solvd.laba.hw3.model.people.customer;

import com.solvd.laba.hw3.model.interfaces.Tranportable;
import com.solvd.laba.hw3.model.people.Person;

public final class Customer extends Person implements Tranportable {
    private static int customersCount;
    private Double spentMoney = 0.0;

    public Customer(String firstName, String lastName, String phoneNumber) {
        super(firstName, lastName, phoneNumber);

        customersCount++;
    }

    public static int getCustomersCount() {
        return customersCount;
    }

    public void pay(Double cash) {
        System.out.println("I have just spent " + cash + "USD!");
        this.spentMoney += cash;
    }

    // Method Overloading
    public void pay(Double cash, String message) {
        System.out.println("I have just spent " + cash + "USD!" + " " + message);
        this.spentMoney += cash;
    }

    public Double getSpentMoney() {
        return spentMoney;
    }

    public void setSpentMoney(Double amount) {
        this.spentMoney = spentMoney;
    }


    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + customersCount;
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
        Customer otherCustomer = (Customer) obj;
        return super.equals(obj) && customersCount == customersCount;
    }

    @Override
    public String toString() {
        return "Customer [firstName=" + firstName + ", lastName=" + lastName + "]";
    }

    @Override
    public void display() {
        System.out.println("Customer's name: " + this.firstName + "surname " + this.lastName);
    }

    @Override
    public void showDetails() {
        System.out.println("Details for Customer: \nName: " + this.firstName +
                "\nLast Name: " + this.lastName +
                "\nSpent money: " + this.spentMoney);
    }

    @Override
    public void move(String source, String destination) {
        System.out.println("I am taking a ride in a Taxi from " + source + " to " + destination + "!");
    }

    @Override
    public Double getTimeOfArrival(Double distanceToGo) {
        return distanceToGo % 2;
    }
}
