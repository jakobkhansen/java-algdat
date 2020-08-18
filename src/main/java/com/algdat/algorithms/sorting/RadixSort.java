package com.algdat.algorithms.sorting;

import java.util.ArrayList;
import java.util.List;

import com.algdat.interfaces.SortingAlgorithm;
import com.algdat.utils.SortingTest;

public class RadixSort implements SortingAlgorithm {

    @Override
    public int[] sort(int[] array) {
        int maxDigits = 0;
        for (int num : array) {
            if (Math.log10(num) > maxDigits) {
                maxDigits = (int) Math.log10(num) + 1;
            }
        }

        for (int i = 0; i < maxDigits; i++) {
            countingSort(array, i);
        }


        return array;
    }

    public void countingSort(int[] array, int digit) {
        List<List<Integer>> count = new ArrayList<List<Integer>>();

        for (int i = 0; i < 10; i++) {
            count.add(new ArrayList<>());
        }

        for (int num : array) {
            int currentDigit = (int) Math.floor(num / Math.pow(10, digit)) % 10;
            count.get(currentDigit).add(num);
        }

        int arrayIndex = 0;
        for (List<Integer> bucket : count) {
            for (int num : bucket) {
                array[arrayIndex] = num;
                arrayIndex++;
            }
        }
    }

    @Override
    public String algorithmName() {
        return "Radix sort";
    }

    public static void main(String[] args) {
        SortingAlgorithm radixSort = new RadixSort();
        SortingTest test = new SortingTest(100, 10, 0, 1000);

        test.testAlgorithm(radixSort);
    }

}
