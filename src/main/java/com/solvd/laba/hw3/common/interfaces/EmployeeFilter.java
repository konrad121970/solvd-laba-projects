package com.solvd.laba.hw3.common.interfaces;

import com.solvd.laba.hw3.model.people.Employee;

@FunctionalInterface
public interface EmployeeFilter<T extends Employee> {
    boolean filter(T employee); // boolean because it is a predicate

}
