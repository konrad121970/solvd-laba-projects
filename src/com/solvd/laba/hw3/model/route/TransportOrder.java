package com.solvd.laba.hw3.model.route;

import com.solvd.laba.hw3.model.people.customer.Customer;
import com.solvd.laba.hw3.model.people.employees.Driver;

import java.time.LocalDate;

public class TransportOrder {
    private Location routeStart;
    private Location routeEnd;
    private Customer customer;
    private Driver driver;
    private Payment payment;
    private Review review;

    public TransportOrder(Location routeStart, Location routeEnd, Customer customer, Driver driver) {
        this.routeStart = routeStart;
        this.routeEnd = routeEnd;
        this.customer = customer;
        this.driver = driver;
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
