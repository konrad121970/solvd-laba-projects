package com.solvd.laba.hw3.menu.company;

import com.solvd.laba.hw3.common.enums.TaxiStandardType;
import com.solvd.laba.hw3.common.exceptions.DuplicateRegistrationPlateException;
import com.solvd.laba.hw3.common.exceptions.InvalidNumberOfSeatsException;
import com.solvd.laba.hw3.common.exceptions.InvalidStarRatingException;
import com.solvd.laba.hw3.menu.input.InputReader;
import com.solvd.laba.hw3.model.TaxiCompany;
import com.solvd.laba.hw3.model.vehicles.Taxi;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.util.Scanner;

public class TaxiCompanyMenu {
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    public static void addNewEmployee(Scanner scanner, TaxiCompany taxiCompany) {

        LOGGER.info("Select employee type:");
        LOGGER.info("1. Driver");
        LOGGER.info("2. Accountant");
        int employeeTypeChoice = scanner.nextInt();

        if (employeeTypeChoice == 1) {
            EmployeeMenu.addDriver(scanner, taxiCompany);
        } else if (employeeTypeChoice == 2) {

        } else {
            LOGGER.info("Invalid employee type choice.");
        }
    }

    public static void addNewVehicle(Scanner scanner, TaxiCompany taxiCompany) {
        LOGGER.info(StringUtils.center("Enter vehicle details", 50, "="));

        String make = InputReader.readUserInput(scanner, "Make");
        String model = InputReader.readUserInput(scanner, "Model");
        LOGGER.info(StringUtils.rightPad("Number of Seats:", 20) + StringUtils.leftPad("", 30));
        int numberOfSeats = InputReader.readNumberOfSeats(scanner);
        String registrationPlate = InputReader.readUserInput(scanner, "Plate Number");

        try {
            Taxi newTaxi = new Taxi(make, model, registrationPlate, numberOfSeats);
            taxiCompany.addVehicle(newTaxi);

            LOGGER.info(StringUtils.rightPad("Fare per kilometer:", 50));
            double farePerKilometer = InputReader.readDoubleData(scanner);
            newTaxi.setFarePerKilometer(farePerKilometer);

            LOGGER.info("Taxi standard: ");
            for (TaxiStandardType taxiStandardType : TaxiStandardType.values()) {
                LOGGER.info((taxiStandardType.ordinal() + 1) + ". " + taxiStandardType.getCategoryName());
            }

            int standardNumber = scanner.nextInt();

            TaxiStandardType selectedStandard = null;
            try {
                selectedStandard = TaxiStandardType.getByOption(standardNumber);
            } catch (InvalidStarRatingException e) {
                LOGGER.info("Invalid rating option. Defaulting to EXCELLENT.");
            }

            newTaxi.setTaxiStandard(selectedStandard);

            LOGGER.info(StringUtils.center("New taxi assigned to the company", 50, "="));
            taxiCompany.printVehicles();
        } catch (InvalidNumberOfSeatsException | DuplicateRegistrationPlateException ex) {
            LOGGER.error(ex.getMessage());
            LOGGER.error(StringUtils.center("Exiting menu option", 50, "="));
        }
    }


}
