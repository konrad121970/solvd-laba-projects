package com.solvd.laba.sortingAlgorithm;

public class UserInfo {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("You have to specify three parameters: Name, Surname and Age.");
            return;
        }

        String name = args[0];
        String surname = args[1];
        int age = Integer.parseInt(args[2]);

        System.out.println("User Information:");
        System.out.println("Name: " + name);
        System.out.println("Surname: " + surname);
        System.out.println("Age: " + age);
    }
}