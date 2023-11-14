package com.solvd.laba.hw3.model.interfaces;

public interface Transportable {
    void move(String source, String destination);

    Double getTimeOfArrival(Double distanceToGo);
}
