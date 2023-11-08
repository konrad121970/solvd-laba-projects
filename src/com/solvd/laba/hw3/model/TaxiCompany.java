package com.solvd.laba.hw3.model;

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
    private TransportOrder[] transportOrders;
    private Customer[] customers;
    private Driver[] drivers;
    private Accountant[] accountants;
    private Vehicle[] vehicles;

    public TaxiCompany(TransportOrder[] transportOrders, Customer[] customers, Driver[] drivers, Accountant[] accountants, Vehicle[] vehicles) {
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

    public void addVehicle(Vehicle vehicle) {
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
        for (Vehicle vehicle : vehicles) {
            LOGGER.info(vehicle.getMake() + " " + vehicle.getModel() + " " + vehicle.getRegistrationPlate());
        }
    }

    public void printCustomers() {
    }

    public void printEmployees() {
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

    }

    @Override
    public void showDetails() {

    }
}
