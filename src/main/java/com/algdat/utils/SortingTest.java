package com.algdat.utils;


import java.util.Arrays;
import java.util.Random;

import com.algdat.interfaces.SortingAlgorithm;

public class SortingTest {

    private int numTests;
    private int arrayMinValue;
    private int arrayMaxValue;
    private int arraySize;

    public SortingTest(int numTests, int arraySize, int arrayMinValue, int arrayMaxValue) {
        this.numTests = numTests;
        this.arrayMinValue = arrayMinValue;
        this.arrayMaxValue = arrayMaxValue;
        this.arraySize = arraySize;
    }

    // Run tests and outputs result to sysout
    public void testAlgorithm(SortingAlgorithm algorithm) {
        System.out.println("--- " + algorithm.algorithmName() + " algorithm test ---");
        int passCounter = 0;
        for (int i = 0; i < numTests; i++) {
            int[] arrayToSort = generateArray();

            String originalArrayString = arrayString(arrayToSort);

            boolean testPassed = runTest(algorithm, arrayToSort);

            String sortedArrayString = arrayString(arrayToSort);

            String passedString = "";
            if (testPassed) {
                passCounter++;
                passedString = "[\u001B[32m✓\u001B[0m]";
            } else {
                passedString = "[\u001B[31mX\u001B[0m]";
            }
            System.out.println(String.format("%3d: %s %" + (arraySize*2 + 15) + "s -> %s", (i+1), passedString, originalArrayString, sortedArrayString));
        }
        System.out.println("Passed " + passCounter + " tests of " + numTests + ".");
    }

    boolean runTest(SortingAlgorithm algorithm, int[] arrayToSort) {
        int[] arrayCopy = Arrays.copyOf(arrayToSort, arrayToSort.length);
        arrayToSort = algorithm.sort(arrayToSort);

        return isSorted(arrayCopy, arrayToSort);
    }

    int[] generateArray() {
        int[] array = new int[arraySize];
        for (int i = 0; i < arraySize; i++) {
            array[i] = generateNumber();
        }

        return array;
    }

    int generateNumber() {
        Random generator = new Random();
        return generator.nextInt(arrayMaxValue - arrayMinValue) + arrayMinValue;
    }

    boolean isSorted(int[] original, int[] sorted) {
        int[] originalCopy = Arrays.copyOf(original, original.length);
        Arrays.sort(originalCopy);

        return Arrays.equals(originalCopy, sorted);
    }

    String arrayString(int[] array) {
        String retString = "[ ";
        for (int i = 0; i < array.length; i++) {
            retString += array[i] + " "; 
        }
        retString += "]";

        return retString;
    }
}
