package com.solvd.laba.hw3.menu.company;

import com.solvd.laba.hw3.common.enums.DriverStatusType;
import com.solvd.laba.hw3.menu.input.InputReader;
import com.solvd.laba.hw3.model.TaxiCompany;
import com.solvd.laba.hw3.model.people.employees.Accountant;
import com.solvd.laba.hw3.model.people.employees.Driver;
import com.solvd.laba.hw3.model.vehicles.Taxi;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.util.Scanner;

public class EmployeeMenu {
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    public static Driver selectDriver(Scanner scanner, TaxiCompany taxiCompany) throws Exception {
        LOGGER.info("Select a driver from the list:");

        for (int i = 0; i < taxiCompany.getDrivers().size(); i++) {
            if (taxiCompany.getDrivers().get(i).getDriverStatus() == DriverStatusType.AVAILABLE) {
                LOGGER.info(i + ". " + taxiCompany.getDrivers().get(i).getFirstName());
            }
        }
        int driverChoice = scanner.nextInt();

        if (driverChoice >= 0 && driverChoice < taxiCompany.getDrivers().size()) {
            return taxiCompany.getDrivers().get(driverChoice);
        } else {
            LOGGER.info("Invalid driver choice.");
            throw new Exception();
        }
    }


    public static void addDriver(Scanner scanner, TaxiCompany taxiCompany) {

        Driver driver = new Driver();
        PersonMenu.createPerson(scanner, driver);

        LOGGER.info("Select a vehicle for the driver:");
        taxiCompany.printVehicles();
        int vehicleChoice = scanner.nextInt();

        if (vehicleChoice >= 0 && vehicleChoice < taxiCompany.getVehicles().size()) {
            Taxi selectedVehicle = taxiCompany.getVehicles().get(vehicleChoice - 1);

            LOGGER.info("Enter driver salary: ");
            Integer salary = InputReader.readSalary(scanner);

            driver.setVehicle(selectedVehicle);
            driver.setSalary(salary);
            taxiCompany.addDriver(driver);
            LOGGER.info("New driver assigned to the company.");
        }
    }

    public static void addAccountant(Scanner scanner, TaxiCompany taxiCompany) {

        Accountant accountant = new Accountant();
        PersonMenu.createPerson(scanner, accountant);

        LOGGER.info("Enter accountant salary: ");
        Integer salary = InputReader.readSalary(scanner);

        accountant.setSalary(salary);

        taxiCompany.addAccountant(accountant);
        LOGGER.info("New accountant assigned to the company.");
    }

}
