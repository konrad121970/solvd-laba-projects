package com.solvd.laba.hw2;

import com.solvd.laba.hw2.customer.Customer;
import com.solvd.laba.hw2.employees.Accountant;
import com.solvd.laba.hw2.employees.Driver;
import com.solvd.laba.hw2.route.Trip;
import com.solvd.laba.hw2.vehicles.Car;

public class TaxiCompany {
    private Customer[] customers;
    private Driver[] drivers;
    private Accountant[] accountants;
    private Trip[] trips;
    private Car[] cars;

    public TaxiCompany(Customer[] customers, Driver[] drivers, Accountant[] accountants, Trip[] trips, Car[] cars) {
        this.customers = customers;
        this.drivers = drivers;
        this.accountants = accountants;
        this.trips = trips;
        this.cars = cars;
    }

    public void printCustomerNames(){
        int i = 1;
        for (Customer customer: customers) {
            System.out.println("Customer " + i + ": " + customer.getFirstName() + " " + customer.getLastName());
            i++;
        }
    }

    public Customer[] getCustomers() {
        return customers;
    }

    public void setCustomers(Customer[] customers) {
        this.customers = customers;
    }

    public Trip[] getTrips() {
        return trips;
    }

    public void setTrips(Trip[] trips) {
        this.trips = trips;
    }

    public Car[] getCars() {
        return cars;
    }

    public void setCars(Car[] cars) {
        this.cars = cars;
    }

    public Driver[] getDrivers() {
        return drivers;
    }

    public void setDrivers(Driver[] drivers) {
        this.drivers = drivers;
    }

    public Accountant[] getAccountants() {
        return accountants;
    }

    public void setAccountants(Accountant[] accountants) {
        this.accountants = accountants;
    }
}
