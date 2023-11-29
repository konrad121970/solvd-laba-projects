package com.solvd.laba.hw3.enums;

public enum DriverStatusType {
    AVAILABLE("Available"),
    UNAVAILABLE("Unavailable"),
    ON_BREAK("On Break");

    private final String description;

    DriverStatusType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
