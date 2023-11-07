package com.solvd.laba.hw3.model.route;

import com.solvd.laba.hw3.model.interfaces.Displayable;
import com.solvd.laba.hw3.model.payment.Payment;
import com.solvd.laba.hw3.model.people.customer.Customer;
import com.solvd.laba.hw3.model.people.employees.Driver;

import java.util.Objects;

public final class TransportOrder implements Displayable {
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
