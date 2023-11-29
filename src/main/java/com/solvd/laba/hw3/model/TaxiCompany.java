package com.solvd.laba.hw3.model;

import com.solvd.laba.hw3.common.exceptions.DuplicateRegistrationPlateException;
import com.solvd.laba.hw3.common.interfaces.Displayable;
import com.solvd.laba.hw3.model.people.customer.Customer;
import com.solvd.laba.hw3.model.people.employees.Accountant;
import com.solvd.laba.hw3.model.people.employees.Driver;
import com.solvd.laba.hw3.model.route.TransportOrder;
import com.solvd.laba.hw3.model.vehicles.Taxi;
import com.solvd.laba.hw3.model.vehicles.Vehicle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class TaxiCompany implements Displayable {
    private static final Logger LOGGER = LogManager.getLogger(TaxiCompany.class);
    private final String name;
    private double earnedMoney;
    private Map<Driver, List<TransportOrder>> driverTransportOrdersMap;
    private List<TransportOrder> transportOrders;
    private List<Customer> customers;
    private List<Driver> drivers;
    private List<Taxi> vehicles;
    private Set<Accountant> accountants;

    public TaxiCompany() {
        this("Default Company");
    }

    public TaxiCompany(String name) {
        this.name = name;
        this.driverTransportOrdersMap = new HashMap<>();
        this.transportOrders = new ArrayList<>();
        this.customers = new ArrayList<>();
        this.drivers = new ArrayList<>();
        this.vehicles = new ArrayList<>();
        this.accountants = new HashSet<>();
    }

    public TaxiCompany(String name, List<TransportOrder> transportOrders, List<Customer> customers, List<Driver> drivers, Set<Accountant> accountants, List<Taxi> vehicles) throws DuplicateRegistrationPlateException {
        this.name = name;
        addTransportOrders(transportOrders, drivers);
        addAccountants(accountants);
        addVehicles(vehicles);
        addCustomers(customers);
        addDrivers(drivers);
    }

    public Map<Driver, List<TransportOrder>> getDriverTransportOrdersMap() {
        return driverTransportOrdersMap;
    }

    public double getEarnedMoney() {
        return earnedMoney;
    }

    public void setEarnedMoney(double earnedMoney) {
        this.earnedMoney = earnedMoney;
    }

    public String getName() {
        return name;
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

    public List<Taxi> getVehicles() {
        return vehicles;
    }

    public void addVehicle(Taxi vehicle) throws DuplicateRegistrationPlateException {
        if (vehicle != null) {
            if (vehicles == null) {
                vehicles = new ArrayList<>();
            }
            isRegistrationPlateDuplicatePresent(vehicle);
            vehicles.add(vehicle);
        } else LOGGER.warn("TaxiVehicle cannot be null");
    }

    public void addVehicles(List<Taxi> vehicles) throws DuplicateRegistrationPlateException {
        if (vehicles != null) {
            if (this.vehicles == null) {
                this.vehicles = new ArrayList<>();
            }
            for (Taxi vehicle : vehicles) {
                isRegistrationPlateDuplicatePresent(vehicle);
                this.vehicles.add(vehicle);
            }
        } else LOGGER.warn("Vehicles list cannot be null");
    }

/*
    private void deleteEntry(Vehicle vehicle) {
        Iterator<Map.Entry<Driver, Vehicle>> iterator = driverVehicleMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Driver, Vehicle> entry = iterator.next();
            if (entry.getValue().equals(vehicle)) {
                iterator.remove();
                break;
            }
        }
        LOGGER.info("Vehicle removed: " + vehicle.getMake() + " " + vehicle.getModel() + " " + vehicle.getRegistrationPlate());
    }
*/

    public void deleteVehicle(Vehicle vehicle) {
        if (vehicle != null) {
            vehicles.remove(vehicle);
            //deleteEntry(vehicle);
        } else {
            LOGGER.warn("Vehicle cannot be null");
        }
    }

    public void deleteVehicles(List<Vehicle> vehicles) {
        if (drivers != null) {
            for (Vehicle vehicle : vehicles) {
                if (vehicle != null) {
                    this.vehicles.remove(vehicle);
                    //deleteEntry(vehicle);
                } else {
                    LOGGER.warn("Vehicle cannot be null");
                }
            }
        }
    }

    public void addDriver(Driver driver) {
        if (driver != null) {
            if (drivers == null) {
                drivers = new ArrayList<>();
            }
            drivers.add(driver);
        } else LOGGER.warn("Driver cannot be null");
    }

    public void addDrivers(List<Driver> drivers) {
        if (drivers != null) {
            if (this.drivers == null) {
                this.drivers = new ArrayList<>();
            }
            for (Driver driver : drivers) {
                this.drivers.add(driver);
            }
        } else LOGGER.warn("Drivers list cannot be null");
    }

    // Each driver comes to company with his own vehicle so it also has to be deleted
    public void deleteDriver(Driver driver) {
        if (driver != null) {
            drivers.remove(driver);
            vehicles.remove(driver.getVehicle());
        }
    }

    public void deleteDrivers(List<Driver> drivers) {
        if (drivers != null) {
            for (Driver driver : drivers) {
                if (driver != null) {
                    this.drivers.remove(driver);
                    vehicles.remove(driver.getVehicle());
                }
            }
        }
    }

    public void addAccountant(Accountant accountant) {
        if (accountant != null) {
            if (accountants == null) {
                accountants = new HashSet<>();
            }
            accountants.add(accountant);
        } else LOGGER.warn("Accountant cannot be null");
    }

    public void addAccountants(Set<Accountant> accountants) {
        if (accountants != null) {
            if (this.accountants == null) {
                this.accountants = new HashSet<>();
            }
            this.accountants.addAll(accountants);
        } else LOGGER.warn("Accountants list cannot be null");
    }

    public void addCustomer(Customer customer) {
        if (customer != null) {
            if (customers == null) {
                customers = new ArrayList<>();
            }
            customers.add(customer);
        } else LOGGER.warn("Customer cannot be null");
    }

    public void addCustomers(List<Customer> customers) {
        if (customers != null) {
            if (this.customers == null) {
                this.customers = new ArrayList<>();
            }
            this.customers.addAll(customers);
        } else LOGGER.warn("Customers list cannot be null");
    }

    public void addTransportOrder(TransportOrder transportOrder, Driver driver) {
        if (transportOrder != null) {
            if (transportOrders == null) {
                transportOrders = new ArrayList<>();
            }
            transportOrders.add(transportOrder);
            this.earnedMoney += transportOrder.getPayment().getAmount();

            if (!driverTransportOrdersMap.containsKey(driver)) { // Check if Map contains the driver
                driverTransportOrdersMap.put(driver, new ArrayList<>()); // If not add new entry
            }
            driverTransportOrdersMap.get(driver).add(transportOrder);
        } else LOGGER.warn("TransportOrder cannot be null");
    }

    public void addTransportOrders(List<TransportOrder> transportOrders, List<Driver> drivers) {
        if (transportOrders != null) {
            if (this.transportOrders == null) {
                this.transportOrders = new ArrayList<>();
            }
            transportOrders.forEach(tr -> {
                if (tr != null) {
                    this.transportOrders.add(tr);
                    this.earnedMoney += tr.getPayment().getAmount();

                    Driver driver = drivers.get(transportOrders.indexOf(tr));

                    if (!driverTransportOrdersMap.containsKey(driver)) {
                        driverTransportOrdersMap.put(driver, new ArrayList<>()); // create new key
                    }
                    driverTransportOrdersMap.get(driver).add(tr); // Add Transport order to driver key
                } else {
                    LOGGER.warn("TransportOrder in the list cannot be null");
                }
            });

        } else {
            LOGGER.warn("TransportOrders list cannot be null");
        }
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
        int i = 1;
        for (Vehicle vehicle : vehicles) {
            LOGGER.info(i + ". " + vehicle.getMake() + " " + vehicle.getModel() + " " + vehicle.getRegistrationPlate());
            i++;
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

    public void printDriverTransportOrders() {
        LOGGER.info("Driver Transport Orders:");
        for (Map.Entry<Driver, List<TransportOrder>> entry : driverTransportOrdersMap.entrySet()) {
            Driver driver = entry.getKey();
            List<TransportOrder> transportOrders = entry.getValue();

            LOGGER.info("Driver: " + driver.getFirstName() + " " + driver.getLastName());
            LOGGER.info("Transport Orders:");

            for (TransportOrder transportOrder : transportOrders) {
                transportOrder.showDetails();
            }
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
                ", transportOrders=" + transportOrders +
                ", customers=" + customers +
                ", drivers=" + drivers +
                ", accountants=" + accountants +
                ", vehicles=" + vehicles +
                '}');
    }

}
