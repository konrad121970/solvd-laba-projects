package model;

import model.customer.Customer;
import model.employees.Employees;
import model.route.Trip;
import model.vehicles.Car;

public class TaxiCompany {
    private Customer[] customers;
    private Employees employees;
    private Trip[] trips;
    private Car[] cars;

    public TaxiCompany(Customer[] customers, Employees employees, Trip[] trips, Car[] cars) {
        this.customers = customers;
        this.employees = employees;
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

    public Employees getEmployees() {
        return employees;
    }

    public void setEmployees(Employees employees) {
        this.employees = employees;
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
}
