package com.solvd.laba.hw3.model.route;


import com.solvd.laba.hw3.model.interfaces.Reviewable;

import java.util.Objects;

public class Review implements Reviewable {
    private Integer starRating;
    private String content;

    public Review() {
    }

    public Review(Integer starRating, String content) {
        this.starRating = starRating;
        this.content = content;
    }

    public Integer getStarRating() {
        return starRating;
    }

    public void setStarRating(Integer starRating) {
        this.starRating = starRating;
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
}


