package com.solvd.laba.hw3.model.route;

import com.solvd.laba.hw3.enums.LocationType;
import com.solvd.laba.hw3.interfaces.Displayable;
import com.solvd.laba.hw3.model.payment.Payment;
import com.solvd.laba.hw3.model.people.customer.Customer;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class TransportOrder implements Displayable {
    private static final Logger LOGGER = LogManager.getLogger(TransportOrder.class);
    private final Customer customer;
    private List<Location> routeStops;
    private Payment payment;
    private Review review;

    public TransportOrder(Customer customer) {
        this.routeStops = new ArrayList<>();
        this.customer = customer;
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }

    public void addRouteStart(Location location) {
        location.setLocationType(LocationType.ROUTE_START);
        routeStops.add(location);
    }

    public void addRouteEnd(Location location) {
        location.setLocationType(LocationType.ROUTE_END);
        routeStops.add(location);
    }

    public void addRouteStop(Location location) {
        location.setLocationType(LocationType.INTERMEDIATE_STOP);
        routeStops.add(location);
    }


    public Customer getCustomer() {
        return customer;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        if (payment != null) {
            this.payment = payment;
        } else {
            LOGGER.error("Invalid payment: cannot be null");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransportOrder that = (TransportOrder) o;
        return Objects.equals(customer, that.customer) && Objects.equals(routeStops, that.routeStops) && Objects.equals(payment, that.payment) && Objects.equals(review, that.review);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customer, routeStops, payment, review);
    }

    @Override
    public String toString() {
        return "TransportOrder{" +
                "customer=" + customer +
                ", routeStops=" + routeStops +
                ", payment=" + payment +
                ", review=" + review +
                '}';
    }

    @Override
    public void display() {
        LOGGER.info(StringUtils.join(
                "TransportOrder{" +
                        "customer=" + customer +
                        ", routeStops=" + routeStops +
                        '}'
        ));
    }

    @Override
    public void showDetails() {
        LOGGER.info(StringUtils.join(
                "Transport Order Details:",
                "TransportOrder{" +
                        "customer=" + customer +
                        ", routeStops=" + routeStops +
                        ", payment=" + payment +
                        ", review=" + review +
                        '}'
        ));
    }
}
