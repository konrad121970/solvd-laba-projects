package route;

import customer.Customer;
import employees.Driver;

import java.time.LocalDate;

public class Trip {
    private Driver driver;
    private Customer customer;
    private Review review;
    private Payment payment;
    private Location routeStart;
    private Location routeEnd;
    private Double distanceInKm;
    private LocalDate date;

    public Trip(Driver driver,
                Customer customer,
                Review review,
                Payment payment, Location routeStart,
                Location routeEnd,
                Double distanceInKm, LocalDate date) {
        this.driver = driver;
        this.customer = customer;
        this.review = review;
        this.payment = payment;
        this.routeStart = routeStart;
        this.routeEnd = routeEnd;
        this.distanceInKm = distanceInKm;
        this.date = date;
    }

    private Double calculatePrice(Double distanceInKm){
        return  distanceInKm * 2.00;
    }


    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
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
}
