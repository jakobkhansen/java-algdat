package com.algdat.algorithms.sorting;

import com.algdat.interfaces.SortingAlgorithm;
import com.algdat.utils.SortingTest;

public class InsertionSort implements SortingAlgorithm {

    @Override
    public int[] sort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            for (int j = i; j > 0 && array[j] < array[j-1]; j--) {
                swap(array, j, j-1);
            }
        }
        return array;
    }

    public void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    @Override
    public String algorithmName() {
        return "Insertion Sort";
    }

    // Test
    public static void main(String[] args) {
        SortingTest test = new SortingTest(100, 10, 0, 100);
        InsertionSort insertionSort = new InsertionSort();
        test.testAlgorithm(insertionSort);
    }

}
