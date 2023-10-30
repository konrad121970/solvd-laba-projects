package model.customer;

public class Customer {
    private static int customersCount;
    private String firstName;
    private String lastName;
    private int age;
    private String phoneNumber;
    private Double cash;

    public Customer(String firstName, String lastName, int age, String phoneNumber, Double availableBalance) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.cash = availableBalance;

        customersCount++;
    }

    public void spendCash(Double cash){
        System.out.println("I have just spent " + cash + "USD!");
    }

    // Method Overloading
    public void spendCash(Double cash, String message){
        System.out.println("I have just spent " + cash + "USD!" + message);
    }

    public static int getCustomersCount() {
        return customersCount;
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

    public Double getCash() {
        return cash;
    }

    public void setCash(Double cash) {
        this.cash = cash;
    }


}
