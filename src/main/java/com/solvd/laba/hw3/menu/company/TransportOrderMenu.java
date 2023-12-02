package com.solvd.laba.hw3.menu.company;

import com.solvd.laba.hw3.menu.input.InputReader;
import com.solvd.laba.hw3.model.TaxiCompany;
import com.solvd.laba.hw3.model.people.employees.Driver;
import com.solvd.laba.hw3.model.route.TransportOrder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.time.LocalDate;
import java.util.Scanner;


public class TransportOrderMenu {
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    public static void addTransportOrder(Scanner scanner, TaxiCompany taxiCompany) {
        TransportOrder transportOrder = new TransportOrder();
        LOGGER.info("Create a Transport Order:");

        transportOrder.addRouteStart(LocationMenu.addStartLocation(scanner));
        transportOrder.addRouteEnd(LocationMenu.addEndLocation(scanner));

        CustomerMenu.addCustomer(scanner, transportOrder);


        //TODO: DEBUG
        Driver driver = null;
        try {
            driver = EmployeeMenu.selectDriver(scanner, taxiCompany);
        } catch (Exception e) {
            throw new RuntimeException();
        }

        LOGGER.info("Ride Date (yyyy-MM-dd): ");
        LocalDate orderDate = InputReader.readRideDate(scanner);

        LOGGER.info("Enter the distance in kilometers(X,XX or X.XX format): ");
        Double distance = InputReader.readDoubleData(scanner);


        driver.driveFromTo((transportOrder.getRouteStops().stream().findFirst().get().getStreetName()), // Find first element
                transportOrder.getRouteStops().stream().reduce((first, second) -> second).get().getStreetName(), // Find last element
                distance);

        driver.getVehicle().calculatePrice(distance);
        LOGGER.info("Order price: " + driver.getVehicle().getFareCost());

        PaymentMenu.addPayment(scanner, orderDate, transportOrder);

        ReviewMenu.addReview(scanner, transportOrder);

        taxiCompany.addTransportOrder(transportOrder, driver);
        driver.endRide();
        scanner.nextLine();
        LOGGER.info("New Transport Order added!.");

    }

}
