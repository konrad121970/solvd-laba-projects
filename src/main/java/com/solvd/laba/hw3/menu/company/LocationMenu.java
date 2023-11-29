package com.solvd.laba.hw3.menu.company;

import com.solvd.laba.hw3.enums.LocationType;
import com.solvd.laba.hw3.model.route.Location;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.util.Scanner;

public class LocationMenu {
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    public static Location addStartLocation(Scanner scanner) {
        LOGGER.info("City: ");
        scanner.nextLine();
        String city = scanner.nextLine();
        LOGGER.info("Pickup Location: ");
        String pickupLocation = scanner.nextLine();

        return new Location(city, pickupLocation, LocationType.ROUTE_START);
    }

    public static Location addEndLocation(Scanner scanner) {
        LOGGER.info("City: ");
        scanner.nextLine();
        String city = scanner.nextLine();
        LOGGER.info("Dropoff Location: ");
        String pickupLocation = scanner.nextLine();

        return new Location(city, pickupLocation, LocationType.ROUTE_END);
    }

    public static Location addStopLocation(Scanner scanner) {
        LOGGER.info("City: ");
        scanner.nextLine();
        String city = scanner.nextLine();
        LOGGER.info("Stop Location: ");
        String pickupLocation = scanner.nextLine();

        return new Location(city, pickupLocation, LocationType.INTERMEDIATE_STOP);
    }

}
