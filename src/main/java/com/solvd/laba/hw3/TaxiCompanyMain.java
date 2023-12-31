package com.solvd.laba.hw3;

import com.solvd.laba.hw3.common.builders.TaxiCompanyBuilder;
import com.solvd.laba.hw3.common.enums.CurrencyType;
import com.solvd.laba.hw3.common.enums.RatingType;
import com.solvd.laba.hw3.common.enums.TaxiStandardType;
import com.solvd.laba.hw3.common.exceptions.InvalidEmployeeDataException;
import com.solvd.laba.hw3.common.exceptions.InvalidNextMaintenanceDateException;
import com.solvd.laba.hw3.common.exceptions.InvalidNumberOfSeatsException;
import com.solvd.laba.hw3.common.exceptions.InvalidPersonDataException;
import com.solvd.laba.hw3.common.interfaces.Transportable;
import com.solvd.laba.hw3.menu.company.AccountantMenu;
import com.solvd.laba.hw3.menu.company.TaxiCompanyMenu;
import com.solvd.laba.hw3.menu.company.TransportOrderMenu;
import com.solvd.laba.hw3.model.TaxiCompany;
import com.solvd.laba.hw3.model.payment.CashPayment;
import com.solvd.laba.hw3.model.people.customer.Customer;
import com.solvd.laba.hw3.model.people.employees.Accountant;
import com.solvd.laba.hw3.model.people.employees.Driver;
import com.solvd.laba.hw3.model.route.Location;
import com.solvd.laba.hw3.model.route.Review;
import com.solvd.laba.hw3.model.route.TransportOrder;
import com.solvd.laba.hw3.model.vehicles.Taxi;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class TaxiCompanyMain {
    private static final Logger LOGGER = LogManager.getLogger(TaxiCompanyMain.class);

    public static void main(String[] args) {
        LOGGER.info("Main application has just been started!");

        List<Taxi> taxiVehiclesList = null;
        List<Driver> driversList = null;
        Set<Accountant> accountants = null;
        List<Customer> customers = null;

        Taxi taxi1 = null;
        Taxi taxi2 = null;

        Driver newDriver1 = null;
        Driver newDriver2 = null;

        Accountant newAccountant1 = null;
        Accountant newAccountant2 = null;

        try {
            taxiVehiclesList = new ArrayList<>(Arrays.asList(
                    new Taxi("Audi", "A4", "BHA 18XX", 4, 2.50, TaxiStandardType.LUXURY),
                    new Taxi("Volkswagen", "Kubelwagen", "BI 1234", 5, 3.00, TaxiStandardType.STANDARD)));

            driversList = new ArrayList<>(Arrays.asList(
                    new Driver("Bartolomeo", "Diaz", 23, "123123123", taxiVehiclesList.get(0), 3500),
                    new Driver("Leon", "Kaputt", 67, "123123123", taxiVehiclesList.get(1), 4000)));

            accountants = new HashSet<>(Arrays.asList(
                    new Accountant("Katharine", "Note", "123123123", 23, 2500),
                    new Accountant("Elias", "Bismark", "123123123", 19, 4000)));

            customers = new ArrayList<>(Arrays.asList(
                    new Customer("Andrzej", "Kowalski", "123123123"),
                    new Customer("Paolo", "Nowak", "111222333"),
                    new Customer("Herbert", "Shmidt", "333222111")));

            taxi1 = new Taxi("Volkswagen", "Polo", "ALALA", 4, 2.5, TaxiStandardType.ECO_FRIENDLY);
            taxi2 = new Taxi("Audi", "A7", "444", 4, 2.5, TaxiStandardType.LUXURY);

            newDriver1 = new Driver("Andrzej", "Kowalski", 50, "123123123", taxi1, 4000);
            newDriver2 = new Driver("Pawel", "Andrzejuk", 22, "123123123", taxi2, 4000);

            newAccountant1 = new Accountant("Robert", "Roberto", "123123123", 40, 3500);
            newAccountant2 = new Accountant("Hubert", "Kowalski", "123123123", 20, 3000);

        } catch (InvalidNumberOfSeatsException | InvalidPersonDataException | InvalidEmployeeDataException e) {
            LOGGER.error(e.getMessage());
        }

        /* USING BUILDER PATTERN */
        TaxiCompany taxiCompany = null;
        taxiCompany = new TaxiCompanyBuilder()
                .setName("Konrad Taxi")
                .setVehicles(taxiVehiclesList)
                .setDrivers(driversList)
                .setAccountants(accountants)
                .setCustomers(customers)
                .build();

        taxiCompany.addVehicles(Arrays.asList(taxi1, taxi2));

        try {
            taxi1.scheduleMaintenance(LocalDate.of(2024, 11, 8)); // Setting wrong maintenance date
        } catch (InvalidNextMaintenanceDateException ex) {
            LOGGER.error(ex.getMessage(), ex); // Exception for wrong next maintenance date
        }

        taxiCompany.addDrivers(Arrays.asList(newDriver1, newDriver2));
        taxiCompany.addAccountants(new HashSet<>(Arrays.asList(newAccountant1, newAccountant2)));
        // VehicleUtils.performMaintenance(taxiVehicle1);

        Location loc1 = new Location("New York", "Blue St");
        Location loc2 = new Location("New York", "Yellow St");

        TransportOrder tr1 = new TransportOrder(customers.get(0));
        tr1.addRouteStart(loc1);
        tr1.addRouteEnd(loc2);
        LOGGER.info(customers.get(0));

        driversList.get(0).move(loc1.getStreetName(), loc2.getStreetName(), 10.00);
        tr1.getCustomer().pay(driversList.get(0).getVehicle().calculatePrice(10.00), CurrencyType.USD);
        tr1.setPayment(new CashPayment(LocalDate.of(2023, 11, 3), driversList.get(0).getVehicle().calculatePrice(10.00), CurrencyType.USD));
        tr1.setReview(new Review(RatingType.AVERAGE, "It was an amazing ride!\n"));

        LOGGER.info("Customer: " + customers.get(0).getFirstName() + " spent money value: " + customers.get(0).getSpentMoney() + tr1.getPayment().getCurrency() + "!");

        driversList.get(0).endRide();

        changePosition(driversList.get(0), "Start", "End", 10.0); // Using interface as parameter
        changePosition(customers.get(0), "Start", "End", 10.0);
        taxiCompany.addTransportOrder(tr1, driversList.get(0));

        taxiCompany.printDrivers(driver -> driver.getFirstName() + " " + driver.getLastName());

        System.out.println("#####################\n\n");

        Class<?> reflectTaxiCompany = TaxiCompany.class;

        LOGGER.info("Taxi Company fields retrieved using reflection");
        Field[] fields = reflectTaxiCompany.getDeclaredFields();
        Constructor<?>[] constructors = reflectTaxiCompany.getDeclaredConstructors();
        Method[] methods = reflectTaxiCompany.getDeclaredMethods();

        Arrays.stream(fields).forEach(field -> {
            LOGGER.info("Name: " + field.getName() + " " + "Type: " + field.getType() + " " + "Modifiers: " + field.getModifiers());
        });

        LOGGER.info("\n\nTaxi Company constructors retrieved using reflection");
        Arrays.stream(constructors)
                .map(constructor -> "Name: " + constructor.getName() + ", Parameters: " + constructor.getParameterCount() + ", Modifiers: " + constructor.getModifiers())
                .forEach(LOGGER::info);

        LOGGER.info("\n\nTaxi Company methods retrieved using reflection");
        Arrays.stream(methods)
                .map(method -> "Name: " + method.getName() + ", Parameters: " + method.getParameterCount() + ", Modifiers: " + method.getModifiers())
                .forEach(LOGGER::info);


        // Create an object using reflection

        Constructor<?> constructor = null;

        TaxiCompany taxiCompany2;
        try {
            constructor = reflectTaxiCompany.getConstructor(String.class);
            taxiCompany2 = (TaxiCompany) constructor.newInstance("MyTaxiCompany");
            Method method = reflectTaxiCompany.getDeclaredMethod("display");
            method.invoke(taxiCompany);
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException e) {
            throw new RuntimeException(e);
        }

        LOGGER.info("Name of TaxiCompany created using reflection: " + taxiCompany2.getName());

        System.out.println("\n\n#####################\n\n");


        newAccountant1.generateFinancialReportByMonth(taxiCompany.getTransportOrders().orElse(Collections.emptyList()),
                LocalDate.of(2023, 11, 11));


        // Consumer
        taxiCompany.printAccountants(accountant -> LOGGER.info(accountant.getFirstName() + " " + accountant.getLastName()));
        // Predicate
        Predicate<Accountant> salaryAccountantFilter = accountant -> accountant.getSalary() > 2000;
        taxiCompany.filterAccountants(salaryAccountantFilter);
        // Function
        Function<Driver, String> driverDetailsMapper = driver -> "Driver: " + driver.getFirstName()
                + " " + driver.getLastName()
                + " | License Plate: "
                + driver.getVehicle().getRegistrationPlate();
        taxiCompany.printDrivers(driverDetailsMapper);
        // Supplier
        Supplier<Customer> customerSupplier = () -> {
            try {
                return new Customer("John", "Doe", "123123123");
            } catch (InvalidPersonDataException e) {
                LOGGER.error(e.getMessage());
                return null;
            }
        };
        taxiCompany.createCustomer(customerSupplier);

        try (Scanner scanner = new Scanner(System.in)) {

            while (true) {
                LOGGER.info("Taxi Company Menu:");
                LOGGER.info("1. Add new Vehicle");
                LOGGER.info("2. Add new Employee");
                LOGGER.info("3. Create a new Transport Order");
                LOGGER.info("4. Show all drivers with their transport orders");
                LOGGER.info("5. Generate financial report for chosen month");
                LOGGER.info("6. Save TaxiCompany data");
                LOGGER.info("7. Load TaxiCompany data");
                LOGGER.info("8. Exit");
                LOGGER.info("Enter your choice: ");

                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        TaxiCompanyMenu.addNewVehicle(scanner, taxiCompany);
                        break;

                    case 2:
                        TaxiCompanyMenu.addNewEmployee(scanner, taxiCompany);
                        break;

                    case 3:
                        TransportOrderMenu.addTransportOrder(scanner, taxiCompany);
                        break;

                    case 4:
                        taxiCompany.printDriverTransportOrders();
                        break;

                    case 5:
                        AccountantMenu.generateFinancialReportByMonth(scanner, taxiCompany);
                        break;

                    case 6:
                        if (taxiCompany != null) {
                            taxiCompany.saveState("src/main/resources/taxiCompany.ser");
                        }
                        break;

                    case 7:
                        taxiCompany = TaxiCompany.loadState("src/main/resources/taxiCompany.ser");
                        LOGGER.info("Taxi Company data succesfully loaded!");
                        break;

                    case 8:
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

    public static void changePosition(Transportable transportable, String startLocation, String destination, Double distance) {
        transportable.move(startLocation, destination, distance);
    }


}
