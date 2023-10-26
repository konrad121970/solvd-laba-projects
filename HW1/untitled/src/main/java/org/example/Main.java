package org.example;

public class Main {

    public static void selectionSort(int array[]){
        for(int i = 0; i < array.length - 1; i++){
            int minimal = i;
            for(int j = i + 1; j < array.length; j++){
                if(array[minimal] > array[j]){
                    minimal = j;
                }
            }
            int temp = array[i];
            array[i] = array[minimal];
            array[minimal] = temp;
        }
    }

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