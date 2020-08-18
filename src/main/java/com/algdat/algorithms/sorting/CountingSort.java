package com.algdat.algorithms.sorting;

import com.algdat.interfaces.SortingAlgorithm;
import com.algdat.utils.SortingTest;

public class CountingSort implements SortingAlgorithm {

    @Override
    public int[] sort(int[] array) {
        int[] count = new int[512];
        int[] sorted = new int[array.length];

        for (int num : array) {
            count[num]++;
        }

        int sortedIndex = 0;
        for (int i = 0; i < count.length; i++) {
            for (int j = 0; j < count[i]; j++) {
                sorted[sortedIndex] = i;
                sortedIndex++;
            }
        }

        return sorted;
    }

    @Override
    public String algorithmName() {
        return "Counting sort";
    }
    
    // Test
    public static void main(String[] args) {
        CountingSort countingSort = new CountingSort();
        SortingTest test = new SortingTest(100, 15, 0, 100);
        test.testAlgorithm(countingSort);
    }

}
