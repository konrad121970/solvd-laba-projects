package com.solvd.laba.hw3.model.route;

import com.solvd.laba.hw3.common.enums.LocationType;
import com.solvd.laba.hw3.common.interfaces.Displayable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Serializable;
import java.util.Objects;

public final class Location implements Displayable, Serializable {
    private static final Logger LOGGER = LogManager.getLogger(Location.class);
    private String city;
    private String streetName;
    private LocationType locationType;

    public Location(String city, String streetName) {
        this.city = city;
        this.streetName = streetName;
    }

    public Location(String city, String streetName, LocationType locationType) {
        this.city = city;
        this.streetName = streetName;
        this.locationType = locationType;
    }

    public LocationType getLocationType() {
        return locationType;
    }

    public void setLocationType(LocationType locationType) {
        this.locationType = locationType;
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
        LOGGER.info("Location [city = '" + city + "', streetName = '" + streetName + "']");
    }

    @Override
    public void showDetails() {
        LOGGER.info("Location [city = '" + city + "', streetName = '" + streetName + "']");
    }
}
