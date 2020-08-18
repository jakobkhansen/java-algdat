package com.algdat.algorithms.sorting;

import com.algdat.interfaces.SortingAlgorithm;
import com.algdat.utils.SortingTest;

public class SelectionSort implements SortingAlgorithm {

    @Override
    public int[] sort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int smallestIndex = i;
            for (int j = i; j < array.length; j++) {
                smallestIndex = array[j] < array[smallestIndex] ? j : smallestIndex;
            }
            swap(array, i, smallestIndex);
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
        return "Selection sort";
    }

    // Test
    public static void main(String[] args) {
        SortingTest test = new SortingTest(100, 10, 0, 100);
        SelectionSort selectionSort = new SelectionSort();

        test.testAlgorithm(selectionSort);
    }
}
