package com.algdat.algorithms.sorting;

import com.algdat.utils.SortingTest;

public class QuickSort implements SortingAlgorithm {

	@Override
	public int[] sort(int[] array) {

        quickSort(array, 0, array.length - 1);

		return array;
	}

    public void quickSort(int[] array, int l, int r) {
        if (l < r) {
            int pivot = array[l];
            int leftScan = l+1;
            int rightScan = r;

            while (leftScan <= rightScan) {
                while (leftScan <= rightScan && array[leftScan] <= pivot) {
                    leftScan++;
                }

                while (rightScan >= leftScan && array[rightScan] >= pivot) {
                    rightScan--;
                }

                if (leftScan < rightScan) {
                    swap(array, leftScan, rightScan);
                }
            }


            swap(array, l, rightScan);


            quickSort(array, l, rightScan - 1);

            quickSort(array, rightScan + 1, r);
        }
    }

    public void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public String arrToString(int[] array, int l, int r) {
        String ret = "[ ";
        for (int i = l; i <= r; i++) {
            ret += array[i] + " ";
        }
        return ret + "]";
    }

	@Override
	public String algorithmName() {
        return "Quick sort";
	}

    public static void main(String[] args) {
        SortingAlgorithm quickSort = new QuickSort();
        SortingTest test = new SortingTest(100, 10, 0, 100);
        test.testAlgorithm(quickSort);
    }
}
