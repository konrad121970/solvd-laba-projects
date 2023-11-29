package com.solvd.laba.hw3.model.people;

import com.solvd.laba.hw3.exceptions.InvalidPersonDataException;
import com.solvd.laba.hw3.interfaces.Displayable;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

public abstract class Person implements Displayable {
    protected String firstName;
    protected String lastName;
    protected String phoneNumber;

    public Person(String firstName, String lastName, String phoneNumber) throws InvalidPersonDataException {
        if (StringUtils.isEmpty(firstName) || StringUtils.isEmpty(lastName)) {
            throw new InvalidPersonDataException("Invalid " + this.getClass().getSimpleName() + " data: First name and last name must not be empty.");
        }
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    public final String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public final String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public final String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(firstName, person.firstName) && Objects.equals(lastName, person.lastName) && Objects.equals(phoneNumber, person.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, phoneNumber);
    }

    @Override
    public String toString() {
        return "Person [name=" + firstName + ", age=" + lastName + "]";
    }
}
