package com.solvd.laba.hw3.model.interfaces;

import com.solvd.laba.hw3.model.route.Location;

public interface Tranportable {
    void move(String source, String destination);
    Double getTimeOfArrival(Double distanceToGo);
}
