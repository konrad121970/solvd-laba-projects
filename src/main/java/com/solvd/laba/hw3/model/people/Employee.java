package com.solvd.laba.hw3.model.people;

import com.solvd.laba.hw3.exceptions.InvalidEmployeeDataException;
import com.solvd.laba.hw3.exceptions.InvalidPersonDataException;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

public abstract class Employee extends Person {
    protected double salary;
    protected Integer age;

    public Employee() {
    }

    public Employee(String firstName, String lastName, String phoneNumber, Integer age, Integer salary) throws InvalidEmployeeDataException, InvalidPersonDataException {
        super(firstName, lastName, phoneNumber);
        if (!StringUtils.isEmpty(phoneNumber) && age != null && salary != null) {
            if (age < 18) {
                throw new InvalidEmployeeDataException("Invalid employee data: Age must be greater than 17.");
            }
            if (salary <= 0) {
                throw new InvalidEmployeeDataException("Invalid employee data: Salary must be greater than 0.");
            }
            this.salary = salary;
            this.age = age;
        }
    }

    /* TEMPLATE METHOD */
    /* Everytime an employee receives a raise he also gets a bonus. */
    public final void giveRaise(Double percentRaise) {
        this.salary *= percentRaise + bonus();
    }

    protected abstract int bonus();

    public Integer getSalary() {
        return (int) salary;
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
