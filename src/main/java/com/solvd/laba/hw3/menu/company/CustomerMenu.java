package com.solvd.laba.hw3.menu.company;

import com.solvd.laba.hw3.exceptions.InvalidPersonDataException;
import com.solvd.laba.hw3.model.TaxiCompany;
import com.solvd.laba.hw3.model.people.customer.Customer;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.util.Scanner;

public class CustomerMenu {
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    public static void addCustomer(Scanner scanner, TaxiCompany taxiCompany) {

        LOGGER.info("Customer Name: ");
        String customerName = scanner.nextLine();
        LOGGER.info("Customer Last Name: ");
        String customerLastName = scanner.nextLine();
        LOGGER.info("Customer Phone Number: ");
        String customerPhoneNumber = scanner.next();
        Customer customer;

        try {
            customer = new Customer(StringUtils.capitalize(customerName).trim(), StringUtils.capitalize(customerLastName).trim(), customerPhoneNumber);
            taxiCompany.addCustomer(customer);
        } catch (InvalidPersonDataException e) {
            throw new RuntimeException(e);
        }
    }

}
