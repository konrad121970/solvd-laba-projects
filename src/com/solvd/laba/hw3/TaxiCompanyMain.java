package com.solvd.laba.hw3;

import com.solvd.laba.hw3.creators.TaxiCompanyCreator;
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
import java.util.Scanner;

public class TaxiCompanyMain {
    private static final Logger LOGGER = LogManager.getLogger(TaxiCompanyMain.class);
    static TaxiCompany taxiCompany = TaxiCompanyCreator.create();
    static Customer[] customers = taxiCompany.getCustomers();
    static Driver[] drivers = taxiCompany.getDrivers();
    static Accountant[] accountants = taxiCompany.getAccountants();

    public static void main(String[] args) {
        LOGGER.info("Main application has just been started!");

        Vehicle vehicle = null; // Parent class
        try {
            vehicle = new Vehicle("JEEP", "Grand Cherokee", 5, "BI 1987A");
        } catch (InvalidNumberOfSeatsException ex) {
            LOGGER.error(ex.getMessage());
        }

        TaxiVehicle taxiVehicle = null; // Child class
        try {
            taxiVehicle = new TaxiVehicle("Volkswagen", "Polo", "BZA 12345", 4, 2.5);
        } catch (InvalidNumberOfSeatsException e) {
            LOGGER.error(e.getMessage());
        }

        try {
            taxiVehicle.scheduleMaintenance(LocalDate.of(2022, 11, 8)); // Setting wrong maintenance date
        } catch (InvalidNextMaintenanceDateException ex) {
            LOGGER.error(ex.getMessage(), ex); // Exception for wrong next maintenance date
        }

        Employee newDriver = null;
        try {
            newDriver = new Driver("Andrzej", "Kowalski", 50, "123123123", taxiVehicle, 4000);
        } catch (InvalidPersonDataException | InvalidEmployeeDataException e) {
            LOGGER.error(e.getMessage());
        }

        Employee newAccountant = null;
        try {
            newAccountant = new Accountant("Robert", "Roberto", "123123123", 40, 3500);
        } catch (InvalidPersonDataException | InvalidEmployeeDataException e) {
            LOGGER.error(e.getMessage());
        }

        newDriver.showDetails();        // Using interfaces
        newAccountant.showDetails();

        // Utility class usage

        TaxiCompanyUtility.assignVehicleToCompany(vehicle, taxiCompany); // Passing parent class to utility class
        taxiCompany.printVehicles();
        TaxiCompanyUtility.assignVehicleToCompany(taxiVehicle, taxiCompany); // Passing child class to utility class

        // Main Logic

        Location loc1 = new Location("New York", "Blue St");
        Location loc2 = new Location("New York", "Yellow St");

        TransportOrder tr1 = new TransportOrder(loc1, loc2, customers[0], drivers[0]);
        LOGGER.info(customers[0]);

        drivers[0].move(loc1.getStreetName(), loc2.getStreetName());
        drivers[0].getVehicle().calculatePrice(10.00);
        tr1.getCustomer().pay(drivers[0].getVehicle().calculatePrice(10.00));
        tr1.setPayment(new CashPayment(LocalDate.of(2023, 11, 3), drivers[0].getVehicle().calculatePrice(10.00)));
        tr1.setReview(new Review(5, "It was an amazing ride!\n"));

        LOGGER.info("Customer[0] spent money value: " + customers[0].getSpentMoney() + "\n");


        // Liskov's Substitution Principle
        changePosition(drivers[0], "Start", "End"); // Using interface as parameter
        changePosition(customers[0], "Start", "End");

        printPersonData(customers[0]); // Using abstract class as parameter
        printPersonData(drivers[0]);
        printPersonData(accountants[0]);


        while (true) {
            LOGGER.info("Taxi Company Menu:");
            LOGGER.info("1. Assign Vehicle to Company");
            LOGGER.info("2. Create a Transport Order");
            LOGGER.info("3. Exit");
            LOGGER.info("Enter your choice: ");

            // Try with resources
            try (Scanner scanner = new Scanner(System.in)) {
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        LOGGER.info("Enter vehicle details:");
                        LOGGER.info("Make: ");
                        String make = scanner.next();
                        LOGGER.info("Model: ");
                        String model = scanner.next();
                        LOGGER.info("Number of seats: ");
                        int numberOfSeats = scanner.nextInt();
                        LOGGER.info("Plate Number: ");
                        scanner.nextLine();
                        String registrationPlate = scanner.nextLine();

                        try {
                            TaxiVehicle newTaxi = new TaxiVehicle(make, model, registrationPlate, numberOfSeats);
                            taxiCompany.addVehicle(newTaxi);
                            LOGGER.info("Fare per kilometer(you should use \",\" as a separator): ");
                            double farePerKilometer = scanner.nextDouble();
                            newTaxi.setFarePerKilometer(farePerKilometer);
                            LOGGER.info("New taxi assigned to the company.");
                        } catch (InvalidNumberOfSeatsException | DuplicateRegistrationPlateException ex) {
                            LOGGER.error(ex.getMessage());
                            LOGGER.error("Exiting menu option");
                        }
                        taxiCompany.printVehicles();
                        break;

                    case 2:
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
                        Customer customer = new Customer(customerName, customerLastName, customerPhoneNumber);
                        taxiCompany.addCustomer(customer);

                        LOGGER.info("Select a driver from the list:");
                        for (int i = 0; i < taxiCompany.getDrivers().length; i++) {
                            LOGGER.info(i + ". " + taxiCompany.getDrivers()[i].getFirstName());
                        }
                        int driverChoice = scanner.nextInt();

                        if (driverChoice >= 0 && driverChoice < taxiCompany.getDrivers().length) {
                            Driver selectedDriver = taxiCompany.getDrivers()[driverChoice];
                            LOGGER.info("Ride Date (yyyy-MM-dd): ");
                            String orderDateStr = scanner.next();
                            LocalDate orderDate = LocalDate.parse(orderDateStr);

                            LOGGER.info("Enter the distance in kilometers(X,XX or X.XX format): ");
                            Double distance = scanner.nextDouble();

                            TransportOrder transportOrder = new TransportOrder(pickup, dropOff, customer, selectedDriver);
                            selectedDriver.driveFromTo(pickup.getStreetName(), dropOff.getStreetName());

                            selectedDriver.getVehicle().calculatePrice(distance);
                            LOGGER.info("Order price: " + selectedDriver.getVehicle().getFareCost());

                            LOGGER.info("Payment amount: ");
                            double paymentAmount = scanner.nextDouble();
                            transportOrder.getCustomer().pay(paymentAmount);
                            transportOrder.setPayment(new CashPayment(orderDate, paymentAmount));

                            LOGGER.info("Review (rating from 1 to 5 stars): ");
                            int rating = scanner.nextInt();
                            LOGGER.info("Review (comment): ");
                            String content = scanner.next();
                            transportOrder.setReview(new Review(rating, content));
                            scanner.nextLine();
                            break;
                        } else {
                            LOGGER.info("Invalid driver choice.");
                        }

                    case 3:
                        LOGGER.info("Exiting...");
                        scanner.close();
                        System.exit(0);
                        break;

                    default:
                        LOGGER.info("Invalid choice. Please enter a valid option.");
                        break;
                }
            } catch (Exception ex) {
                LOGGER.error(ex.getMessage());
            }
        }
    }

    public static void changePosition(Transportable transportable, String startLocation, String destination) {
        transportable.move(startLocation, destination);
    }

    public static void printPersonData(Person person) {
        person.toString();
    }


}