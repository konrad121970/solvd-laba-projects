package com.solvd.laba.hw3.model.customer;

public class Customer extends Person {
    private static int customersCount;
    private Double spentMoney = 0.0;

    public Customer(String firstName, String lastName, String phoneNumber) {
        super(firstName, lastName, phoneNumber);

        customersCount++;
    }

    public static int getCustomersCount() {
        return customersCount;
    }

    public void pay(Double cash) {
        System.out.println("I have just spent " + cash + "USD!");
        this.spentMoney += cash;
    }

    // Method Overloading
    public void pay(Double cash, String message) {
        System.out.println("I have just spent " + cash + "USD!" + " " + message);
        this.spentMoney += cash;
    }

    public Double getSpentMoney() {
        return spentMoney;
    }

    public void setSpentMoney(Double amount) {
        this.spentMoney = spentMoney;
    }

    @Override
    public void displayInfo() {
        System.out.println("Customer Info: Name" + firstName + " Last name: " + lastName);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + customersCount;
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
        Customer otherCustomer = (Customer) obj;
        return super.equals(obj) && customersCount == otherCustomer.customersCount;
    }

    @Override
    public String toString() {
        return "Customer [firstName=" + firstName + ", lastName=" + lastName + "]";
    }
}
