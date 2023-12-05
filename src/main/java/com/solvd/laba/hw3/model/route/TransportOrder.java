package com.solvd.laba.hw3.model.route;

import com.solvd.laba.hw3.common.enums.LocationType;
import com.solvd.laba.hw3.common.interfaces.Displayable;
import com.solvd.laba.hw3.model.payment.Payment;
import com.solvd.laba.hw3.model.people.customer.Customer;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class TransportOrder implements Displayable, Serializable {
    private static final Logger LOGGER = LogManager.getLogger(TransportOrder.class);
    private Customer customer;
    private List<com.solvd.laba.hw3.model.route.Location> routeStops;
    private Payment payment;
    private Review review;

    public TransportOrder() {
    }

    public TransportOrder(Customer customer) {
        this.customer = customer;
    }

    public List<com.solvd.laba.hw3.model.route.Location> getRouteStops() {
        return routeStops;
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        if (review != null) {
            this.review = review;
        } else {
            LOGGER.warn("You can't set null value to review!");
        }
    }

    public void addRouteStart(com.solvd.laba.hw3.model.route.Location location) {
        if (location != null) {
            if (routeStops == null) {
                routeStops = new ArrayList<>();
            }
            location.setLocationType(LocationType.ROUTE_START);
            routeStops.add(location);
        } else {
            LOGGER.warn("Location cannot be null");
        }
    }

    public void addRouteEnd(com.solvd.laba.hw3.model.route.Location location) {
        if (location != null) {
            if (routeStops == null) {
                routeStops = new ArrayList<>();
            }
            location.setLocationType(LocationType.ROUTE_END);
            routeStops.add(location);
        } else {
            LOGGER.warn("Location cannot be null");
        }
    }

    public void addRouteStop(com.solvd.laba.hw3.model.route.Location location) {
        if (location != null) {
            if (routeStops == null) {
                routeStops = new ArrayList<>();
            }
            location.setLocationType(LocationType.INTERMEDIATE_STOP);
            routeStops.add(location);
        } else {
            LOGGER.warn("Location cannot be null");
        }
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        if (customer != null) {
            this.customer = customer;
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
