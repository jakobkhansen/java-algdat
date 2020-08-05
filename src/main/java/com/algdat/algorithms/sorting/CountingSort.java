package com.algdat.algorithms.sorting;

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

}
