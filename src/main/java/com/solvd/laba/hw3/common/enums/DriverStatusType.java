package com.solvd.laba.hw3.common.enums;

public enum DriverStatusType {
    AVAILABLE("Available"),
    UNAVAILABLE("Unavailable"),
    ON_BREAK("On Break");

    private final String status;

    DriverStatusType(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
