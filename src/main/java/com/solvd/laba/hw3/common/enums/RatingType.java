package com.solvd.laba.hw3.common.enums;

import com.solvd.laba.hw3.common.exceptions.InvalidStarRatingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public enum RatingType {

    TERRIBLE("Terrible", "Oh no! We're sorry to hear that."),
    POOR("Poor", "We appreciate your feedback. We'll work on improving."),
    AVERAGE("Average", "Thank you for your feedback. We'll strive to do better."),
    GOOD("Good", "Great to hear! We're glad you had a positive experience."),
    EXCELLENT("Excellent", "Wow! Thanks for the fantastic feedback!");
    private static final Logger LOGGER = LogManager.getLogger(RatingType.class);
    private final String name;
    private final String message;

    RatingType(String rating, String message) {
        this.name = rating;
        this.message = message;
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

    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }
}