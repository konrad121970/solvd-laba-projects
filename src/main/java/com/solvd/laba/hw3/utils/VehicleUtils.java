package com.solvd.laba.hw3.utils;

import com.solvd.laba.hw3.model.vehicles.Vehicle;

import java.time.LocalDate;
import java.util.Optional;

public class VehicleUtils {

    private VehicleUtils() {
        // Private constructor to prevent instantiation
    }

    public static void performMaintenance(Vehicle vehicle) {
        // Display basic information about the vehicle
        vehicle.display();

        // Perform maintenance if needed
        Optional.ofNullable(vehicle.getNextMaintenance())
                .filter(nextMaintenanceDate -> LocalDate.now().isAfter(nextMaintenanceDate))
                .ifPresentOrElse(
                        nextMaintenanceDate -> {
                            vehicle.doMaintenance();
                            System.out.println("Maintenance performed for the vehicle.");
                        },
                        () -> System.out.println("No maintenance needed for the vehicle.")
                );
    }
}
