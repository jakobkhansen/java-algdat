package com.algdat.tests.sortingtests;

import com.algdat.algorithms.sorting.SelectionSort;

class SelectionSortTest {
    public static void main(String[] args) {
        SortingTest test = new SortingTest(100, 10, 0, 100);
        SelectionSort selectionSort = new SelectionSort();

        test.testAlgorithm(selectionSort);
    }
}
