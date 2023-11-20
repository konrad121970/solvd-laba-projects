package com.solvd.laba.hw3.model.people;

import com.solvd.laba.hw3.model.exceptions.InvalidEmployeeDataException;
import com.solvd.laba.hw3.model.exceptions.InvalidPersonDataException;

import java.util.Objects;

public abstract class Employee extends Person {
    protected Integer salary;
    protected Integer age;

    public Employee(String firstName, String lastName, String phoneNumber, Integer age, Integer salary) throws InvalidEmployeeDataException, InvalidPersonDataException {
        super(firstName, lastName, phoneNumber);
        if (phoneNumber != null && age != null && salary != null) {
            if (age <= 0 || salary <= 0) {
                throw new InvalidEmployeeDataException("Invalid employee data: Age and salary must be greater than 0.");
            }
            this.salary = salary;
            this.age = age;
        }
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Employee employee = (Employee) o;
        return Objects.equals(salary, employee.salary) && Objects.equals(age, employee.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), salary, age);
    }

    @Override
    public String toString() {
        return "Employee [name=" + firstName + ", lastName=" + lastName + "]";
    }
}
