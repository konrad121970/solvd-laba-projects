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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class TaxiCompanyCreator {

    private static final Logger LOGGER = LogManager.getLogger(TaxiCompanyCreator.class);

    static {
        LOGGER.info("TaxiCompanyCreator class initialized.");
    }

    public static TaxiCompany create() {
        ArrayList<TaxiVehicle> vehicles = createVehicles();
        ArrayList<Driver> drivers = createDrivers(vehicles);

        ArrayList<Customer> customers = createCustomers();
        Set<Accountant> accountants = createAccountants();

        //[] locations = createLocations();
        ArrayList<TransportOrder> transportOrders = new ArrayList<>();
        return new TaxiCompany("DzieduszkaTrans", transportOrders, customers, drivers, accountants, vehicles);
    }

    private static ArrayList<TaxiVehicle> createVehicles() {

        try {
            ArrayList<TaxiVehicle> taxiVehicleArrayList = new ArrayList<>();
            taxiVehicleArrayList.add(new TaxiVehicle("Audi", "A4", "BHA 18XX", 4, 2.50));
            taxiVehicleArrayList.add(new TaxiVehicle("Volkswagen", "Kubelwagen", "BI 1234", 5, 3.00));
            return taxiVehicleArrayList;
        } catch (InvalidNumberOfSeatsException e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    private static ArrayList<Driver> createDrivers(ArrayList<TaxiVehicle> vehicles) {
        try {
            ArrayList<Driver> driverArrayList = new ArrayList<>();
            driverArrayList.add(new Driver("Bartolomeo", "Diaz", 23, "123123123", vehicles.get(0), 3500));
            driverArrayList.add(new Driver("Leon", "Kaputt", 67, "123123123", vehicles.get(1), 4000));
            return driverArrayList;
        } catch (InvalidPersonDataException e) {
            LOGGER.error(e.getMessage());
            return null;
        } catch (InvalidEmployeeDataException e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    private static Set<Accountant> createAccountants() {
        try {
            Set<Accountant> accountantsSet = new HashSet<>();
            accountantsSet.add(new Accountant("Katharine", "Note", "123123123", 23, 2500));
            accountantsSet.add(new Accountant("Elias", "Bismark", "123123123", 19, 4000));
            return accountantsSet;
        } catch (InvalidPersonDataException e) {
            LOGGER.error(e.getMessage());
            return null;
        } catch (InvalidEmployeeDataException e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }


    private static ArrayList<Customer> createCustomers() {
        try {
            ArrayList<Customer> customerArrayList = new ArrayList<>();
            customerArrayList.add(new Customer("Andrzej", "Kowalski", "123123123"));
            customerArrayList.add(new Customer("Paolo", "Nowak", "111222333"));
            customerArrayList.add(new Customer("Herbert", "Shmidt", "333222111"));
            return customerArrayList;
        } catch (InvalidPersonDataException e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }
}
