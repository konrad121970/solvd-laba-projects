package com.solvd.laba.hw3.menu.company;

import com.solvd.laba.hw3.menu.input.InputReader;
import com.solvd.laba.hw3.model.people.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.util.Scanner;

public class PersonMenu {
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    public static Person createPerson(Scanner scanner, Person person) {
        LOGGER.info("First Name: ");
        String firstName = scanner.next();
        LOGGER.info("Last Name: ");
        String lastName = scanner.next();
        LOGGER.info("Age: ");
        int age = InputReader.readAge(scanner);
        LOGGER.info("Phone Number: ");
        String phoneNumber = scanner.next();

        return person;
    }
}
