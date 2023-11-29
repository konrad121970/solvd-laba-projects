package com.solvd.laba.hw3.model.route;


import com.solvd.laba.hw3.enums.RatingType;
import com.solvd.laba.hw3.exceptions.InvalidStarRatingException;
import com.solvd.laba.hw3.interfaces.Displayable;
import com.solvd.laba.hw3.interfaces.Reviewable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

public class Review implements Reviewable, Displayable {
    private static final Logger LOGGER = LogManager.getLogger(Review.class);
    private RatingType ratingType;
    private String content;

    public Review() {
    }

    public Review(RatingType ratingType, String content) {
        if (ratingType != null) {
            this.ratingType = ratingType;
        } else {
            throw new InvalidStarRatingException("Star rating should be between 1 and 6 stars.");
        }
        this.content = content;
    }

    public RatingType getStarRating() {
        return ratingType;
    }

    public void setStarRating(RatingType ratingType) {
        this.ratingType = ratingType;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Review{" +
                "numberOfStars=" + ratingType +
                ", content='" + content + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return Objects.equals(ratingType, review.ratingType) && Objects.equals(content, review.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ratingType, content);
    }

    @Override
    public void writeReview(RatingType ratingType, String content) {
        this.ratingType = ratingType;
        this.content = content;
    }

    @Override
    public String getReview() {
        return "Star rating: " + this.ratingType + "out of 5. Content of review:\n" + this.content;
    }


    // TODO:
    @Override
    public void display() {
        LOGGER.info("Star rating:" + this.ratingType);
    }

    @Override
    public void showDetails() {
        LOGGER.info("Star rating: " + this.ratingType + "out of 5. Content of review:\n" + this.content);
    }
}


