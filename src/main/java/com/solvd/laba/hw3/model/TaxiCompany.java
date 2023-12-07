package com.solvd.laba.hw3.model;

import com.solvd.laba.hw3.common.exceptions.DuplicateRegistrationPlateException;
import com.solvd.laba.hw3.common.interfaces.Displayable;
import com.solvd.laba.hw3.common.interfaces.EmployeeFilter;
import com.solvd.laba.hw3.model.people.customer.Customer;
import com.solvd.laba.hw3.model.people.employees.Accountant;
import com.solvd.laba.hw3.model.people.employees.Driver;
import com.solvd.laba.hw3.model.route.TransportOrder;
import com.solvd.laba.hw3.model.vehicles.Taxi;
import com.solvd.laba.hw3.model.vehicles.Vehicle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class TaxiCompany implements Displayable, Serializable {
    private static final Logger LOGGER = LogManager.getLogger(TaxiCompany.class);
    private final String name;
    private double earnedMoney;
    private List<TransportOrder> transportOrders;
    private Map<Driver, List<TransportOrder>> driverTransportOrdersMap;
    private List<Driver> drivers;
    private List<Customer> customers;
    private List<Taxi> vehicles;
    private Set<Accountant> accountants;

    public TaxiCompany() {
        this("Default Company");
    }

    public TaxiCompany(String name) {
        this.name = name;
    }

    public TaxiCompany(String name, List<TransportOrder> transportOrders, List<Customer> customers, List<Driver> drivers, Set<Accountant> accountants, List<Taxi> vehicles) {
        this.name = name;
        addTransportOrders(transportOrders, drivers);
        addAccountants(accountants);
        addVehicles(vehicles);
        addCustomers(customers);
        addDrivers(drivers);
    }

    public static TaxiCompany loadState(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            return (TaxiCompany) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            LOGGER.error("Error saving state: {}", e.getMessage());
            return null;
        }
    }

    public void saveState(String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(this);
            LOGGER.info("State saved successfully.");
        } catch (IOException e) {
            LOGGER.error("Error saving state: {}", e.getMessage());
        }
    }

    public Optional<Map<Driver, List<TransportOrder>>> getDriverTransportOrdersMap() {
        return Optional.ofNullable(driverTransportOrdersMap);
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

    public Optional<List<TransportOrder>> getTransportOrders() {
        return Optional.ofNullable(transportOrders);
    }

    public Optional<List<Customer>> getCustomers() {
        return Optional.ofNullable(customers);
    }

    public Optional<List<Driver>> getDrivers() {
        return Optional.ofNullable(drivers);
    }

    public Optional<Set<Accountant>> getAccountants() {
        return Optional.ofNullable(accountants);
    }

    public Optional<List<Taxi>> getVehicles() {
        return Optional.ofNullable(vehicles);
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

    public void addVehicles(List<Taxi> vehicles) {
        if (vehicles != null) {
            if (this.vehicles == null) {
                this.vehicles = new ArrayList<>();
            }
            vehicles.forEach(vehicle -> {
                try {
                    isRegistrationPlateDuplicatePresent(vehicle);
                } catch (DuplicateRegistrationPlateException e) {
                    LOGGER.warn("Vehicle with the same license plate already exists!");
                }
                this.vehicles.add(vehicle);
            });
        } else LOGGER.warn("Vehicles list cannot be null");
    }

    public void deleteVehicle(Vehicle vehicle) {
        if (vehicle != null) {
            vehicles.remove(vehicle);
        } else {
            LOGGER.warn("Vehicle cannot be null");
        }
    }

    public void deleteVehicles(List<Taxi> vehicles) {
        if (vehicles != null) {
            this.vehicles.removeAll(vehicles);
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
            this.drivers.addAll(drivers);
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

            drivers.forEach(driver -> {
                if (driver != null) {
                    this.drivers.remove(driver);
                    vehicles.remove(driver.getVehicle());
                }
            });
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

            if (driverTransportOrdersMap == null) {
                driverTransportOrdersMap = new HashMap<>();
            }

            if (!customers.contains(transportOrder.getCustomer())) {
                customers.add(transportOrder.getCustomer());
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
        if (transportOrders == null) {
            LOGGER.warn("TransportOrders list cannot be null");
            return;
        }

        if (this.transportOrders == null) {
            this.transportOrders = new ArrayList<>();
        }

        if (driverTransportOrdersMap == null) {
            driverTransportOrdersMap = new HashMap<>();
        }

        transportOrders.forEach(transportOrder -> {
            if (transportOrder == null) {
                LOGGER.warn("TransportOrder in the list cannot be null");
                return;
            }

            if (!customers.contains(transportOrder.getCustomer())) {
                customers.add(transportOrder.getCustomer());
            }

            this.transportOrders.add(transportOrder);
            this.earnedMoney += transportOrder.getPayment().getAmount();

            Driver driver = drivers.get(transportOrders.indexOf(transportOrder));

            driverTransportOrdersMap.computeIfAbsent(driver, k -> new ArrayList<>()).add(transportOrder);
        });
    }

    // PREDICATE
    public void isRegistrationPlateDuplicatePresent(Vehicle vehicle) throws DuplicateRegistrationPlateException {
        if (vehicles.stream().anyMatch(v -> v.getRegistrationPlate().equals(vehicle.getRegistrationPlate()))) {
            throw new DuplicateRegistrationPlateException("There is another car with the same registration plate assigned!" + vehicle.getRegistrationPlate());
        }
    }

    public void printVehicles() {
        LOGGER.info("List of company vehicles:");
        AtomicInteger counter = new AtomicInteger(1);
        vehicles.forEach(vehicle -> {
            LOGGER.info(counter.getAndIncrement() + ". " + vehicle.getMake() + " "
                    + vehicle.getModel() + " " + vehicle.getRegistrationPlate());
        });
    }

    public void printCustomers() {
        LOGGER.info("List of company Customers:");
        customers.forEach(customer -> {
            LOGGER.info(customer.getFirstName() + " " + customer.getLastName() + " " + customer.getSpentMoney());
        });
    }

    public void printDrivers() {
        LOGGER.info("List of company Drivers:");
        drivers.forEach(driver -> {
            LOGGER.info(driver.getFirstName() + " " + driver.getLastName());
        });
    }


    public void printDriverTransportOrders() {
        LOGGER.info("Driver Transport Orders:");
        driverTransportOrdersMap.forEach((driver, transportOrders) -> {

            LOGGER.info("Driver: " + driver.getFirstName() + " " + driver.getLastName());
            LOGGER.info("Transport Orders:");

            transportOrders.forEach(TransportOrder::showDetails);
        });
    }

    public void printAccountants() {
        LOGGER.info("List of company accountants:");
        accountants.forEach(accountant -> {
            LOGGER.info(accountant.getFirstName() + " " + accountant.getLastName());
        });
    }

    public void printEmployees() {
        printAccountants();
        printDrivers();
    }

    public List<Driver> filterDrivers(EmployeeFilter<Driver> employeeFilter) {
        LOGGER.info("List of Filtered Drivers:");

        return drivers.stream()
                .filter(employeeFilter::filter)
                .collect(Collectors.toList());
    }

    public List<Accountant> filterAccountants(EmployeeFilter<Accountant> employeeFilter) {
        LOGGER.info("List of Filtered Accountants:");

        return accountants.stream()
                .filter(employeeFilter::filter)
                .collect(Collectors.toList());
    }

    // Function
    public void printDrivers(Function<Driver, String> fullNameMapper) {

        LOGGER.info("List of Driver Full Names:");
        drivers.forEach(driver -> {
            LOGGER.info(fullNameMapper.apply(driver));
        });
    }

    // Predicate
    public List<Accountant> filterAccountants(Predicate<Accountant> accountantFilter) {
        LOGGER.info("List of Filtered Accountants:");
        return accountants.stream()
                .filter(accountantFilter)
                .collect(Collectors.toList());
    }

    // Consumer
    public void printAccountants(Consumer<Accountant> accountantConsumer) {
        LOGGER.info("List of company accountants:");
        accountants.forEach(accountantConsumer);
    }

    // Supplier
    public Customer createCustomer(Supplier<Customer> customerSupplier) {
        Customer newCustomer = customerSupplier.get();
        LOGGER.info("New customer created: " + newCustomer.getFirstName() + " " + newCustomer.getLastName());
        return newCustomer;
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
                ", earnedMoney=" + earnedMoney +
                ", transportOrders=" + transportOrders +
                ", driverTransportOrdersMap=" + driverTransportOrdersMap +
                ", drivers=" + drivers +
                ", customers=" + customers +
                ", vehicles=" + vehicles +
                ", accountants=" + accountants +
                '}';
    }

    @Override
    public void display() {
        LOGGER.info("Taxi Company Information:");
        LOGGER.info("Name: " + name);
        LOGGER.info("Earned Money: " + earnedMoney);

        if (vehicles != null && !vehicles.isEmpty()) {
            LOGGER.info("Number of Vehicles: " + vehicles.size());
        }

        if (drivers != null && !drivers.isEmpty()) {
            LOGGER.info("Number of Drivers: " + drivers.size());
        }

        if (accountants != null && !accountants.isEmpty()) {
            LOGGER.info("Number of Accountants: " + accountants.size());
        }

        if (transportOrders != null && !transportOrders.isEmpty()) {
            LOGGER.info("Number of Transport Orders: " + transportOrders.size());
        }
    }

    @Override
    public void showDetails() {
        display();

        if (vehicles != null && !vehicles.isEmpty()) {
            LOGGER.info("Vehicles:");
            vehicles.forEach(vehicle -> LOGGER.info("- {}", vehicle));
        }

        if (drivers != null && !drivers.isEmpty()) {
            LOGGER.info("Drivers:");
            drivers.forEach(driver -> LOGGER.info("- {}", driver));
        }

        if (accountants != null && !accountants.isEmpty()) {
            LOGGER.info("Accountants:");
            accountants.forEach(accountant -> LOGGER.info("- {}", accountant));
        }

        if (customers != null && !customers.isEmpty()) {
            LOGGER.info("Customers:");
            customers.forEach(customer -> LOGGER.info("- {}", customer));
        }

        if (transportOrders != null && !transportOrders.isEmpty()) {
            LOGGER.info("Transport Orders:");
            transportOrders.forEach(transportOrder -> LOGGER.info("- {}", transportOrder));
        }

        if (driverTransportOrdersMap != null && !driverTransportOrdersMap.isEmpty()) {
            LOGGER.info("Driver Transport Orders:");
            driverTransportOrdersMap.forEach((driver, tOrders) -> {
                LOGGER.info("Driver: {} {}", driver.getFirstName(), driver.getLastName());
                LOGGER.info("Transport Orders:");
                tOrders.forEach(order -> LOGGER.info("- {}", order));
            });
        }
    }


}
