package com.solvd.laba.hw3.menu.company;

import com.solvd.laba.hw3.common.enums.LocationType;
import com.solvd.laba.hw3.model.route.Location;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.util.Scanner;

public class LocationMenu {
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    public static Location addStartLocation(Scanner scanner) {

        String city, pickupLocation;
        do {
            LOGGER.info("City: ");
            city = scanner.nextLine();

            LOGGER.info("Pickup Location: ");
            pickupLocation = scanner.nextLine();

            if (!isValidLocation(city, pickupLocation)) {
                LOGGER.info("Invalid input. It cannot be empty. Please try again.");
            }

        } while (!isValidLocation(city, pickupLocation));

        return new Location(city, pickupLocation, LocationType.ROUTE_START);
    }

    public static Location addEndLocation(Scanner scanner) {
        String city, dropoffLocation;

        do {
            LOGGER.info("City: ");
            city = scanner.nextLine();

            LOGGER.info("Dropoff Location: ");
            dropoffLocation = scanner.nextLine();

            if (!isValidLocation(city, dropoffLocation)) {
                LOGGER.info("Invalid input. It cannot be empty. Please try again.");
            }

        } while (!isValidLocation(city, dropoffLocation));

        return new Location(city, dropoffLocation, LocationType.ROUTE_END);
    }

    public static Location addRouteStop(Scanner scanner) {
        String city, dropoffLocation;

        do {
            LOGGER.info("City: ");
            city = scanner.nextLine();

            LOGGER.info("Dropoff Location: ");
            dropoffLocation = scanner.nextLine();

            if (!isValidLocation(city, dropoffLocation)) {
                LOGGER.info("Invalid input. It cannot be empty. Please try again.");
            }

        } while (!isValidLocation(city, dropoffLocation));

        return new Location(city, dropoffLocation, LocationType.INTERMEDIATE_STOP);
    }

    private static boolean isValidLocation(String city, String location) {
        return !city.isEmpty() && !location.isEmpty();
    }


}
