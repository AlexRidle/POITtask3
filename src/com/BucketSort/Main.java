package com.BucketSort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Main {

    //settings
    private static int powOfTwo = 4;
    private static int maxRandomValue = 10000;
    private static int arraySize = 20;

    //values for sort method
    private static int binaryShift = getBinaryShift();
    private static int sortCounter = 0;

    //array declaration
    private static int[] array = new int[arraySize];
    private static ArrayList<ArrayList<Integer>> tempArray = new ArrayList<>();

    public static void main(String[] args) {
        initArrays();
        int counter = getCount();
        System.out.println("Array: " + Arrays.toString(array));

        for (int i = 0; i < counter; i++) {
            sortArray();
            System.out.println("Iteration: " + i + " Array: " + Arrays.toString(array));
        }
    }

    private static void initArrays() {
        Random random = new Random();

        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(maxRandomValue);
        }

        for (int i = 0; i < powOfTwo; i++) {
            tempArray.add(new ArrayList<>());
        }
    }

    private static int getBinaryShift() {
        String binValue = Integer.toBinaryString(powOfTwo - 1);
        return binValue.length();
    }

    private static int getCount() {
        String binValue = Integer.toBinaryString(getMaxOfArray(array));
        return binValue.length() % binaryShift != 0 ?
                binValue.length() / binaryShift :
                binValue.length() / binaryShift + 1;
    }

    private static int getMaxOfArray(int[] array) {
        int value = array[0];
        for (int num : array) {
            if (num > value) {
                value = num;
            }
        }
        return value;
    }

    private static void sortArray() {
        //add current num from array into temp array by last bit's
        for (int num : array) {
            tempArray.get((num >> sortCounter) & powOfTwo - 1).add(num);
        }

        //add value by value in main array
        int arrayPos = 0;
        for (ArrayList<Integer> arrayList : tempArray) {
            if (!arrayList.isEmpty()) {
                for (int value : arrayList) {
                    array[arrayPos] = value;
                    arrayPos++;
                }
                arrayList.clear();
            }
        }

        sortCounter += binaryShift;
    }
}
