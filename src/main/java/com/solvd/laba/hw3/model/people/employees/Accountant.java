package com.solvd.laba.hw3.model.people.employees;

import com.solvd.laba.hw3.common.exceptions.InvalidEmployeeDataException;
import com.solvd.laba.hw3.common.exceptions.InvalidPersonDataException;
import com.solvd.laba.hw3.model.people.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Accountant extends Employee {

    private static final Logger LOGGER = LogManager.getLogger(Accountant.class);
    private static int accountantsCount;

    public Accountant() {
    }

    public Accountant(String firstName, String lastName, String phoneNumber,
                      Integer age, Integer salary) throws InvalidPersonDataException, InvalidEmployeeDataException {
        super(firstName, lastName, phoneNumber, age, salary);
        accountantsCount++;
    }

    public static int getAccountantsCount() {
        return accountantsCount;
    }

    @Override
    protected int givebonus() {
        if (age > 30 && salary > 10000) {
            return 1000;
        } else return 500;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + accountantsCount;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Accountant otherAccountant = (Accountant) obj;
        return super.equals(obj) && accountantsCount == accountantsCount;
    }

    @Override
    public String toString() {
        return "Accountant [name=" + firstName + ", lastName=" + lastName + "]";
    }

    @Override
    public void display() {
        LOGGER.info("Accountant's name: " + this.firstName + "surname " + this.lastName);
    }

    @Override
    public void showDetails() {
        LOGGER.info("Details for Accountant: \nName: " + this.firstName +
                "\nLast Name: " + this.lastName +
                "\nAge: " + this.age +
                "\nSalary: " + this.salary);
    }
}
