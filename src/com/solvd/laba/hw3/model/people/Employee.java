package com.solvd.laba.hw3.model.people;

public abstract class Employee extends Person {
    protected Integer salary;
    protected Integer age;

    public Employee(String firstName, String lastName, String phoneNumber, Integer age, Integer salary) {
        super(firstName, lastName, phoneNumber);
        this.salary = salary;
        this.age = age;
    }

    public abstract void giveRaise();

    public final Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public final Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "Employee [name=" + firstName + ", lastName=" + lastName + "]";
    }
}
