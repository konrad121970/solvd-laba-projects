package com.solvd.laba.hw3.menu.company;

import com.solvd.laba.hw3.enums.RatingType;
import com.solvd.laba.hw3.exceptions.InvalidStarRatingException;
import com.solvd.laba.hw3.menu.input.InputReader;
import com.solvd.laba.hw3.model.route.Review;
import com.solvd.laba.hw3.model.route.TransportOrder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.util.Scanner;

public class ReviewMenu {
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    public static void addReview(Scanner scanner, TransportOrder transportOrder) {
        LOGGER.info("Review (rating from 1 to 5 stars): ");
        for (RatingType rating : RatingType.values()) {
            LOGGER.info((rating.ordinal() + 1) + ". " + rating.getDescription());
        }

        int rating = InputReader.readStarRating(scanner);
        RatingType selectedRating = null;
        try {
            selectedRating = RatingType.getByOption(rating);
        } catch (InvalidStarRatingException e) {
            LOGGER.info("Invalid rating option. Defaulting to EXCELLENT.");
            selectedRating = RatingType.EXCELLENT;
        }

        LOGGER.info("Review (comment): ");
        String content = scanner.next();
        selectedRating.displayMessage();
        transportOrder.setReview(new Review(selectedRating, content));
    }

}
