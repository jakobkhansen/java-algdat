package com.algdat.algorithms.sorting;

import com.algdat.interfaces.SortingAlgorithm;
import com.algdat.utils.SortingTest;

public class HeapSort implements SortingAlgorithm {

	@Override
	public int[] sort(int[] array) {
        for (int i = array.length / 2 - 1; i >= 0; i--) {
            heapify(array, array.length, i);
        }

        for (int i = array.length - 1; i >= 0; i--) {
            int temp = array[0];
            array[0] = array[i];
            array[i] = temp;

            heapify(array, i, 0);
        }

        return array;
	}

    public void heapify(int[] array, int size, int root) {
        int largest = root;
        int left = getLeftChild(root);
        int right = getRightChild(root);

        if (left < size && array[left] > array[largest]) {
            largest = left;
        }

        if (right < size && array[right] > array[largest]) {
            largest = right;
        }

        if (largest != root) {
            int temp = array[root];
            array[root] = array[largest];
            array[largest] = temp;

            heapify(array, size, largest);
        }
    }


    public int getLeftChild(int index) {
        return index * 2 + 1;
    }

    public int getRightChild(int index) {
        return index * 2 + 2;
    }


	@Override
	public String algorithmName() {
        return "Heap sort";
	}


    public static void main(String[] args) {
        SortingAlgorithm heapSort = new HeapSort();
        SortingTest test = new SortingTest(100, 10, 0, 100);

        test.testAlgorithm(heapSort);
    }
}
