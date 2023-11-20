package com.solvd.laba.hw3.model.route;


import com.solvd.laba.hw3.model.exceptions.InvalidStarRatingException;
import com.solvd.laba.hw3.model.interfaces.Displayable;
import com.solvd.laba.hw3.model.interfaces.Reviewable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

public class Review implements Reviewable, Displayable {
    private static final Logger LOGGER = LogManager.getLogger(Review.class);
    private Integer starRating;
    private String content;

    public Review() {
    }

    public Review(Integer starRating, String content) {
        if (starRating != null && starRating >= 1 && starRating <= 5) {
            this.starRating = starRating;
        } else {
            throw new InvalidStarRatingException("Star rating should be between 1 and 6 stars.");
        }
        this.content = content;
    }

    public Integer getStarRating() {
        return starRating;
    }

    public void setStarRating(Integer starRating) {
        if (starRating != null && starRating >= 1 && starRating <= 5) {
            this.starRating = starRating;
        } else {
            throw new InvalidStarRatingException("Star rating should be between 1 and 6 stars.");
        }
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
                "numberOfStars=" + starRating +
                ", content='" + content + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return Objects.equals(starRating, review.starRating) && Objects.equals(content, review.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(starRating, content);
    }

    @Override
    public void writeReview(Integer starRating, String content) {
        this.starRating = starRating;
        this.content = content;
    }

    @Override
    public String getReview() {
        return "Star rating: " + this.starRating + "out of 5. Content of review:\n" + this.content;
    }


    // TODO:
    @Override
    public void display() {
        LOGGER.info("Star rating:" + this.starRating);
    }

    @Override
    public void showDetails() {
        LOGGER.info("Star rating: " + this.starRating + "out of 5. Content of review:\n" + this.content);
    }
}


