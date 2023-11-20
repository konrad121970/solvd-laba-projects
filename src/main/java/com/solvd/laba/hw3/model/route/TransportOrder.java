package com.solvd.laba.hw3.model.route;

import com.solvd.laba.hw3.model.interfaces.Displayable;
import com.solvd.laba.hw3.model.payment.Payment;
import com.solvd.laba.hw3.model.people.customer.Customer;
import com.solvd.laba.hw3.model.people.employees.Driver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

public final class TransportOrder implements Displayable {
    private static final Logger LOGGER = LogManager.getLogger(TransportOrder.class);
    private final Customer customer;
    private Location routeStart;
    private Location routeEnd;
    private Driver driver;
    private Payment payment;
    private Review review;

    public TransportOrder(Location routeStart, Location routeEnd, Customer customer, Driver driver) {
        this.routeStart = routeStart;
        this.routeEnd = routeEnd;
        this.customer = customer;
        this.driver = driver;
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }

    public Location getRouteStart() {
        return routeStart;
    }

    public void setRouteStart(Location routeStart) {
        if (routeStart != null) {
            this.routeStart = routeStart;
        } else {
            LOGGER.error("Invalid route start location: cannot be null or empty");
            // You can choose to throw an exception, log an error, or handle it according to your application's needs
        }
    }

    public Location getRouteEnd() {
        return routeEnd;
    }

    public void setRouteEnd(Location routeEnd) {
        if (routeEnd != null) {
            this.routeEnd = routeEnd;
        } else {
            LOGGER.error("Invalid route end location: cannot be null");
            // You can choose to throw an exception, log an error, or handle it according to your application's needs
        }
    }

    public Customer getCustomer() {
        return customer;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        if (driver != null) {
            this.driver = driver;
        } else {
            LOGGER.error("Invalid driver: cannot be null");
            // You can choose to throw an exception, log an error, or handle it according to your application's needs
        }
    }


    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        if (payment != null) {
            this.payment = payment;
        } else {
            LOGGER.error("Invalid payment: cannot be null");
            // You can choose to throw an exception, log an error, or handle it according to your application's needs
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransportOrder that = (TransportOrder) o;
        return Objects.equals(routeStart, that.routeStart) && Objects.equals(routeEnd, that.routeEnd) && Objects.equals(customer, that.customer) && Objects.equals(driver, that.driver) && Objects.equals(payment, that.payment) && Objects.equals(review, that.review);
    }

    @Override
    public int hashCode() {
        return Objects.hash(routeStart, routeEnd, customer, driver, payment, review);
    }

    @Override
    public String toString() {
        return "TransportOrder{" +
                "routeStart=" + routeStart +
                ", routeEnd=" + routeEnd +
                ", customer=" + customer +
                ", driver=" + driver +
                ", payment=" + payment +
                ", review=" + review +
                '}';
    }

    // TODO:
    @Override
    public void display() {

    }

    @Override
    public void showDetails() {

    }
}