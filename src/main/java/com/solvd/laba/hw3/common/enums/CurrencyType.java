package com.solvd.laba.hw3.common.enums;

public enum CurrencyType {

    USD("US Dollar", "$"),
    EUR("Euro", "€"),
    GBP("British Pound", "£");

    private final String name;
    private final String symbol;

    CurrencyType(String name, String symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    public static CurrencyType getByOption(int option) {
        switch (option) {
            case 1:
                return USD;

            case 2:
                return EUR;

            case 3:
                return GBP;

            default:
                throw new IllegalArgumentException("Invalid currency option: " + option);
        }
    }

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }
}
