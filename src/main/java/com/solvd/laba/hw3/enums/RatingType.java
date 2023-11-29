package com.solvd.laba.hw3.enums;

import com.solvd.laba.hw3.exceptions.InvalidStarRatingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public enum RatingType {

    TERRIBLE("Terrible") {
        @Override
        public void displayMessage() {
            System.out.println("Oh no! We're sorry to hear that.");
        }
    },
    POOR("Poor") {
        @Override
        public void displayMessage() {
            System.out.println("We appreciate your feedback. We'll work on improving.");
        }
    },
    AVERAGE("Average") {
        @Override
        public void displayMessage() {
            System.out.println("Thank you for your feedback. We'll strive to do better.");
        }
    },
    GOOD("Good") {
        @Override
        public void displayMessage() {
            System.out.println("Great to hear! We're glad you had a positive experience.");
        }
    },
    EXCELLENT("Excellent") {
        @Override
        public void displayMessage() {
            LOGGER.info("Wow! Thanks for the fantastic feedback!");
        }
    };
    private static final Logger LOGGER = LogManager.getLogger(RatingType.class);
    private final String description;

    RatingType(String description) {
        this.description = description;
    }

    public static RatingType getByOption(int option) {
        switch (option) {
            case 1:
                return TERRIBLE;

            case 2:
                return POOR;

            case 3:
                return AVERAGE;

            case 4:
                return GOOD;

            case 5:
                return EXCELLENT;

            default:
                throw new InvalidStarRatingException("Invalid rating option: " + option);
        }
    }

    public String getDescription() {
        return description;
    }

    // Abstract method to show additional message after receiving an Review
    public abstract void displayMessage();
}