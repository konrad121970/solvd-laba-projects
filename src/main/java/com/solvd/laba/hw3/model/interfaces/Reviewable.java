package com.solvd.laba.hw3.model.interfaces;

public interface Reviewable {
    void writeReview(Integer starRating, String Review);

    String getReview();
}
