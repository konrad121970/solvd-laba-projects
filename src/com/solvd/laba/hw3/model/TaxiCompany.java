package com.solvd.laba.hw3.model;

import com.solvd.laba.hw3.model.people.customer.Customer;
import com.solvd.laba.hw3.model.people.employees.Accountant;
import com.solvd.laba.hw3.model.people.employees.Driver;
import com.solvd.laba.hw3.model.route.TransportOrder;
import com.solvd.laba.hw3.model.vehicles.Vehicle;

import java.util.Arrays;

public class TaxiCompany {
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
            System.out.println("Customer " + i + ": " + customer.getFirstName() + " " + customer.getLastName());
            i++;
        }
    }

    public void addVehicle(Vehicle vehicle) {
        Vehicle[] newVehicle = new Vehicle[vehicles.length + 1];

        for (int i = 0; i < vehicles.length; i++) {
            newVehicle[i] = vehicles[i];
        }
        newVehicle[vehicles.length] = vehicle;
        this.vehicles = newVehicle;
    }

    public void addDriver(Driver driver) {
        Driver[] newDrivers = new Driver[drivers.length + 1];

        for (int i = 0; i < drivers.length; i++) {
            newDrivers[i] = drivers[i];
        }
        newDrivers[vehicles.length] = driver;
        this.drivers = newDrivers;
    }

    public void addCustomer(Customer customer) {
        Customer[] newCustomers = new Customer[customers.length + 1];

        for (int i = 0; i < customers.length; i++) {
            newCustomers[i] = customers[i];
        }
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
            System.out.println(vehicle.getMake() + " " + vehicle.getModel() + " " + vehicle.getRegistrationPlate());
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
}
