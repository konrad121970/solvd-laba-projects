package com.solvd.laba.hw3.threading;

public class Connection {
    public void doWork1() {
        System.out.println("Performing Work1");
    }

    public void doWork2DependendOnWork1() {
        System.out.println("Performing Work2");
    }
}
