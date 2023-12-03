package com.solvd.laba.hw3.model;

import com.solvd.laba.hw3.common.exceptions.DuplicateRegistrationPlateException;
import com.solvd.laba.hw3.common.exceptions.InvalidPersonDataException;
import com.solvd.laba.hw3.common.interfaces.Displayable;
import com.solvd.laba.hw3.model.people.customer.Customer;
import com.solvd.laba.hw3.model.people.employees.Accountant;
import com.solvd.laba.hw3.model.people.employees.Driver;
import com.solvd.laba.hw3.model.route.TransportOrder;
import com.solvd.laba.hw3.model.vehicles.Taxi;
import com.solvd.laba.hw3.model.vehicles.Vehicle;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

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

    public TaxiCompany(String name, List<TransportOrder> transportOrders, List<Customer> customers, List<Driver> drivers, Set<Accountant> accountants, List<Taxi> vehicles) throws DuplicateRegistrationPlateException {
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
            LOGGER.error("Error loading state: " + e.getMessage());
            return null;
        }
    }

    public static List<Customer> loadCustomersFromFile() {
        List<Customer> customers = new ArrayList<>();

        try {
            File file = new File("src/main/resources/customers_list");
            if (file.exists()) {
                List<String> lines = FileUtils.readLines(file, StandardCharsets.UTF_8);

                final Customer[] customer = {null};

                lines.forEach(line -> {
                    if (line.startsWith("Name: ")) {
                        String[] nameParts = line.replace("Name: ", "").split(" ");
                        try {
                            customer[0] = new Customer(nameParts[0], nameParts[1], "");
                        } catch (InvalidPersonDataException e) {
                            throw new RuntimeException(e);
                        }

                    } else if (line.startsWith("Phone Number: ")) {

                        if (customer[0] != null) {
                            String number = line.replace("Phone Number: ", "");
                            customer[0].setPhoneNumber(number);
                        }
                    } else if (line.startsWith("Spent Money: ")) {

                        if (customer[0] != null) {
                            customer[0].setSpentMoney(Double.parseDouble(line.replace("Spent Money: ", "")));
                        }
                    }
                });

                if (customer[0] != null) {
                    customers.add(customer[0]);
                }
            }
        } catch (IOException e) {
            LOGGER.error("Error loading customer information from file: " + e.getMessage());
        } catch (NumberFormatException e) {
            LOGGER.error("Error creating customer object: " + e.getMessage());
        }
        return customers;
    }

    public void saveState(String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(this);
            LOGGER.info("State saved successfully.");
        } catch (IOException e) {
            LOGGER.error("Error saving state: " + e.getMessage());
        }
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
            //deleteEntry(vehicle);
        } else {
            LOGGER.warn("Vehicle cannot be null");
        }
    }

    public void deleteVehicles(List<Vehicle> vehicles) {
        if (vehicles != null) {
            vehicles.removeAll(vehicles);
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
        AtomicInteger counter = new AtomicInteger(1);
        customers.forEach(customer -> {
            LOGGER.info(customer.getFirstName() + " " + customer.getLastName() + " " + customer.getSpentMoney());
        });
    }

    public void printDrivers() {
        LOGGER.info("List of company Drivers:");
        AtomicInteger counter = new AtomicInteger(1);
        drivers.forEach(driver -> {
            LOGGER.info(driver.getFirstName() + " " + driver.getLastName());
        });
    }

    // FUNCTION
    public void printDrivers(Function<Driver, String> fullNameMapper) {

        LOGGER.info("List of Driver Full Names:");
        drivers.forEach(driver -> {
            LOGGER.info(fullNameMapper.apply(driver));
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

    public void writeCustomersToFile() {
        try {
            StringBuilder data = new StringBuilder();

            data.append("Consumer Information\n");

            customers.forEach(customer -> {
                data.append("Name: ").append(customer.getFirstName()).append(" ").append(customer.getLastName()).append("\n");
                data.append("Phone Number: ").append(customer.getPhoneNumber()).append("\n");
                data.append("Spent Money: ").append(customer.getSpentMoney()).append("\n");
                data.append("---------------\n");
            });

            FileUtils.writeStringToFile(new File("src/main/resources/customers_list"), data.toString(), StandardCharsets.UTF_8, true);
        } catch (IOException e) {
            LOGGER.error("Error saving customer " + this.toString() + " information to file: " + e.getMessage());
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
