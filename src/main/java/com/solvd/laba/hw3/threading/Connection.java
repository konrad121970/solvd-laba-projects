package com.solvd.laba.hw3.threading;

public class Connection {
    private static int number = 0;

    public static int getNumber() {
        return number;
    }

    /**
     * Method to increment number.
     * <p>
     * Access to this method must be static synchronized to
     * prevent race condition from happening.
     * Not static method (even synchronized) will allow
     * instances to use this method simultaneously.
     */
    public static synchronized void incrementNumber() {
        System.out.println("Old Value: " + number);
        for (int i = 0; i < 10000; i++) {
            number++;
        }
        System.out.println("New Value: " + number);
    }
}
