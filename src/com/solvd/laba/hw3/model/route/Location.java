package com.solvd.laba.hw3.model.route;

import com.solvd.laba.hw3.model.interfaces.Displayable;

import java.util.Objects;

public final class Location implements Displayable {
    private String city;
    private String streetName;

    public Location(String city, String streetName) {
        this.city = city;
        this.streetName = streetName;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, streetName);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Location otherLocation = (Location) obj;
        return Objects.equals(city, otherLocation.city) &&
                Objects.equals(streetName, otherLocation.streetName);
    }

    @Override
    public String toString() {
        return "Location [city = '" + city + "', streetName = '" + streetName + "']";
    }

    // TODO:
    @Override
    public void display() {

    }

    @Override
    public void showDetails() {

    }
}
