package com.solvd.laba.hw3.common.builders;

import com.solvd.laba.hw3.model.TaxiCompany;
import com.solvd.laba.hw3.model.people.customer.Customer;
import com.solvd.laba.hw3.model.people.employees.Accountant;
import com.solvd.laba.hw3.model.people.employees.Driver;
import com.solvd.laba.hw3.model.route.TransportOrder;
import com.solvd.laba.hw3.model.vehicles.Taxi;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Set;

public class TaxiCompanyBuilder {
    private static final Logger LOGGER = LogManager.getLogger(TaxiCompanyBuilder.class);
    private String name;
    private List<TransportOrder> transportOrders;
    private List<Customer> customers;
    private List<Driver> drivers;
    private List<Taxi> vehicles;
    private Set<Accountant> accountants;

    public TaxiCompanyBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public TaxiCompanyBuilder setTransportOrders(List<TransportOrder> transportOrders) {
        this.transportOrders = transportOrders;
        return this;
    }

    public TaxiCompanyBuilder setAccountants(Set<Accountant> accountants) {
        this.accountants = accountants;
        return this;
    }

    public TaxiCompanyBuilder setVehicles(List<Taxi> vehicles) {
        this.vehicles = vehicles;
        return this;
    }

    public TaxiCompanyBuilder setCustomers(List<Customer> customers) {
        this.customers = customers;
        return this;
    }

    public TaxiCompanyBuilder setDrivers(List<Driver> drivers) {
        this.drivers = drivers;
        return this;
    }

    public TaxiCompany build() {
        TaxiCompany taxiCompany = new TaxiCompany(this.name);
        LOGGER.info("TaxiCompanyBuilder has been used to create taxiCompany class!");

        if (transportOrders != null) {
            taxiCompany.addTransportOrders(transportOrders, drivers);
        }

        if (accountants != null) {
            taxiCompany.addAccountants(accountants);
        }

        if (vehicles != null) {
            taxiCompany.addVehicles(vehicles);
        }

        if (customers != null) {
            taxiCompany.addCustomers(customers);
        }

        if (drivers != null) {
            taxiCompany.addDrivers(drivers);
        }

        return taxiCompany;
    }
}
