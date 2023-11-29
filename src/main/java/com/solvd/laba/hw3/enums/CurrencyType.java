package com.solvd.laba.hw3.enums;

public enum CurrencyType {
    USD("US Dollar") {
        @Override
        public String getSymbol() {
            return "$";
        }
    },
    EUR("Euro") {
        @Override
        public String getSymbol() {
            return "€";
        }
    },
    GBP("British Pound") {
        @Override
        public String getSymbol() {
            return "£";
        }
    };

    private final String name;

    CurrencyType(String name) {
        this.name = name;
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

    public abstract String getSymbol();
}
