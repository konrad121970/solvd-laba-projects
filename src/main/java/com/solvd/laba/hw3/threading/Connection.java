package com.solvd.laba.hw3.threading;

public class Connection {
    private static int number = 0;

    public static int getNumber() {
        return number;
    }

    public static synchronized void incrementNumber() {
        System.out.println("Old Value: " + number);
        for (int i = 0; i < 10000; i++) {
            number++;
        }
        System.out.println("New Value: " + number);
    }
}
