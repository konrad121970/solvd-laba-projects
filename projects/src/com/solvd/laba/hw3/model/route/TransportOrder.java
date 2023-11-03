package com.solvd.laba.hw3.model.route;

import com.solvd.laba.hw3.model.customer.Customer;
import com.solvd.laba.hw3.model.employees.Driver;
import com.solvd.laba.hw3.model.vehicles.TaxiVehicle;

import java.time.LocalDate;

public class TransportOrder {
    private Location routeStart;
    private Location routeEnd;
    private Customer customer;
    private Driver driver;
    private Double distanceInKm;
    private LocalDate date;
    private Payment payment;
    private Review review;

    public TransportOrder(Location routeStart, Location routeEnd, Customer customer, Driver driver, Double distanceInKm, LocalDate date) {
        this.routeStart = routeStart;
        this.routeEnd = routeEnd;
        this.customer = customer;
        this.driver = driver;
        this.distanceInKm = distanceInKm;
        this.date = date;
    }

    public Double calculatePrice(Double distanceInKm, TaxiVehicle taxiVehicle) {
        return distanceInKm * taxiVehicle.getFarePerKilometer();
    }


    public Location getRouteStart() {
        return routeStart;
    }

    public void setRouteStart(Location routeStart) {
        this.routeStart = routeStart;
    }

    public Location getRouteEnd() {
        return routeEnd;
    }

    public void setRouteEnd(Location routeEnd) {
        this.routeEnd = routeEnd;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Double getDistanceInKm() {
        return distanceInKm;
    }

    public void setDistanceInKm(Double distanceInKm) {
        this.distanceInKm = distanceInKm;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
