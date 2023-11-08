package com.solvd.laba.hw3.model;

import com.solvd.laba.hw3.model.exceptions.DuplicateRegistrationPlateException;
import com.solvd.laba.hw3.model.interfaces.Displayable;
import com.solvd.laba.hw3.model.people.customer.Customer;
import com.solvd.laba.hw3.model.people.employees.Accountant;
import com.solvd.laba.hw3.model.people.employees.Driver;
import com.solvd.laba.hw3.model.route.TransportOrder;
import com.solvd.laba.hw3.model.vehicles.Vehicle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

public class TaxiCompany implements Displayable {
    private static final Logger LOGGER = LogManager.getLogger(TaxiCompany.class);
    private final String name;
    private TransportOrder[] transportOrders;
    private Customer[] customers;
    private Driver[] drivers;
    private Accountant[] accountants;
    private Vehicle[] vehicles;

    public TaxiCompany(String name) {
        this.name = name;
    }

    public TaxiCompany(String name, TransportOrder[] transportOrders, Customer[] customers, Driver[] drivers, Accountant[] accountants, Vehicle[] vehicles) {
        this.name = name;
        this.transportOrders = transportOrders;
        this.customers = customers;
        this.drivers = drivers;
        this.accountants = accountants;
        this.vehicles = vehicles;
    }

    public void printCustomerNames() {
        int i = 1;
        for (Customer customer : customers) {
            LOGGER.info("Customer " + i + ": " + customer.getFirstName() + " " + customer.getLastName());
            i++;
        }
    }

    public void addVehicle(Vehicle vehicle) throws DuplicateRegistrationPlateException {
        isRegistrationPlateDuplicatePresent(vehicle);

        Vehicle[] newVehicle = new Vehicle[vehicles.length + 1];
        System.arraycopy(vehicles, 0, newVehicle, 0, vehicles.length);
        newVehicle[vehicles.length] = vehicle;
        this.vehicles = newVehicle;
    }

    public void addDriver(Driver driver) {
        Driver[] newDrivers = new Driver[drivers.length + 1];

        System.arraycopy(drivers, 0, newDrivers, 0, drivers.length);
        newDrivers[vehicles.length] = driver;
        this.drivers = newDrivers;
    }

    public void addCustomer(Customer customer) {
        Customer[] newCustomers = new Customer[customers.length + 1];

        System.arraycopy(customers, 0, newCustomers, 0, customers.length);
        newCustomers[vehicles.length] = customer;
        this.customers = newCustomers;
    }

    public void isRegistrationPlateDuplicatePresent(Vehicle vehicle) throws DuplicateRegistrationPlateException {
        for (int i = 0; i < vehicles.length; i++) {
            if (vehicles[i].getRegistrationPlate().equals(vehicle.getRegistrationPlate())) {
                throw new DuplicateRegistrationPlateException("There is another car with the same registration plate assigned!");
            }
        }
    }

    public TransportOrder[] getTransportOrders() {
        return transportOrders;
    }

    public void setTransportOrders(TransportOrder[] transportOrders) {
        this.transportOrders = transportOrders;
    }

    public Customer[] getCustomers() {
        return customers;
    }

    public void setCustomers(Customer[] customers) {
        this.customers = customers;
    }

    public Driver[] getDrivers() {
        return drivers;
    }

    public void setDrivers(Driver[] drivers) {
        this.drivers = drivers;
    }

    public Accountant[] getAccountants() {
        return accountants;
    }

    public void setAccountants(Accountant[] accountants) {
        this.accountants = accountants;
    }

    public Vehicle[] getVehicles() {
        return vehicles;
    }

    public void setVehicles(Vehicle[] vehicles) {
        this.vehicles = vehicles;
    }

    public void printVehicles() {
        LOGGER.info("List of company vehicles:");
        for (Vehicle vehicle : vehicles) {
            LOGGER.info(vehicle.getMake() + " " + vehicle.getModel() + " " + vehicle.getRegistrationPlate());
        }
    }


    public void printCustomers() {
        LOGGER.info("List of company Customers:");
        for (Customer customer : customers) {
            LOGGER.info(customer.getFirstName() + " " + customer.getLastName() + " " + customer.getSpentMoney());
        }
    }

    public void printEmployees() {
        LOGGER.info("List of company Drivers:");
        for (Customer customer : customers) {
            LOGGER.info(customer.getFirstName() + " " + customer.getLastName() + " " + customer.getSpentMoney());
        }
        LOGGER.info("List of company Accountants:");
        for (Accountant accountant : accountants) {
            LOGGER.info(accountant.getFirstName() + " " + accountant.getLastName() + " " + accountant.getSalary());
        }
    }


    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "TaxiCompany{" +
                "transportOrders=" + Arrays.toString(transportOrders) +
                ", customers=" + Arrays.toString(customers) +
                ", drivers=" + Arrays.toString(drivers) +
                ", accountants=" + Arrays.toString(accountants) +
                ", vehicles=" + Arrays.toString(vehicles) +
                '}';
    }

    // TODO: implement these methods
    @Override
    public void display() {
        LOGGER.info("Company name: " + this.name);
    }

    @Override
    public void showDetails() {
        LOGGER.info("TaxiCompany{" +
                "transportOrders=" + Arrays.toString(transportOrders) +
                ", customers=" + Arrays.toString(customers) +
                ", drivers=" + Arrays.toString(drivers) +
                ", accountants=" + Arrays.toString(accountants) +
                ", vehicles=" + Arrays.toString(vehicles) +
                '}');
    }
}
