package com.solvd.laba.sortingAlgorithm;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserInfo {
    private static final Logger LOGGER = LogManager.getLogger(UserInfo.class);

    public static void main(String[] args) {
        if (args.length != 3) {
            LOGGER.info("You have to specify three parameters: Name, Surname and Age.");
            return;
        }

        String name = args[0];
        String surname = args[1];
        int age = Integer.parseInt(args[2]);

        LOGGER.info("User Information:");
        LOGGER.info("Name: " + name);
        LOGGER.info("Surname: " + surname);
        LOGGER.info("Age: " + age);
    }
}