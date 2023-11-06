package com.solvd.laba.hw3.model.people.employees;

import com.solvd.laba.hw3.model.people.Employee;

public final class Accountant extends Employee {
    private static int accountantsCount;

    public Accountant(String firstName, String lastName, String phoneNumber, Integer age, Integer salary) {
        super(firstName, lastName, phoneNumber, age, salary);
        accountantsCount++;
    }

    public static int getAccountantsCount() {
        return accountantsCount;
    }

    @Override
    public void giveRaise() {
        this.salary += 200;
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
        System.out.println("Accountant's name: " + this.firstName + "surname " + this.lastName);
    }

    @Override
    public void showDetails() {
        System.out.println("Details for Accountant: \nName: " + this.firstName +
                "\nLast Name: " + this.lastName +
                "\nAge: " + this.age +
                "\nSalary: " + this.salary);
    }
}
