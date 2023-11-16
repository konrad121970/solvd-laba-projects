package com.solvd.laba.hw3.model;

import com.solvd.laba.hw3.model.exceptions.DuplicateRegistrationPlateException;
import com.solvd.laba.hw3.model.interfaces.Displayable;
import com.solvd.laba.hw3.model.people.Employee;
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

    public void setTransportOrders(List<TransportOrder> transportOrders) {
        this.transportOrders = transportOrders;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public List<Driver> getDrivers() {
        return drivers;
    }

    public void setDrivers(List<Driver> drivers) {
        this.drivers = drivers;
    }

    public Set<Accountant> getAccountants() {
        return accountants;
    }

    public void setAccountants(Set<Accountant> accountants) {
        this.accountants = accountants;
    }

    public List<TaxiVehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<TaxiVehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public void addDriverWithVehicle(Driver driver, TaxiVehicle vehicle) throws DuplicateRegistrationPlateException {
/*        if (driverVehicleMap.isEmpty()) {
            driverVehicleMap = new HashMap<>();
        }*/
        addDriver(driver);
        addVehicle(vehicle);
        driver.setVehicle(vehicle);
        driverVehicleMap.put(driver, vehicle);
    }

    public void printCustomerNames() {
        int i = 1;
        for (Customer customer : customers) {
            LOGGER.info("Customer " + i + ": " + customer.getFirstName() + " " + customer.getLastName());
            i++;
        }
    }

    public void addVehicle(TaxiVehicle vehicle) throws DuplicateRegistrationPlateException {
        isRegistrationPlateDuplicatePresent(vehicle);
        if (vehicles.isEmpty()) {
            vehicles = new ArrayList<>();
        }
        vehicles.add(vehicle);
    }

    public void addDriver(Driver driver) {
        if (drivers.isEmpty()) {
            drivers = new ArrayList<>();
        }
        drivers.add(driver);
    }

    public void addCustomer(Customer customer) {
        if (customers.isEmpty()) {
            customers = new ArrayList<>();
        }
        customers.add(customer);
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

    public void printEmployees() {
        LOGGER.info("List of company Drivers:");
        for (Customer customer : customers) {
            LOGGER.info(customer.getFirstName() + " " + customer.getLastName() + " " + customer.getSpentMoney());
        }
        LOGGER.info("List of company Accountants:");
        for (Employee accountant : accountants) {
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
                "name='" + name + '\'' +
                ", driverVehicleMap=" + driverVehicleMap +
                ", transportOrders=" + transportOrders +
                ", customers=" + customers +
                ", drivers=" + drivers +
                ", accountants=" + accountants +
                ", vehicles=" + vehicles +
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
