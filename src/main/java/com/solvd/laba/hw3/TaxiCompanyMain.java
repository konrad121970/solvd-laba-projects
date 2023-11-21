package com.solvd.laba.hw3;

import com.solvd.laba.hw3.builders.TaxiCompanyBuilder;
import com.solvd.laba.hw3.model.TaxiCompany;
import com.solvd.laba.hw3.model.exceptions.*;
import com.solvd.laba.hw3.model.interfaces.Transportable;
import com.solvd.laba.hw3.model.payment.CashPayment;
import com.solvd.laba.hw3.model.people.Employee;
import com.solvd.laba.hw3.model.people.Person;
import com.solvd.laba.hw3.model.people.customer.Customer;
import com.solvd.laba.hw3.model.people.employees.Accountant;
import com.solvd.laba.hw3.model.people.employees.Driver;
import com.solvd.laba.hw3.model.route.Location;
import com.solvd.laba.hw3.model.route.Review;
import com.solvd.laba.hw3.model.route.TransportOrder;
import com.solvd.laba.hw3.model.vehicles.TaxiVehicle;
import com.solvd.laba.hw3.model.vehicles.Vehicle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;

public class TaxiCompanyMain {
    private static final Logger LOGGER = LogManager.getLogger(TaxiCompanyMain.class);

    public static void main(String[] args) {
        LOGGER.info("Main application has just been started!");

        ArrayList<TaxiVehicle> taxiVehiclesList;
        try {
            taxiVehiclesList = new ArrayList<>(Arrays.asList(
                    new TaxiVehicle("Audi", "A4", "BHA 18XX", 4, 2.50),
                    new TaxiVehicle("Volkswagen", "Kubelwagen", "BI 1234", 5, 3.00)));
        } catch (InvalidNumberOfSeatsException e) {
            throw new RuntimeException(e);
        }

        ArrayList<Driver> driversList = null;
        try {
            driversList = new ArrayList<>(Arrays.asList(
                    new Driver("Bartolomeo", "Diaz", 23, "123123123", taxiVehiclesList.get(0), 3500),
                    new Driver("Leon", "Kaputt", 67, "123123123", taxiVehiclesList.get(1), 4000)));
        } catch (InvalidPersonDataException | InvalidEmployeeDataException e) {
            LOGGER.error(e.getMessage());
        }

        HashSet<Accountant> accountants = null;
        try {
            accountants = new HashSet<>(Arrays.asList(
                    new Accountant("Katharine", "Note", "123123123", 23, 2500),
                    new Accountant("Elias", "Bismark", "123123123", 19, 4000)));
        } catch (InvalidPersonDataException | InvalidEmployeeDataException e) {
            LOGGER.error(e.getMessage());
        }

        List<Customer> customers = null;
        try {
            customers = new ArrayList<>(Arrays.asList(
                    new Customer("Andrzej", "Kowalski", "123123123"),
                    new Customer("Paolo", "Nowak", "111222333"),
                    new Customer("Herbert", "Shmidt", "333222111")));
        } catch (InvalidPersonDataException e) {
            LOGGER.error(e.getMessage());
        }

        /* USING BUILDER PATTERN */

        TaxiCompany taxiCompany = null;
        try {
            taxiCompany = new TaxiCompanyBuilder()
                    .setName("Konrad Taxi")
                    .setVehicles(taxiVehiclesList)
                    .setDrivers(driversList)
                    .setAccountants(accountants)
                    .setCustomers(customers)
                    .build();
        } catch (DuplicateRegistrationPlateException e) {
            throw new RuntimeException();
        }


        TaxiVehicle taxiVehicle1 = null;
        TaxiVehicle taxiVehicle2 = null;
        try {
            taxiVehicle1 = new TaxiVehicle("Volkswagen", "Polo", "ALALA", 4, 2.5);
            taxiVehicle2 = new TaxiVehicle("Audi", "A7", "444", 4, 2.5);
        } catch (InvalidNumberOfSeatsException e) {
            LOGGER.error(e.getMessage());
        }

        try {
            taxiCompany.addVehicles(Arrays.asList(taxiVehicle1, taxiVehicle2));
        } catch (DuplicateRegistrationPlateException e) {
            throw new RuntimeException(e);
        }

        try {
            taxiVehicle1.scheduleMaintenance(LocalDate.of(2024, 11, 8)); // Setting wrong maintenance date
        } catch (InvalidNextMaintenanceDateException ex) {
            LOGGER.error(ex.getMessage(), ex); // Exception for wrong next maintenance date
        }

        Driver newDriver1 = null;
        Driver newDriver2 = null;
        try {
            newDriver1 = new Driver("Andrzej", "Kowalski", 50, "123123123", taxiVehicle1, 4000);
            newDriver2 = new Driver("Pawel", "Andrzejuk", 22, "123123123", taxiVehicle2, 4000);
        } catch (InvalidPersonDataException | InvalidEmployeeDataException e) {
            LOGGER.error(e.getMessage());
        }

        taxiCompany.addDrivers(Arrays.asList(newDriver1, newDriver2));

        LOGGER.info("Size of driverVehicleMap: " + taxiCompany.getDriverVehicleMap().size());
        LOGGER.info("****************** ITERATE MAP - ITERATOR ******************");
        // Iterate Map using iterator
        Iterator<Map.Entry<Driver, Vehicle>> iterator = taxiCompany.getDriverVehicleMap().entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Driver, Vehicle> entry = iterator.next();
            LOGGER.info("Key = " + entry.getKey() + " Value = " + entry.getValue());
        }

        LOGGER.info("****************** ITERATE MAP - FOREACH ******************");
        for (Map.Entry<Driver, Vehicle> entry : taxiCompany.getDriverVehicleMap().entrySet()) {
            Driver key = entry.getKey();
            Vehicle value = entry.getValue();
            LOGGER.info("Key: " + key + ", Value: " + value);
        }

        Accountant newAccountant1 = null;
        Accountant newAccountant2 = null;
        try {
            newAccountant1 = new Accountant("Robert", "Roberto", "123123123", 40, 3500);
            newAccountant2 = new Accountant("Hubert", "Kowalski", "123123123", 20, 3000);
        } catch (InvalidPersonDataException | InvalidEmployeeDataException e) {
            LOGGER.error(e.getMessage());
        }

        taxiCompany.addAccountants(new HashSet<>(Arrays.asList(newAccountant1, newAccountant2)));
        // VehicleUtils.performMaintenance(taxiVehicle1);

        LOGGER.info("Size of accountants Set: " + accountants.size());
        LOGGER.info("****************** ITERATE SET - ITERATOR ******************");
        // Iterate Set using iterator allows for dynamic removing of elements during iteration
        Iterator<Accountant> iterator1 = accountants.iterator(); //
        while (iterator1.hasNext()) {
            Employee accountant = iterator1.next();
            LOGGER.info(accountant);
        }
        LOGGER.info("****************** ITERATE SET - FOREACH ******************");
        // Iterate Set using foreach
        for (Employee employee : accountants) {
            LOGGER.info(employee);
        }

        Location loc1 = new Location("New York", "Blue St");
        Location loc2 = new Location("New York", "Yellow St");

        TransportOrder tr1 = new TransportOrder(loc1, loc2, customers.get(0), driversList.get(0));
        LOGGER.info(customers.get(0));

        driversList.get(0).move(loc1.getStreetName(), loc2.getStreetName());
        tr1.getCustomer().pay(driversList.get(0).getVehicle().calculatePrice(10.00));
        tr1.setPayment(new CashPayment(LocalDate.of(2023, 11, 3), driversList.get(0).getVehicle().calculatePrice(10.00)));
        tr1.setReview(new Review(5, "It was an amazing ride!\n"));

        LOGGER.info("Customer: " + customers.get(0).getFirstName() + " spent money value: " + customers.get(0).getSpentMoney() + "\n");

        changePosition(driversList.get(0), "Start", "End"); // Using interface as parameter
        changePosition(customers.get(0), "Start", "End");
        taxiCompany.addTransportOrder(tr1);

        printPersonData(customers.get(0)); // Using abstract class as parameter
        printPersonData(driversList.get(0));

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                LOGGER.info("Taxi Company Menu:");
                LOGGER.info("1. Add new Vehicle");
                LOGGER.info("2. Add new Employee");
                LOGGER.info("3. Create a new Transport Order");
                LOGGER.info("4. Exit");
                LOGGER.info("Enter your choice: ");

                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        addNewVehicle(scanner, taxiCompany);
                        break;

                    case 2:
                        addNewEmployee(scanner, taxiCompany);
                        break;

                    case 3:
                        createTransportOrder(scanner, taxiCompany);
                        break;

                    case 4:
                        LOGGER.info("Exiting...");
                        return;

                    default:
                        LOGGER.info("Invalid choice. Please enter a valid option.");
                        break;
                }
            }
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
    }

    public static void changePosition(Transportable transportable, String startLocation, String destination) {
        transportable.move(startLocation, destination);
    }

    public static void printPersonData(Person person) {
        person.toString();
    }

    private static Double readDoubleData(Scanner scanner) {
        double number = 0.0;
        boolean validInput = false;

        while (!validInput) {
            String input = scanner.next();

            try {
                // Try parsing with dot as the decimal separator
                number = Double.parseDouble(input);
                validInput = true;
            } catch (NumberFormatException e1) {
                try {
                    // Try parsing with comma as the decimal separator
                    number = Double.parseDouble(input.replace(",", "."));
                    validInput = true;
                } catch (NumberFormatException e2) {
                    LOGGER.warn("Invalid input. Please enter a valid data in X.XX or X,XX format.");
                }
            }
        }

        return number;
    }

    private static LocalDate readRideDate(Scanner scanner) {
        LocalDate orderDate = null;
        boolean validInput = false;

        while (!validInput) {
            try {
                String orderDateStr = scanner.next();
                orderDate = LocalDate.parse(orderDateStr);
                validInput = true;
            } catch (DateTimeParseException e) {
                LOGGER.warn("Invalid date format. Please enter a valid date in the yyyy-MM-dd format.");
            }
        }

        return orderDate;
    }

    private static int readAge(Scanner scanner) {
        int age = 0;
        boolean validInput = false;

        while (!validInput) {
            try {
                age = scanner.nextInt();
                if (age < 18) throw new InvalidPersonDataException("Age must be greater than 17!");
                validInput = true;
            } catch (InputMismatchException e) {
                LOGGER.error("Invalid input. Please enter a valid age as a number higher than 17.");
                scanner.next(); // Read invalid input to avoid an infinite loop
            } catch (InvalidPersonDataException e) {
                LOGGER.error(e.getMessage());
            }
        }

        return age;
    }

    private static int readSalary(Scanner scanner) {
        int salary = 0;
        boolean validInput = false;

        while (!validInput) {
            try {
                salary = scanner.nextInt();

                if (salary > 0) {
                    validInput = true;
                } else {
                    LOGGER.warn("Invalid salary. Please enter a positive integer value.");
                }
            } catch (InputMismatchException e) {
                LOGGER.warn("Invalid input. Please enter a valid integer value.");
                scanner.next(); // Read invalid input to avoid an infinite loop
            }
        }

        return salary;
    }

    private static int readStarRating(Scanner scanner) {
        int starRating = 0;
        boolean validInput = false;

        while (!validInput) {
            try {
                starRating = scanner.nextInt();

                if (starRating >= 1 && starRating <= 5) {
                    validInput = true;
                } else {
                    LOGGER.warn("Invalid star rating. Please enter a rating between 1 and 5.");
                }
            } catch (InputMismatchException e) {
                LOGGER.warn("Invalid input. Please enter a valid number for star rating.");
                scanner.next(); // Read invalid input to avoid an infinite loop
            }
        }

        return starRating;
    }

    private static int readNumberOfSeats(Scanner scanner) {
        int numberOfSeats = 0;
        boolean validInput = false;

        while (!validInput) {
            try {
                numberOfSeats = scanner.nextInt();

                if (numberOfSeats > 0 && numberOfSeats <= 300) {
                    validInput = true;
                } else {
                    LOGGER.warn("Invalid number of seats. Please enter a value between 1 and 300.");
                }
            } catch (InputMismatchException e) {
                LOGGER.warn("Invalid input. Please enter a valid number for the number of seats.");
                scanner.next(); // Read invalid input to avoid an infinite loop
            }
        }

        return numberOfSeats;
    }

    public static void addNewVehicle(Scanner scanner, TaxiCompany taxiCompany) {
        LOGGER.info("Enter vehicle details:");
        LOGGER.info("Make: ");
        String make = scanner.next();
        LOGGER.info("Model: ");
        String model = scanner.next();
        LOGGER.info("Number of seats: ");
        int numberOfSeats = readNumberOfSeats(scanner);
        LOGGER.info("Plate Number: ");
        scanner.nextLine();
        String registrationPlate = scanner.nextLine();

        try {
            TaxiVehicle newTaxi = new TaxiVehicle(make, model, registrationPlate, numberOfSeats);
            taxiCompany.addVehicle(newTaxi);
            LOGGER.info("Fare per kilometer(you should use \",\" as a separator): ");
            double farePerKilometer = readDoubleData(scanner);  //scanner.nextDouble();
            newTaxi.setFarePerKilometer(farePerKilometer);
            LOGGER.info("New taxi assigned to the company.");
            taxiCompany.printVehicles();
        } catch (InvalidNumberOfSeatsException | DuplicateRegistrationPlateException ex) {
            LOGGER.error(ex.getMessage());
            LOGGER.error("Exiting menu option");
        }
    }

    public static void addNewEmployee(Scanner scanner, TaxiCompany taxiCompany) {
        LOGGER.info("Enter employee details:");
        LOGGER.info("First Name: ");
        String firstName = scanner.next();
        LOGGER.info("Last Name: ");
        String lastName = scanner.next();
        LOGGER.info("Age: ");
        int age = readAge(scanner);
        LOGGER.info("Phone Number: ");
        String phoneNumber = scanner.next();

        try {
            LOGGER.info("Select employee type:");
            LOGGER.info("1. Driver");
            LOGGER.info("2. Accountant");
            int employeeTypeChoice = scanner.nextInt();

            if (employeeTypeChoice == 1) {
                LOGGER.info("Select a vehicle for the driver:");
                taxiCompany.printVehicles();
                int vehicleChoice = scanner.nextInt();

                if (vehicleChoice >= 0 && vehicleChoice < taxiCompany.getVehicles().size()) {
                    TaxiVehicle selectedVehicle = taxiCompany.getVehicles().get(vehicleChoice - 1);

                    LOGGER.info("Enter driver salary: ");
                    Integer salary = readSalary(scanner);

                    Driver newDriver = new Driver(firstName, lastName, age, phoneNumber, selectedVehicle, salary);
                    taxiCompany.addDriver(newDriver);
                    LOGGER.info("New driver assigned to the company.");
                } else {
                    LOGGER.info("Invalid vehicle choice.");
                }
            } else if (employeeTypeChoice == 2) {
                LOGGER.info("Enter accountant salary: ");
                Integer salary = readSalary(scanner);

                Accountant newAccountant = new Accountant(firstName, lastName, phoneNumber, age, salary);
                taxiCompany.addAccountant(newAccountant);
                LOGGER.info("New accountant assigned to the company.");
            } else {
                LOGGER.info("Invalid employee type choice.");
            }
        } catch (InvalidPersonDataException | InvalidEmployeeDataException ex) {
            LOGGER.error(ex.getMessage());
            LOGGER.error("Exiting menu option");
        }
    }

    public static void createTransportOrder(Scanner scanner, TaxiCompany taxiCompany) {
        LOGGER.info("Create a Transport Order:");
        LOGGER.info("City: ");
        scanner.nextLine();
        String city = scanner.nextLine();

        LOGGER.info("Pickup Location: ");
        String pickupLocation = scanner.nextLine();
        LOGGER.info("Drop-off Location: ");
        String dropOffLocation = scanner.nextLine();

        Location pickup = new Location(city, pickupLocation);
        Location dropOff = new Location(city, dropOffLocation);

        LOGGER.info("Customer Name: ");
        String customerName = scanner.nextLine();
        LOGGER.info("Customer Last Name: ");
        String customerLastName = scanner.nextLine();
        LOGGER.info("Customer Phone Number: ");
        String customerPhoneNumber = scanner.next();
        Customer customer;
        try {
            customer = new Customer(customerName, customerLastName, customerPhoneNumber);
        } catch (InvalidPersonDataException e) {
            throw new RuntimeException(e);
        }
        taxiCompany.addCustomer(customer);

        LOGGER.info("Select a driver from the list:");
        for (int i = 0; i < taxiCompany.getDrivers().size(); i++) {
            LOGGER.info(i + ". " + taxiCompany.getDrivers().get(i).getFirstName());
        }
        int driverChoice = scanner.nextInt();

        if (driverChoice >= 0 && driverChoice < taxiCompany.getDrivers().size()) {
            Driver selectedDriver = taxiCompany.getDrivers().get(driverChoice);
            LOGGER.info("Ride Date (yyyy-MM-dd): ");
            LocalDate orderDate = readRideDate(scanner);
            //LocalDate orderDate = LocalDate.parse(orderDateStr);

            LOGGER.info("Enter the distance in kilometers(X,XX or X.XX format): ");
            Double distance = readDoubleData(scanner); //scanner.nextDouble();

            TransportOrder transportOrder = new TransportOrder(pickup, dropOff, customer, selectedDriver);
            selectedDriver.driveFromTo(pickup.getStreetName(), dropOff.getStreetName());

            selectedDriver.getVehicle().calculatePrice(distance);
            LOGGER.info("Order price: " + selectedDriver.getVehicle().getFareCost());

            LOGGER.info("Payment amount: ");
            double paymentAmount = readDoubleData(scanner); //scanner.nextDouble();
            transportOrder.getCustomer().pay(paymentAmount);
            transportOrder.setPayment(new CashPayment(orderDate, paymentAmount));

            LOGGER.info("Review (rating from 1 to 5 stars): ");
            int rating = readStarRating(scanner);
            LOGGER.info("Review (comment): ");
            String content = scanner.next();
            transportOrder.setReview(new Review(rating, content));
            scanner.nextLine();
            LOGGER.info("New Transport Order added!.");
        } else {
            LOGGER.info("Invalid driver choice.");
        }

    }
}
