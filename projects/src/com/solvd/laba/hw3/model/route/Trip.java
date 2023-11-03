package com.solvd.laba.hw3.model.route;

import com.solvd.laba.hw3.model.customer.Customer;
import com.solvd.laba.hw3.model.employees.Driver;

import java.time.LocalDate;

public class Trip {
    private Driver driver;
    private Review review;
    private Payment payment;


    public Trip(Driver driver,
                Customer customer,
                Location routeStart,
                Location routeEnd,
                Double distanceInKm,
                LocalDate date) {
        this.driver = driver;
    }

    public Double calculatePrice(Double distanceInKm) {
        return distanceInKm * 2.00;
    }


    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}
