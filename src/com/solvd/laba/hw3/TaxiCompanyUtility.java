package com.solvd.laba.hw3;

import com.solvd.laba.hw3.model.TaxiCompany;
import com.solvd.laba.hw3.model.vehicles.Vehicle;

public class TaxiCompanyUtility {
    public static void assignVehicleToCompany(Vehicle vehicle, TaxiCompany company) {
        if (vehicle.getClass() == Vehicle.class) {
            company.addVehicle(vehicle);
            System.out.println("Vehicle assigned to the company.");
        } else {
            System.out.println("Invalid vehicle assignment. Child classes not allowed.");
        }
    }
}
