package com.solvd.laba.hw3;

import com.solvd.laba.hw3.model.TaxiCompany;
import com.solvd.laba.hw3.model.exceptions.DuplicateRegistrationPlateException;
import com.solvd.laba.hw3.model.vehicles.TaxiVehicle;
import com.solvd.laba.hw3.model.vehicles.Vehicle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TaxiCompanyUtility {
    private static final Logger LOGGER = LogManager.getLogger(TaxiCompanyUtility.class);

    public static void assignVehicleToCompany(Vehicle vehicle, TaxiCompany company) {
        if (vehicle.getClass() == Vehicle.class) {
            try {
                company.addVehicle((TaxiVehicle) vehicle);
            } catch (DuplicateRegistrationPlateException ex) {
                LOGGER.error(ex.getMessage());
            }
            LOGGER.info("Vehicle assigned to the company.");
        } else {
            LOGGER.info("Invalid vehicle assignment. Child classes not allowed.");
        }
    }
}
