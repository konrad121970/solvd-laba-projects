package com.solvd.laba.hw3.enums;

public enum TaxiStandardType {
    STANDARD("Standard Taxi", 1.0),
    LUXURY("Luxury Taxi", 2.0),
    ECO_FRIENDLY("Eco-Friendly Taxi", 1.5);

    private final String categoryName;
    private final double fareMultiplier;

    TaxiStandardType(String categoryName, double fareMultiplier) {
        this.categoryName = categoryName;
        this.fareMultiplier = fareMultiplier;
    }

    public static TaxiStandardType getByOption(int option) {
        switch (option) {
            case 1:
                return STANDARD;

            case 2:
                return ECO_FRIENDLY;

            case 3:
                return LUXURY;

            default:
                throw new IllegalArgumentException("Invalid taxi standard option: " + option);
        }
    }

    public String getCategoryName() {
        return categoryName;
    }

    public double getFareMultiplier() {
        return fareMultiplier;
    }


}
