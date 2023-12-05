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
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.IntStream;

public class EmployeeMenu {
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    public static Driver selectDriver(Scanner scanner, TaxiCompany taxiCompany) throws Exception {
        LOGGER.info("Select a driver from the list:");

        Optional<List<Driver>> drivers = taxiCompany.getDrivers();

        if (drivers.isPresent()) {
            List<Driver> availableDrivers = taxiCompany
                    .filterDrivers(d -> d.getDriverStatus() == DriverStatusType.AVAILABLE);

            IntStream.range(0, availableDrivers.size()).forEach(i -> {
                LOGGER.info(i + ". " + availableDrivers.get(i).getFirstName() + " " + availableDrivers.get(i).getLastName());
            });

            int driverChoice = scanner.nextInt();

            if (driverChoice >= 0 && driverChoice < availableDrivers.size()) {
                return availableDrivers.get(driverChoice);
            } else {
                LOGGER.info("Invalid driver choice.");
                throw new Exception();
            }
        } else {
            throw new Exception("No available drivers");
        }
    }


    public static void addDriver(Scanner scanner, TaxiCompany taxiCompany) {

        Driver driver = new Driver();
        PersonMenu.createPerson(scanner, driver);

        LOGGER.info("Select a vehicle for the driver:");
        taxiCompany.printVehicles();
        int vehicleChoice = scanner.nextInt();

        if (taxiCompany.getVehicles().isPresent()) {

            List<Taxi> vehiclesList = taxiCompany.getVehicles().get();

            if (vehicleChoice >= 0 && vehicleChoice < vehiclesList.size() + 1) {
                Taxi selectedVehicle = vehiclesList.get(vehicleChoice - 1);

                LOGGER.info("Enter driver salary: ");
                Integer salary = InputReader.readSalary(scanner);

                driver.setVehicle(selectedVehicle);
                driver.setSalary(salary);
                taxiCompany.addDriver(driver);
                LOGGER.info("New driver assigned to the company.");
            } else {
                LOGGER.info("Invalid vehicle choice.");
            }
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
