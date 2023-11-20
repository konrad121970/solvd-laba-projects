package com.solvd.laba.hw3.model;

import com.solvd.laba.hw3.model.exceptions.DuplicateRegistrationPlateException;
import com.solvd.laba.hw3.model.interfaces.Displayable;
import com.solvd.laba.hw3.model.people.customer.Customer;
import com.solvd.laba.hw3.model.people.employees.Accountant;
import com.solvd.laba.hw3.model.people.employees.Driver;
import com.solvd.laba.hw3.model.route.TransportOrder;
import com.solvd.laba.hw3.model.vehicles.TaxiVehicle;
import com.solvd.laba.hw3.model.vehicles.Vehicle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class TaxiCompany implements Displayable {
    private static final Logger LOGGER = LogManager.getLogger(TaxiCompany.class);
    private final String name;
    private Map<Driver, Vehicle> driverVehicleMap;
    private List<TransportOrder> transportOrders;
    private List<Customer> customers;
    private List<Driver> drivers;
    private List<TaxiVehicle> vehicles;
    private Set<Accountant> accountants;

    public TaxiCompany() {
        this("Default Company");
    }

    public TaxiCompany(String name) {
        this.name = name;
        this.driverVehicleMap = new HashMap<>();
        this.transportOrders = new ArrayList<>();
        this.customers = new ArrayList<>();
        this.drivers = new ArrayList<>();
        this.vehicles = new ArrayList<>();
        this.accountants = new HashSet<>();
    }

    public TaxiCompany(String name, List<TransportOrder> transportOrders, List<Customer> customers, List<Driver> drivers, Set<Accountant> accountants, List<TaxiVehicle> vehicles) {
        this.name = name;
        this.driverVehicleMap = new HashMap<>();
        this.transportOrders = transportOrders;
        this.customers = customers;
        this.drivers = drivers;
        this.accountants = accountants;
        this.vehicles = vehicles;
    }

    public String getName() {
        return name;
    }

    public Map<Driver, Vehicle> getDriverVehicleMap() {
        return driverVehicleMap;
    }

    public List<TransportOrder> getTransportOrders() {
        return transportOrders;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public List<Driver> getDrivers() {
        return drivers;
    }

    public Set<Accountant> getAccountants() {
        return accountants;
    }

    public List<TaxiVehicle> getVehicles() {
        return vehicles;
    }

/*    public void addDriverWithVehicle(Driver driver, TaxiVehicle vehicle) throws DuplicateRegistrationPlateException {
        if (driverVehicleMap.isEmpty()) {
            driverVehicleMap = new HashMap<>();
        }
        addDriver(driver);
        addVehicle(vehicle);
        driver.setVehicle(vehicle);
        driverVehicleMap.put(driver, vehicle);
    }*/


    public void addVehicle(TaxiVehicle vehicle) throws DuplicateRegistrationPlateException {
        if (vehicle != null) {
            if (vehicles == null) {
                vehicles = new ArrayList<>();
            }
            isRegistrationPlateDuplicatePresent(vehicle);
            vehicles.add(vehicle);
        } else throw new IllegalArgumentException("TaxiVehicle cannot be null");
    }

    public void addVehicles(List<TaxiVehicle> vehicles) throws DuplicateRegistrationPlateException {
        if (vehicles != null) {
            if (this.vehicles == null) {
                this.vehicles = new ArrayList<>();
            }
            for (TaxiVehicle vehicle : vehicles) {
                isRegistrationPlateDuplicatePresent(vehicle);
                this.vehicles.add(vehicle);
            }
        } else throw new IllegalArgumentException("Vehicles list cannot be null");
    }

    public void addDriver(Driver driver) throws DuplicateRegistrationPlateException {
        if (driver != null) {
            if (drivers == null) {
                drivers = new ArrayList<>();
            }
            drivers.add(driver);
            driverVehicleMap.put(driver, driver.getVehicle());
        } else throw new IllegalArgumentException("Driver cannot be null");
    }

    public void addDrivers(List<Driver> drivers) throws DuplicateRegistrationPlateException {
        if (drivers != null) {
            if (this.drivers == null) {
                this.drivers = new ArrayList<>();
            }
            for (Driver driver : drivers) {
                this.drivers.add(driver);
                driverVehicleMap.put(driver, driver.getVehicle());
            }
        } else throw new IllegalArgumentException("Drivers list cannot be null");
    }

    public void addCustomer(Customer customer) {
        if (customer != null) {
            if (customers == null) {
                customers = new ArrayList<>();
            }
            customers.add(customer);
        } else throw new IllegalArgumentException("Customer cannot be null");
    }

    public void addCustomers(List<Customer> customers) {
        if (customers != null) {
            if (this.customers == null) {
                this.customers = new ArrayList<>();
            }
            this.customers.addAll(customers);
        } else throw new IllegalArgumentException("Customers list cannot be null");
    }

    public void addTransportOrder(TransportOrder transportOrder) {
        if (transportOrder != null) {
            if (transportOrders == null) {
                transportOrders = new ArrayList<>();
            }
            transportOrders.add(transportOrder);
        } else throw new IllegalArgumentException("TransportOrder cannot be null");
    }

    public void addTransportOrder(List<TransportOrder> transportOrders) {
        if (transportOrders != null) {
            if (this.transportOrders == null) {
                this.transportOrders = new ArrayList<>();
            }
            this.transportOrders.addAll(transportOrders);
        } else throw new IllegalArgumentException("TransportOrders list cannot be null");
    }

    public void isRegistrationPlateDuplicatePresent(Vehicle vehicle) throws DuplicateRegistrationPlateException {
        for (Vehicle v : vehicles) {
            if (v.getRegistrationPlate().equals(vehicle.getRegistrationPlate())) {
                throw new DuplicateRegistrationPlateException("There is another car with the same registration plate assigned!" + v.getRegistrationPlate());
            }
        }
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

    public void printDrivers() {
        LOGGER.info("List of company Drivers:");
        for (Driver driver : drivers) {
            LOGGER.info(driver.getFirstName() + " " + driver.getLastName());
        }
    }

    public void printAccountants() {
        LOGGER.info("List of company Drivers:");
        for (Accountant accountant : accountants) {
            LOGGER.info(accountant.getFirstName() + " " + accountant.getLastName());
        }
    }

    public void printEmployees() {
        printAccountants();
        printDrivers();
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
                "name='" + name + '\'' +
                ", driverVehicleMap=" + driverVehicleMap +
                ", transportOrders=" + transportOrders +
                ", customers=" + customers +
                ", drivers=" + drivers +
                ", accountants=" + accountants +
                ", vehicles=" + vehicles +
                '}';
    }

    @Override
    public void display() {
        LOGGER.info("Company name: " + this.name);
    }

    @Override
    public void showDetails() {
        LOGGER.info("TaxiCompany{" +
                "name='" + name + '\'' +
                ", driverVehicleMap=" + driverVehicleMap +
                ", transportOrders=" + transportOrders +
                ", customers=" + customers +
                ", drivers=" + drivers +
                ", accountants=" + accountants +
                ", vehicles=" + vehicles +
                '}');
    }
}
