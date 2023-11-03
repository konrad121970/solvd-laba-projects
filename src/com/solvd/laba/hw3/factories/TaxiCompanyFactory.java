package com.solvd.laba.hw3.factories;

import com.solvd.laba.hw3.model.TaxiCompany;
import com.solvd.laba.hw3.model.customer.Customer;
import com.solvd.laba.hw3.model.employees.Accountant;
import com.solvd.laba.hw3.model.employees.Driver;
import com.solvd.laba.hw3.model.route.TransportOrder;
import com.solvd.laba.hw3.model.vehicles.TaxiVehicle;

public class TaxiCompanyFactory {
    public static TaxiCompany create() {
        TaxiVehicle[] vehicles = createVehicles();
        Driver[] drivers = createDrivers(vehicles);

        Customer[] customers = createCustomers();
        Accountant[] accountants = createAccountants();

        //[] locations = createLocations();
        TransportOrder[] transportOrders = new TransportOrder[]{};
        return new TaxiCompany(transportOrders, customers, drivers, accountants, vehicles);
    }

    private static TaxiVehicle[] createVehicles() {

        return new TaxiVehicle[]{
                new TaxiVehicle("Audi", "A4", "BHA 18XX", 4, 2.50),
                new TaxiVehicle("Volkswagen", "Kubelwagen", "BI 1234", 5, 3.00)
        };
    }

    private static Driver[] createDrivers(TaxiVehicle[] vehicles) {
        Driver driver1 = new Driver("Bartolomeo", "Diaz", 23, "123123123", vehicles[0], 3500);
        Driver driver2 = new Driver("Leon", "Kaputt", 67, "123123123", vehicles[1], 4000);
        return new Driver[]{driver1, driver2};
    }

    private static Accountant[] createAccountants() {
        return new Accountant[]{
                new Accountant("Katharine", "Note", "123123123", 23, 2500),
                new Accountant("Elias", "Bismark", "123123123", 19, 4000)
        };
    }

    private static Customer[] createCustomers() {
        return new Customer[]{
                new Customer("Andrzej", "Kowalski", "123123123"),
                new Customer("Paolo", "Nowak", "111222333"),
                new Customer("Herbert", "Shmidt", "333222111")
        };
    }
}
