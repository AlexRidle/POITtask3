package com.BucketSort;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    private static int powOfTwo = 4;
    private static int binaryShift = getBinaryShift();
    private static int sortCounter = 0;
    private static int[] array = {1000, 1, 65, 758, 912, 850, 8210, 27, 239, 72, 7, 5, 223};
    private static ArrayList<ArrayList<Integer>> tempArray = new ArrayList<>();

    public static void main(String[] args) {

        //initialize size of temp array
        for (int i = 0; i < powOfTwo; i++) {
            tempArray.add(new ArrayList<>());
        }

        //sort array for n times
        int counter = getCount();
        System.out.println("Iteration: -" + " Array: " + Arrays.toString(array));
        for (int i = 0; i < counter; i++) {
            sortArray();
            System.out.println("Iteration: " + i + " Array: " + Arrays.toString(array));
        }
    }

    private static int getBinaryShift() {
        String binValue = Integer.toBinaryString(powOfTwo-1);
        System.out.println("bin: " + binValue.length());
        return binValue.length();
    }

    private static int getCount() {
        String binValue = Integer.toBinaryString(getMaxOfArray(array));
        System.out.println(binValue);
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
        System.out.println(value);
        return value;
    }

    private static void sortArray() {
        //add current num from array into temp array by last bit's
        for (int num : array) {
            tempArray.get((num >> sortCounter) & powOfTwo - 1).add(num);
        }

        //add value by value in main array
        int arrayPos = 0;
        for(ArrayList<Integer> arrayList : tempArray){
            if(!arrayList.isEmpty()){
                for (int value : arrayList){
                    array[arrayPos] = value;
                    arrayPos++;
                }
                arrayList.clear();
            }
        }

        sortCounter+=binaryShift;
    }
}
