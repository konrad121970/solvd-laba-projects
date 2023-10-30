package employees;

import route.Location;
import vehicles.Car;

public class Driver {
    private static int driversCount;
    private String firstName;
    private String lastName;
    private int age;
    private String phoneNumber;
    private Car car;


    public Driver(String firstName, String lastName, int age, String phoneNumber, Car car) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.car = car;

        driversCount++;
    }

    private void driveTo(Location startLocation, Location endLocation){
        System.out.println("I am en route to " + endLocation + "!. I started my journey from " + startLocation + ".");
    }

    public static int getDriversCount() {
        return driversCount;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
