package com.solvd.laba.hw3.enums;

public enum LocationType {
    ROUTE_START("Start of Route"),
    ROUTE_END("End of Route"),
    INTERMEDIATE_STOP("Intermediate Stop");

    private final String description;


    LocationType(String description) {
        this.description = description;
    }


    public String getDescription() {
        return description;
    }


    public void displayInfo() {
        System.out.println("Location type: " + this.name());
        System.out.println("Description: " + description);
    }
}