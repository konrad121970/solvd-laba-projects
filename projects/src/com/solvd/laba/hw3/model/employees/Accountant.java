package com.solvd.laba.hw3.model.employees;

public class Accountant extends Employee {
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
    public void displayInfo() {
        System.out.println("Accountant Info: Name: " + firstName + " Last Name: " + lastName);
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
        return super.equals(obj) && accountantsCount == otherAccountant.accountantsCount;
    }

    @Override
    public String toString() {
        return "Accountant [name=" + firstName + ", lastName=" + lastName + "]";
    }
}
