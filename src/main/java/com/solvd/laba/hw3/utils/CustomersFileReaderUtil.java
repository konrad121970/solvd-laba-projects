package com.solvd.laba.hw3.utils;

import com.solvd.laba.hw3.common.exceptions.InvalidPersonDataException;
import com.solvd.laba.hw3.model.TaxiCompany;
import com.solvd.laba.hw3.model.people.customer.Customer;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


public class CustomersFileReaderUtil {

    public static List<Customer> loadCustomersFromFile(TaxiCompany taxiCompany) {
        List<Customer> customers = new ArrayList<>();

        try {
            File file = new File("src/main/resources/customers_list");
            if (!file.exists()) {
                return customers;
            }

            try (LineIterator iterator = FileUtils.lineIterator(file, StandardCharsets.UTF_8.toString())) {
                Customer customer = null;

                while (iterator.hasNext()) {
                    String line = iterator.nextLine();

                    if (line.startsWith("Name: ")) {
                        customer = createCustomerFromNameLine(line);
                    } else if (line.startsWith("Phone Number: ")) {
                        setPhoneNumberForCustomer(customer, line);
                    } else if (line.startsWith("Spent Money: ")) {
                        addCustomerToListIfValid(customer, line, customers);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading customer information from file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Error creating customer object: " + e.getMessage());
        }
        return customers;
    }

    private static Customer createCustomerFromNameLine(String line) {
        String[] nameParts = line.replace("Name: ", "").split(" ");
        try {
            return new Customer(nameParts[0], nameParts[1], "");
        } catch (InvalidPersonDataException e) {
            throw new RuntimeException(e);
        }
    }

    private static void setPhoneNumberForCustomer(Customer customer, String line) {
        if (customer != null) {
            String number = line.replace("Phone Number: ", "");
            customer.setPhoneNumber(number);
        }
    }

    private static void addCustomerToListIfValid(Customer customer, String line, List<Customer> customers) {
        if (customer != null) {
            customer.setSpentMoney(Double.parseDouble(line.replace("Spent Money: ", "")));
            customers.add(customer);
        }
    }
}

