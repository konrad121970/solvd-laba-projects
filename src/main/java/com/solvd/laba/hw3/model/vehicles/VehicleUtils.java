package com.solvd.laba.hw3.model.vehicles;

import java.time.LocalDate;

public class VehicleUtils {

    private VehicleUtils() {
        // Private constructor to prevent instantiation
    }

    public static void processVehicle(Vehicle vehicle) {
        // Display basic information about the vehicle
        vehicle.display();

        // Perform maintenance if needed
        if (vehicle.getNextMaintenance() != null && LocalDate.now().isAfter(vehicle.getNextMaintenance())) {
            vehicle.doMaintenance();
            System.out.println("Maintenance performed for the vehicle.");
        } else {
            System.out.println("No maintenance needed for the vehicle.");
        }
    }
}
