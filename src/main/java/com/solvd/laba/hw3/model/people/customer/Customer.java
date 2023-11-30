package com.solvd.laba.hw3.model.people.customer;

import com.solvd.laba.hw3.common.enums.CurrencyType;
import com.solvd.laba.hw3.common.exceptions.InvalidPersonDataException;
import com.solvd.laba.hw3.common.interfaces.Transportable;
import com.solvd.laba.hw3.model.people.Person;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public final class Customer extends Person implements Transportable, Serializable {
    private static final Logger LOGGER = LogManager.getLogger(Customer.class);
    private static int customersCount;
    private Double spentMoney;

    public Customer(String firstName, String lastName, String phoneNumber) throws InvalidPersonDataException {
        super(firstName, lastName, phoneNumber);
        this.spentMoney = 10.0;
        writeCustomerToFile();
        customersCount++;
    }

    public static int getCustomersCount() {
        return customersCount;
    }

    public static List<Customer> loadCustomersFromFile() {
        List<Customer> customers = new ArrayList<>();

        try {
            File file = new File("src/main/resources/customers_list");
            if (file.exists()) {
                List<String> lines = FileUtils.readLines(file, StandardCharsets.UTF_8);

                Customer customer = null;
                for (String line : lines) {
                    if (line.startsWith("Name: ")) {
                        if (customer != null) {
                            customers.add(customer);
                        }

                        String[] nameParts = line.replace("Name: ", "").split(" ");
                        customer = new Customer(nameParts[0], nameParts[1], "");
                    } else if (line.startsWith("Phone Number: ")) {
                        if (customer != null) {
                            customer.setPhoneNumber(line.replace("Phone Number: ", ""));
                        }
                    } else if (line.startsWith("Spent Money: ")) {
                        if (customer != null) {
                            customer.setSpentMoney(Double.parseDouble(line.replace("Spent Money: ", "")));
                        }
                    }
                }

                if (customer != null) {
                    customers.add(customer);
                }
            }
        } catch (IOException e) {
            LOGGER.error("Error loading customer information from file: " + e.getMessage());
        } catch (InvalidPersonDataException | NumberFormatException e) {
            LOGGER.error("Error creating customer object: " + e.getMessage());
        }

        return customers;
    }

    private void writeCustomerToFile() {
        try {
            StringBuilder data = new StringBuilder();

            data.append("Customer Information\n");
            data.append("Name: ").append(this.getFirstName()).append(" ").append(getLastName()).append("\n");
            data.append("Phone Number: ").append(this.getPhoneNumber()).append("\n");
            data.append("Spent Money: ").append(this.spentMoney).append("\n");
            data.append(StringUtils.repeat("-", 20)).append("\n");

            FileUtils.writeStringToFile(new File("src/main/resources/customers_list"), data.toString(), StandardCharsets.UTF_8, true);
            LOGGER.info("Customer information saved to file successfully.");
        } catch (IOException e) {
            LOGGER.error("Error saving customer information to file: " + e.getMessage());
        }
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
