package org.example;

public class Main {

    public static void selectionSort(int array[]){
        for(int i = 0; i < array.length - 1; i++){ // Iterate over each element in the array
            int minimal = i; // Set the minimal element to i
            for(int j = i + 1; j < array.length; j++){ // Start iterating from the adjacent element
                if(array[minimal] > array[j]){ // If element at index minimal is greater than element at index j
                    minimal = j; // Assign j to minimal
                }
            }
            int temp = array[i]; // Save element at index i to temporary variable
            array[i] = array[minimal]; // Replace with lower value
            array[minimal] = temp; // Higher goes where the array[minimal] was
        }
    }

//    public static void bubbleSort(int array[]){
//
//    }

    public static void main(String[] args) {

        int array[] = {5, 6, 3, 2, 1, 7, 8, 9, 4};

        System.out.println("### BEFORE SORTING ###");
        for (int item :array) {
            System.out.print(item + " ");
        }

        System.out.println();
        System.out.println("### AFTER SORTING ###");
        selectionSort(array);
        for (int item :array) {
            System.out.print(item + " ");
        }

    }
}