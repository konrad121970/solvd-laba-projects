package com.solvd.laba.hw3.menu.input;

import com.solvd.laba.hw3.common.exceptions.InvalidPersonDataException;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class InputReader {
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    public static Double readDoubleData(Scanner scanner) {
        double number = 0;
        boolean validInput = false;

        while (!validInput) {
            String input = scanner.next();

            try {
                // Try parsing with dot as the decimal separator
                number = Double.parseDouble(input);
                validInput = true;
            } catch (NumberFormatException e1) {
                try {
                    // Try parsing with comma as the decimal separator
                    number = Double.parseDouble(input.replace(",", "."));
                    validInput = true;
                } catch (NumberFormatException e2) {
                    LOGGER.warn("Invalid input. Please enter a valid data in X.XX or X,XX format.");
                }
            }
        }

        return number;
    }

    public static LocalDate readRideDate(Scanner scanner) {
        LocalDate orderDate = null;
        boolean validInput = false;

        while (!validInput) {
            try {
                String orderDateStr = scanner.next();
                orderDate = LocalDate.parse(orderDateStr);
                validInput = true;
            } catch (DateTimeParseException e) {
                LOGGER.warn("Invalid date format. Please enter a valid date in the yyyy-MM-dd format.");
            }
        }

        return orderDate;
    }

    public static int readAge(Scanner scanner) {
        int age = 0;
        boolean validInput = false;

        while (!validInput) {
            try {
                age = scanner.nextInt();
                if (age < 18) throw new InvalidPersonDataException("Age must be greater than 17!");
                validInput = true;
            } catch (InputMismatchException e) {
                LOGGER.error("Invalid input. Please enter a valid age as a number higher than 17.");
                scanner.next(); // Read invalid input to avoid an infinite loop
            } catch (InvalidPersonDataException e) {
                LOGGER.error(e.getMessage());
            }
        }

        return age;
    }

    public static int readSalary(Scanner scanner) {
        int salary = 0;
        boolean validInput = false;

        while (!validInput) {
            try {
                salary = scanner.nextInt();

                if (salary > 0) {
                    validInput = true;
                } else {
                    LOGGER.warn("Invalid salary. Please enter a positive integer value.");
                }
            } catch (InputMismatchException e) {
                LOGGER.warn("Invalid input. Please enter a valid integer value.");
                scanner.next(); // Read invalid input to avoid an infinite loop
            }
        }

        return salary;
    }

    public static int readStarRating(Scanner scanner) {
        int starRating = 0;
        boolean validInput = false;

        while (!validInput) {
            try {
                starRating = scanner.nextInt();

                if (starRating >= 1 && starRating <= 5) {
                    validInput = true;
                } else {
                    LOGGER.warn("Invalid star rating. Please enter a rating between 1 and 5.");
                }
            } catch (InputMismatchException e) {
                LOGGER.warn("Invalid input. Please enter a valid number for star rating.");
                scanner.next(); // Read invalid input to avoid an infinite loop
            }
        }

        return starRating;
    }

    public static int readNumberOfSeats(Scanner scanner) {
        int numberOfSeats = 0;
        boolean validInput = false;

        while (!validInput) {
            try {
                numberOfSeats = scanner.nextInt();

                if (numberOfSeats > 0 && numberOfSeats <= 300) {
                    validInput = true;
                } else {
                    LOGGER.warn("Invalid number of seats. Please enter a value between 1 and 300.");
                }
            } catch (InputMismatchException e) {
                LOGGER.warn("Invalid input. Please enter a valid number for the number of seats.");
                scanner.next(); // Read invalid input to avoid an infinite loop
            }
        }

        return numberOfSeats;
    }

    public static String readUserInput(Scanner scanner, String prompt) {
        LOGGER.info(StringUtils.rightPad(prompt + ":", 20) + StringUtils.leftPad("", 30));
        return scanner.next();
    }
}
