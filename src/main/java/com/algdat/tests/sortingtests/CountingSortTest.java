package com.algdat.tests.sortingtests;

import com.algdat.algorithms.sorting.CountingSort;

public class CountingSortTest {
    public static void main(String[] args) {
        CountingSort countingSort = new CountingSort();
        SortingTest test = new SortingTest(100, 15, 0, 100);
        test.testAlgorithm(countingSort);
    }
}
