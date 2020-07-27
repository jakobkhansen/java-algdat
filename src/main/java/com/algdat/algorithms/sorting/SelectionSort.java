package com.algdat.algorithms.sorting;

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

}
