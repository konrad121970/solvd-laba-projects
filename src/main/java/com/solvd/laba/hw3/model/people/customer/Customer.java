package com.solvd.laba.hw3.model.people.customer;

import com.solvd.laba.hw3.common.enums.CurrencyType;
import com.solvd.laba.hw3.common.exceptions.InvalidPersonDataException;
import com.solvd.laba.hw3.common.interfaces.Transportable;
import com.solvd.laba.hw3.model.people.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Serializable;

public final class Customer extends Person implements Transportable, Serializable {
    private static final Logger LOGGER = LogManager.getLogger(Customer.class);
    private static int customersCount;
    private Double spentMoney;

    public Customer(String firstName, String lastName, String phoneNumber) throws InvalidPersonDataException {
        super(firstName, lastName, phoneNumber);
        this.spentMoney = 10.0;
        customersCount++;
    }

    public static int getCustomersCount() {
        return customersCount;
    }

    public void pay(Double cash, CurrencyType currencyType) {
        LOGGER.info("Customer " + this.firstName + " " + "has just spent " + cash + " " + currencyType.getSymbol());
        this.spentMoney += cash;
    }

    // Method Overloading
    public void pay(Double cash, String message) {
        LOGGER.info("Customer " + this.firstName + " " + "has just spent " + cash + " USD!\n He also left a message: " + message);
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
        LOGGER.info("Customer's name: " + this.firstName + "surname " + this.lastName);
    }

    @Override
    public void showDetails() {
        LOGGER.info("Details for Customer: \nName: " + this.firstName +
                "\nLast Name: " + this.lastName +
                "\nSpent money: " + this.spentMoney);
    }

    @Override
    public void move(String source, String destination) {
        LOGGER.debug("I am taking a ride in a Taxi from " + source + " to " + destination + "!");
    }

    @Override
    public Double getTimeOfArrival(Double distanceToGo) {
        return distanceToGo % 2;
    }
}
