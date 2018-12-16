package com.BucketSort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Main {

    private static int powOfTwo;
    private static int maxRandomValue;
    private static int arraySize;
    private static int binaryShift;
    private static int sortCounter;
    private static int[] array;
    private static ArrayList<ArrayList<Integer>> tempArray;

    public static void main(String[] args) {
        start();
    }

    private static void start(){
        setupSettings();
        initArrays();

        int counter = getCount();
        for (int i = 0; i < counter; i++) {
            sortArray();
            System.out.println("Iteration: " + i + " Array: " + Arrays.toString(array));
        }
    }

    private static void setupSettings(){
        powOfTwo = 4;
        maxRandomValue = 10000;
        arraySize = 20;
        binaryShift = getBinaryShift();
        sortCounter = 0;
        array = new int[arraySize];
        tempArray = new ArrayList<>();
    }

    private static void initArrays() {
        Random random = new Random();

        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(maxRandomValue);
        }

        System.out.println("Array: " + Arrays.toString(array));

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
                binValue.length() / binaryShift + 1:
                binValue.length() / binaryShift;
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
