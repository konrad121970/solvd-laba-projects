package com.solvd.laba.hw3;

import com.solvd.laba.hw3.factories.TaxiCompanyFactory;
import com.solvd.laba.hw3.model.TaxiCompany;
import com.solvd.laba.hw3.model.employees.Accountant;
import com.solvd.laba.hw3.model.employees.Driver;
import com.solvd.laba.hw3.model.employees.Employee;
import com.solvd.laba.hw3.model.vehicles.TaxiVehicle;
import com.solvd.laba.hw3.model.vehicles.Vehicle;

public class TaxiCompany2Main {
    public static void main(String[] args) {

        TaxiCompany taxiCompany = TaxiCompanyFactory.create();


        Vehicle vehicle = new Vehicle("JEEP", "Grand Cherokee", 5, "BI 1987A");
        TaxiVehicle taxiVehicle = new TaxiVehicle("Volkswagen", "Polo", "BZA 12345", 4, 2.5);

        Employee newDriver = new Driver("Andrzej", "Kowalski", 50, "123123123", taxiVehicle, 4000);
        Employee newAccountant = new Accountant("Robert", "Roberto", "123123123", 40, 3500);

        newDriver.displayInfo();
        newAccountant.displayInfo();

        TaxiCompanyUtility.assignVehicleToCompany(vehicle, taxiCompany);
        taxiCompany.printVehicles();
        TaxiCompanyUtility.assignVehicleToCompany(taxiVehicle, taxiCompany);



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