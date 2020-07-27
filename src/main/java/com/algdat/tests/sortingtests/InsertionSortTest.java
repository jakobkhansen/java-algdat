package com.algdat.tests.sortingtests;

import com.algdat.algorithms.sorting.InsertionSort;

class InsertionSortTest {
    public static void main(String[] args) {
        SortingTest test = new SortingTest(100, 10, 0, 100);
        InsertionSort insertionSort = new InsertionSort();
        test.testAlgorithm(insertionSort);
    }
}
