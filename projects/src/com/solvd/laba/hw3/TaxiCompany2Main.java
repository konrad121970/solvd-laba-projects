package com.solvd.laba.hw3;

import com.solvd.laba.hw3.factories.TaxiCompanyFactory;
import com.solvd.laba.hw3.model.TaxiCompany;
import com.solvd.laba.hw3.model.customer.Customer;
import com.solvd.laba.hw3.model.employees.Accountant;
import com.solvd.laba.hw3.model.employees.Driver;
import com.solvd.laba.hw3.model.employees.Employee;
import com.solvd.laba.hw3.model.route.Location;
import com.solvd.laba.hw3.model.route.Payment;
import com.solvd.laba.hw3.model.route.Review;
import com.solvd.laba.hw3.model.route.TransportOrder;
import com.solvd.laba.hw3.model.vehicles.TaxiVehicle;
import com.solvd.laba.hw3.model.vehicles.Vehicle;

import java.time.LocalDate;
import java.util.Scanner;

public class TaxiCompany2Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        TaxiCompany taxiCompany = TaxiCompanyFactory.create();

        Customer[] customers = taxiCompany.getCustomers();
        Driver[] drivers = taxiCompany.getDrivers();
        Accountant[] accountants = taxiCompany.getAccountants();

        Vehicle vehicle = new Vehicle("JEEP", "Grand Cherokee", 5, "BI 1987A"); // Parent class
        TaxiVehicle taxiVehicle = new TaxiVehicle("Volkswagen", "Polo", "BZA 12345", 4, 2.5); // Child class

        Employee newDriver = new Driver("Andrzej", "Kowalski", 50, "123123123", taxiVehicle, 4000);
        Employee newAccountant = new Accountant("Robert", "Roberto", "123123123", 40, 3500);

        newDriver.displayInfo();        // Method polimorphism
        newAccountant.displayInfo();    // Method polimorphism

        // Utility class usage

        TaxiCompanyUtility.assignVehicleToCompany(vehicle, taxiCompany); // Passing parent class to utility class
        taxiCompany.printVehicles();
        TaxiCompanyUtility.assignVehicleToCompany(taxiVehicle, taxiCompany); // Passing child class to utility class

        // Main Logic

        Location loc1 = new Location("New York", "Blue St");
        Location loc2 = new Location("New York", "Yellow St");
        Location loc3 = new Location("New York", "Red St");
        Location loc4 = new Location("New York", "Green St");

        TransportOrder tr1 = new TransportOrder(loc1, loc2, customers[0], drivers[0], LocalDate.of(2023, 11, 3));
        drivers[0].driveFromTo(loc1.getStreetName(), loc2.getStreetName());
        tr1.setDistanceInKm(10.0);
        tr1.calculatePrice();
        tr1.getCustomer().pay(tr1.getPrice());
        tr1.setPayment(new Payment(LocalDate.of(2023, 11, 3), tr1.getPrice()));
        tr1.setReview(new Review(5, "It was an amazing ride!"));

        System.out.println(customers[0].getSpentMoney());

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
                    System.out.println(city);

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
                        System.out.print("Order Date (yyyy-MM-dd): ");
                        String orderDateStr = scanner.next();
                        LocalDate orderDate = LocalDate.parse(orderDateStr);

                        TransportOrder transportOrder = new TransportOrder(pickup, dropOff, customer, selectedDriver, orderDate);
                        selectedDriver.driveFromTo(pickup.getStreetName(), dropOff.getStreetName());

                        System.out.print("Enter the distance in kilometers(X,XX or X.XX format): ");
                        double distanceInKm = scanner.nextDouble();
                        transportOrder.setDistanceInKm(distanceInKm);

                        transportOrder.calculatePrice();
                        System.out.println("Order price: " + transportOrder.getPrice());

                        System.out.print("Payment amount: ");
                        double paymentAmount = scanner.nextDouble();
                        transportOrder.getCustomer().pay(paymentAmount);

                        System.out.print("Review (rating from 1 to 5 stars): ");
                        int rating = scanner.nextInt();
                        System.out.print("Review (comment): ");
                        String comment = scanner.next();
                        transportOrder.setReview(new Review(rating, comment));
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


/*        Trip trip1 = new Trip(driver1, customer1, location1, location2, 10.5, LocalDate.of(2023, 10, 30));
        Trip trip2 = new Trip(driver2, customer2, location3, location4, 20.0, LocalDate.of(2023, 10, 29));
        Trip trip3 = new Trip(driver1, customer3, location1, location4, 30.5, LocalDate.of(2023, 10, 31));
        Trip[] trips = new Trip[]{trip1, trip2, trip3};

        driver1.driveFromTo(location1.getStreetName(), location2.getStreetName());
        driver2.driveFromTo(location3.getStreetName(), location4.getStreetName());
        driver1.driveFromTo(location1.getStreetName(), location4.getStreetName(), "30.10.2023 22:03"); // Method overloading

        Double price1 = trip1.calculatePrice(trip1.getDistanceInKm());
        Double price2 = trip2.calculatePrice(trip2.getDistanceInKm());
        Double price3 = trip3.calculatePrice(trip3.getDistanceInKm());

        Payment payment1 = new Payment(trip1.getDate(), price1);
        Payment payment2 = new Payment(trip2.getDate(), price2);
        Payment payment3 = new Payment(trip3.getDate(), price3);

        trip1.getCustomer().pay(price1);
        trip2.getCustomer().pay(price2);
        trip3.getCustomer().pay(price3, "It was an expensive ride!"); // Method overloading
        System.out.println();

        Review review1 = new Review(5, "It was an amazing ride!");
        Review review2 = new Review(4, "It was good");
        Review review3 = new Review(1, "It was terrible!");

        trip1.setPayment(payment1);
        trip1.setReview(review1);
        trip2.setPayment(payment2);
        trip2.setReview(review2);
        trip3.setPayment(payment3);
        trip3.setReview(review3);

        TaxiCompany taxiCompany = new TaxiCompany(drivers, accountants, cars);
        System.out.println("List of customers: ");
        taxiCompany.printCustomerNames();*/

/*        System.out.println("\nStatic variable of customerCount: " + Customer.getCustomersCount());
        System.out.println("Static variable of driversCount: " + Driver.getDriversCount());
        System.out.println("Static variable of accountantsCount: " + Accountant.getAccountantsCount());*/
    }
}