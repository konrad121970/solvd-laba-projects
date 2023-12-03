package com.solvd.laba.hw3.common.interfaces;

public interface Transportable {
    void move(String source, String destination, Double distanceInKm);

    Double getTimeOfArrival(Double distanceToGo);
}
