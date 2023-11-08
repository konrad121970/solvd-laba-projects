package com.solvd.laba.hw3;

import com.solvd.laba.hw3.creators.TaxiCompanyCreator;
import com.solvd.laba.hw3.model.TaxiCompany;
import com.solvd.laba.hw3.model.interfaces.Tranportable;
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


    public static void main(String[] args) {

        LOGGER.info("Main application has just been started!");

        Scanner scanner = new Scanner(System.in);
        TaxiCompany taxiCompany = TaxiCompanyCreator.create();

        Customer[] customers = taxiCompany.getCustomers();
        Driver[] drivers = taxiCompany.getDrivers();
        Accountant[] accountants = taxiCompany.getAccountants();

        Vehicle vehicle = new Vehicle("JEEP", "Grand Cherokee", 5, "BI 1987A"); // Parent class
        TaxiVehicle taxiVehicle = new TaxiVehicle("Volkswagen", "Polo", "BZA 12345", 4, 2.5); // Child class

        Employee newDriver = new Driver("Andrzej", "Kowalski", 50, "123123123", taxiVehicle, 4000);
        Employee newAccountant = new Accountant("Robert", "Roberto", "123123123", 40, 3500);

        newDriver.showDetails();        // Using interfaces
        newAccountant.showDetails();

        // Utility class usage

        TaxiCompanyUtility.assignVehicleToCompany(vehicle, taxiCompany); // Passing parent class to utility class
        taxiCompany.printVehicles();
        TaxiCompanyUtility.assignVehicleToCompany(taxiVehicle, taxiCompany); // Passing child class to utility class

        // Main Logic

        Location loc1 = new Location("New York", "Blue St");
        Location loc2 = new Location("New York", "Yellow St");
        Location loc3 = new Location("New York", "Red St");
        Location loc4 = new Location("New York", "Green St");

        TransportOrder tr1 = new TransportOrder(loc1, loc2, customers[0], drivers[0]);
        LOGGER.info(customers[0]);

        drivers[0].move(loc1.getStreetName(), loc2.getStreetName());
        drivers[0].getVehicle().calculatePrice(10.00);
        tr1.getCustomer().pay(drivers[0].getVehicle().calculatePrice(10.00));
        tr1.setPayment(new CashPayment(LocalDate.of(2023, 11, 3), drivers[0].getVehicle().calculatePrice(10.00)));
        tr1.setReview(new Review(5, "It was an amazing ride!\n"));

        LOGGER.info("Customer[0] spent money value: " + customers[0].getSpentMoney() + "\n");


        // Liskov's Substitution Principle
        changePosition(drivers[0], "Start", "End");
        changePosition(customers[0], "Start", "End");

        printPersonData(customers[0]);
        printPersonData(drivers[0]);
        printPersonData(accountants[0]);


        while (true) {
            LOGGER.info("Taxi Company Menu:");
            LOGGER.info("1. Assign Vehicle to Company");
            LOGGER.info("2. Create a Transport Order");
            LOGGER.info("3. Exit");
            LOGGER.info("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    LOGGER.info("Enter vehicle details:");
                    LOGGER.info("Make: ");
                    String make = scanner.next();
                    LOGGER.info("Model: ");
                    String model = scanner.next();
                    LOGGER.info("Plate Number: ");
                    scanner.nextLine();
                    String registrationPlate = scanner.nextLine();
                    LOGGER.info("Number of seats: ");
                    int numberOfSeats = scanner.nextInt();
                    LOGGER.info("Fare per kilometer: ");
                    double farePerKilometer = scanner.nextDouble();

                    TaxiVehicle newTaxi = new TaxiVehicle(make, model, registrationPlate, numberOfSeats, farePerKilometer);
                    taxiCompany.addVehicle(newTaxi);
                    LOGGER.info("New taxi assigned to the company.");

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
        }
    }

    public static void changePosition(Tranportable tranportable, String startLocation, String destination) {
        tranportable.move(startLocation, destination);
    }

    public static void printPersonData(Person person) {
        person.toString();
    }


}