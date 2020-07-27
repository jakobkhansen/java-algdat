package com.algdat.algorithms.sorting;

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

}
