package com.solvd.laba.hw3.creators;

import com.solvd.laba.hw3.model.TaxiCompany;
import com.solvd.laba.hw3.model.exceptions.InvalidEmployeeDataException;
import com.solvd.laba.hw3.model.exceptions.InvalidNumberOfSeatsException;
import com.solvd.laba.hw3.model.exceptions.InvalidPersonDataException;
import com.solvd.laba.hw3.model.people.customer.Customer;
import com.solvd.laba.hw3.model.people.employees.Accountant;
import com.solvd.laba.hw3.model.people.employees.Driver;
import com.solvd.laba.hw3.model.route.TransportOrder;
import com.solvd.laba.hw3.model.vehicles.TaxiVehicle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TaxiCompanyCreator {

    private static final Logger LOGGER = LogManager.getLogger(TaxiCompanyCreator.class);

    static {
        LOGGER.info("TaxiCompanyCreator class initialized.");
    }

    public static TaxiCompany create() {
        TaxiVehicle[] vehicles = createVehicles();
        Driver[] drivers = createDrivers(vehicles);

        Customer[] customers = createCustomers();
        Accountant[] accountants = createAccountants();

        //[] locations = createLocations();
        TransportOrder[] transportOrders = new TransportOrder[]{};
        return new TaxiCompany("DzieduszkaTrans", transportOrders, customers, drivers, accountants, vehicles);
    }

    private static TaxiVehicle[] createVehicles() {

        try {
            return new TaxiVehicle[]{
                    new TaxiVehicle("Audi", "A4", "BHA 18XX", 4, 2.50),
                    new TaxiVehicle("Volkswagen", "Kubelwagen", "BI 1234", 5, 3.00)
            };
        } catch (InvalidNumberOfSeatsException e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    private static Driver[] createDrivers(TaxiVehicle[] vehicles) {
        try {
            return new Driver[]{
                    new Driver("Bartolomeo", "Diaz", 23, "123123123", vehicles[0], 3500),
                    new Driver("Leon", "Kaputt", 67, "123123123", vehicles[1], 4000)
            };
        } catch (InvalidPersonDataException e) {
            LOGGER.error(e.getMessage());
            return null;
        } catch (InvalidEmployeeDataException e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    private static Accountant[] createAccountants() {
        try {
            return new Accountant[]{
                    new Accountant("Katharine", "Note", "123123123", 23, 2500),
                    new Accountant("Elias", "Bismark", "123123123", 19, 4000)
            };
        } catch (InvalidPersonDataException e) {
            LOGGER.error(e.getMessage());
            return null;
        } catch (InvalidEmployeeDataException e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    private static Customer[] createCustomers() {
        try {
            return new Customer[]{
                    new Customer("Andrzej", "Kowalski", "123123123"),
                    new Customer("Paolo", "Nowak", "111222333"),
                    new Customer("Herbert", "Shmidt", "333222111")
            };
        } catch (InvalidPersonDataException e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }
}