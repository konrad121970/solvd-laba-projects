package com.solvd.laba.hw3;

import com.solvd.laba.hw3.creators.TaxiCompanyCreator;
import com.solvd.laba.hw3.model.TaxiCompany;
import com.solvd.laba.hw3.model.interfaces.Tranportable;
import com.solvd.laba.hw3.model.people.Employee;
import com.solvd.laba.hw3.model.people.customer.Customer;
import com.solvd.laba.hw3.model.people.employees.Accountant;
import com.solvd.laba.hw3.model.people.employees.Driver;
import com.solvd.laba.hw3.model.route.Location;
import com.solvd.laba.hw3.model.route.Payment;
import com.solvd.laba.hw3.model.route.Review;
import com.solvd.laba.hw3.model.route.TransportOrder;
import com.solvd.laba.hw3.model.vehicles.TaxiVehicle;
import com.solvd.laba.hw3.model.vehicles.Vehicle;

import java.time.LocalDate;
import java.util.Scanner;

public class TaxiCompanyMain {
    public static void main(String[] args) {

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
        System.out.println();
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
        drivers[0].move(loc1.getStreetName(), loc2.getStreetName());
        drivers[0].getVehicle().calculatePrice(10.00);
        tr1.getCustomer().pay(drivers[0].getVehicle().calculatePrice(10.00));
        tr1.setPayment(new Payment(LocalDate.of(2023, 11, 3), drivers[0].getVehicle().calculatePrice(10.00)));
        tr1.setReview(new Review(5, "It was an amazing ride!\n"));

        System.out.println("\nCustomer[0] spent money value: " + customers[0].getSpentMoney() + "\n");

        System.out.println("*********************************\n");

        // Liskov's Substitution Principle
        changePosition(drivers[0], "Start", "End");
        changePosition(customers[0], "Start", "Emd");

        while (true) {
            System.out.println("Taxi Company Menu:");
            System.out.println("1. Assign Vehicle to Company");
            System.out.println("2. Create a Transport Order");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Enter vehicle details:");
                    System.out.print("Make: ");
                    String make = scanner.next();
                    System.out.print("Model: ");
                    String model = scanner.next();
                    System.out.print("Plate Number: ");
                    scanner.nextLine();
                    String registrationPlate = scanner.nextLine();
                    System.out.print("Number of seats: ");
                    int numberOfSeats = scanner.nextInt();
                    System.out.print("Fare per kilometer: ");
                    double farePerKilometer = scanner.nextDouble();

                    TaxiVehicle newTaxi = new TaxiVehicle(make, model, registrationPlate, numberOfSeats, farePerKilometer);
                    taxiCompany.addVehicle(newTaxi);
                    System.out.println("New taxi assigned to the company.");

                    System.out.println();
                    taxiCompany.printVehicles();
                    System.out.println();

                    break;

                case 2:
                    System.out.println("Create a Transport Order:");
                    System.out.print("City: ");
                    scanner.nextLine();
                    String city = scanner.nextLine();

                    System.out.print("Pickup Location: ");
                    String pickupLocation = scanner.nextLine();
                    System.out.print("Drop-off Location: ");
                    String dropOffLocation = scanner.nextLine();

                    Location pickup = new Location(city, pickupLocation);
                    Location dropOff = new Location(city, dropOffLocation);

                    System.out.print("Customer Name: ");
                    String customerName = scanner.nextLine();
                    System.out.print("Customer Last Name: ");
                    String customerLastName = scanner.nextLine();
                    System.out.print("Customer Phone Number: ");
                    String customerPhoneNumber = scanner.next();
                    Customer customer = new Customer(customerName, customerLastName, customerPhoneNumber);
                    taxiCompany.addCustomer(customer);

                    System.out.println("Select a driver from the list:");
                    for (int i = 0; i < taxiCompany.getDrivers().length; i++) {
                        System.out.println(i + ". " + taxiCompany.getDrivers()[i].getFirstName());
                    }
                    int driverChoice = scanner.nextInt();

                    if (driverChoice >= 0 && driverChoice < taxiCompany.getDrivers().length) {
                        Driver selectedDriver = taxiCompany.getDrivers()[driverChoice];
                        System.out.print("Ride Date (yyyy-MM-dd): ");
                        String orderDateStr = scanner.next();
                        LocalDate orderDate = LocalDate.parse(orderDateStr);

                        System.out.print("Enter the distance in kilometers(X,XX or X.XX format): ");
                        Double distance = scanner.nextDouble();

                        TransportOrder transportOrder = new TransportOrder(pickup, dropOff, customer, selectedDriver);
                        selectedDriver.driveFromTo(pickup.getStreetName(), dropOff.getStreetName());

                        selectedDriver.getVehicle().calculatePrice(distance);
                        System.out.println("Order price: " + selectedDriver.getVehicle().getFareCost());

                        System.out.print("Payment amount: ");
                        double paymentAmount = scanner.nextDouble();
                        transportOrder.getCustomer().pay(paymentAmount);
                        transportOrder.setPayment(new Payment(orderDate, paymentAmount));

                        System.out.print("Review (rating from 1 to 5 stars): ");
                        int rating = scanner.nextInt();
                        System.out.print("Review (comment): ");
                        String content = scanner.next();
                        transportOrder.setReview(new Review(rating, content));
                        scanner.nextLine();
                        break;
                    } else {
                        System.out.println("Invalid driver choice.");
                    }

                case 3:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
                    break;
            }
        }
    }

    public static void changePosition(Tranportable tranportable, String startLocation, String destination) {
        tranportable.move(startLocation, destination);
    }


}