package com.solvd.laba.sortingAlgorithm;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void selectionSort(Integer[] array) {
        for (int i = 0; i < array.length - 1; i++) { // Iterate over each element in the array
            int minimal = i; // Set the minimal element to i
            for (int j = i + 1; j < array.length; j++) { // Start iterating from the adjacent element
                if (array[minimal] > array[j]) { // If element at index minimal is greater than element at index j
                    minimal = j; // Assign j to minimal
                }
            }
            int temp = array[i]; // Save element at index i to temporary variable
            array[i] = array[minimal]; // Replace with lower value
            array[minimal] = temp; // Higher goes where the array[minimal] was
        }
    }

    public static void bubbleSort(Integer[] array) {
        for (int i = 0; i < array.length - 1; i++) { // Count already sorted numbers
            for (int j = 0; j < array.length - i - 1; j++) { // Iterate over array
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {

        if (args.length < 2) {
            LOGGER.info("Please provide at least two parameters!");
            return;
        }

        Integer[] array = new Integer[args.length];
        for (int i = 0; i < args.length; i++) {
            array[i] = Integer.parseInt(args[i]);
        }

        LOGGER.info("### BEFORE SORTING ###");
        for (Integer item : array) {
            LOGGER.info(item + " ");
        }
        
        LOGGER.info("### AFTER SORTING ###");
        selectionSort(array); // bubbleSort(array)
        for (Integer item : array) {
            LOGGER.info(item + " ");
        }

    }
}