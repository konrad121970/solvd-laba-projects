import model.TaxiCompany;
import model.customer.Customer;
import model.employees.Accountant;
import model.employees.Driver;
import model.employees.Employees;
import model.route.Location;
import model.route.Payment;
import model.route.Review;
import model.route.Trip;
import model.vehicles.Car;
import model.vehicles.Engine;

import java.math.BigDecimal;
import java.sql.SQLOutput;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        Engine engine = new Engine("Petrol", "2,5L");

        Car car1 = new Car("Audi", "A4", engine, "BHA 18XX");
        Car car2 = new Car("Volkswagen", "Kubelwagen", engine, "BI 1234");
        Car[] cars = new Car[]{car1, car2};

        // Create and initialize drivers array
        Driver driver1 = new Driver("Bartolomeo", "Diaz", 23, "123123123", car1, new BigDecimal(3500));
        Driver driver2 = new Driver("Leon", "Kaputt", 67, "123123123", car2, new BigDecimal(4500));
        Driver[] drivers = new Driver[]{driver1, driver2};

        Accountant accountant1 = new Accountant("Katharine", "Note", 23, "123123123", new BigDecimal(2500));
        Accountant accountant2 = new Accountant("Elias", "Bismark", 19, "123123123", new BigDecimal(4000));
        Accountant[] accountants = new Accountant[]{accountant1, accountant2};

        Employees employees = new Employees(drivers, accountants);

        // Create and initialize customers array
        Customer customer1 = new Customer("Andrzej", "Kowalski", 23, "123123123", 100.50);
        Customer customer2 = new Customer("Paolo", "Nowak", 26, "111222333", 50.25);
        Customer customer3 = new Customer("Herbert", "Shmidt", 40, "333222111", 220.0);
        Customer[] customers = new Customer[]{customer1, customer2, customer3};

        Location location1 = new Location("New York", "Blue St");
        Location location2 = new Location("New York", "Yellow St");
        Location location3 = new Location("New York", "Red St");
        Location location4 = new Location("New York", "Green St");

        Trip trip1 = new Trip(driver1, customer1, location1, location2, 10.5, LocalDate.of(2023, 10,30));
        Trip trip2 = new Trip(driver2, customer2, location3,location4,20.0, LocalDate.of(2023, 10,29));
        Trip trip3 = new Trip(driver1, customer3, location1,location4, 30.5, LocalDate.of(2023, 10,31));
        Trip[] trips = new Trip[]{trip1, trip2, trip3};

        driver1.driveFromTo(location1.getStreetName(), location2.getStreetName());
        driver2.driveFromTo(location3.getStreetName(), location4.getStreetName());
        driver1.driveFromTo(location1.getStreetName(), location4.getStreetName(), "30.10.2023 22:03"); // Method overloading
        System.out.println();

        Double price1 = trip1.calculatePrice(trip1.getDistanceInKm());
        Double price2 = trip2.calculatePrice(trip2.getDistanceInKm());
        Double price3 = trip3.calculatePrice(trip3.getDistanceInKm());

        Payment payment1 = new Payment(trip1.getDate(), price1);
        Payment payment2 = new Payment(trip2.getDate(), price2);
        Payment payment3 = new Payment(trip3.getDate(), price3);

        trip1.getCustomer().spendCash(price1);
        trip2.getCustomer().spendCash(price2);
        trip3.getCustomer().spendCash(price3, "It was an expensive ride!"); // Method overloading
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

        TaxiCompany taxiCompany = new TaxiCompany(customers, employees, trips, cars);
        System.out.println("List of customers: ");
        taxiCompany.printCustomerNames();

        System.out.println("\nStatic variable of customerCount: " + Customer.getCustomersCount());
        System.out.println("Static variable of driversCount: " + Driver.getDriversCount());
        System.out.println("Static variable of accountantsCount: " + Accountant.getAccountantsCount());





    }
}