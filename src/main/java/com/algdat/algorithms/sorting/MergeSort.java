package com.algdat.algorithms.sorting;

import com.algdat.interfaces.SortingAlgorithm;
import com.algdat.utils.SortingTest;

public class MergeSort implements SortingAlgorithm {

    @Override
    public int[] sort(int[] array) {
        mergeSort(array, 0, array.length-1);
        return array;
    }

    public void mergeSort(int[] array, int l, int r) {
        if (l < r) {
            int m = (l+r)/2;

            mergeSort(array, l, m);
            mergeSort(array, m+1, r);

            merge(array, l, m, r);
        }
    }

    public void merge(int[] array, int l, int m, int r) {
        int leftLength = m - l + 1;
        int rightLength = r - m;

        int[] left = new int[leftLength];
        int[] right = new int[rightLength];

        for (int i = 0; i < leftLength; i++) {
            left[i] = array[l+i];
        }
        
        for (int i = 0; i < rightLength; i++) {
            right[i] = array[m + 1 + i];
        }

        int leftIndex = 0;
        int rightIndex = 0;
        int arrayIndex = l;

        while (leftIndex < leftLength && rightIndex < rightLength) {
            if (left[leftIndex] <= right[rightIndex]) {
                array[arrayIndex] = left[leftIndex];
                leftIndex++;
            } else {
                array[arrayIndex] = right[rightIndex];
                rightIndex++;
            }
            arrayIndex++;
        }

        while (leftIndex < leftLength) {
            array[arrayIndex] = left[leftIndex];
            leftIndex++;
            arrayIndex++;
        }

        while (rightIndex < rightLength) {
            array[arrayIndex] = right[rightIndex];
            rightIndex++;
            arrayIndex++;
        }
    }

    @Override
    public String algorithmName() {
        return "Merge sort";
    }

    public static void main(String[] args) {
        SortingAlgorithm mergeSort = new MergeSort();
        SortingTest test = new SortingTest(100, 10, 0, 100);
        test.testAlgorithm(mergeSort);
    }

}
