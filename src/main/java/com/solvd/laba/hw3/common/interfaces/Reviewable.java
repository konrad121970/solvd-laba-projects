package com.solvd.laba.hw3.common.interfaces;

import com.solvd.laba.hw3.common.enums.RatingType;

public interface Reviewable {
    void writeReview(RatingType ratingType, String review);

    String getReview();
}
