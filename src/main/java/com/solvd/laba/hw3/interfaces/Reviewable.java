package com.solvd.laba.hw3.interfaces;

import com.solvd.laba.hw3.enums.RatingType;

public interface Reviewable {
    void writeReview(RatingType ratingType, String Review);

    String getReview();
}
