package com.solvd.laba.hw3.interfaces;

public interface Transportable {
    void move(String source, String destination);

    Double getTimeOfArrival(Double distanceToGo);
}
