package com.algdat.algorithms.sorting;

import java.util.ArrayList;
import java.util.List;

import com.algdat.interfaces.SortingAlgorithm;
import com.algdat.utils.SortingTest;

public class BucketSort implements SortingAlgorithm {

    @Override
    public int[] sort(int[] array) {
        int numBuckets = array.length;
        int max = 0;
        List<List<Integer>> buckets = new ArrayList<>();

        // Find max element
        for (int num : array) {
            max = Integer.max(max, num);
        }

        // Create buckets
        for (int i = 0; i < numBuckets; i++) {
            buckets.add(new ArrayList<>());
        }

        // Put elements in bucket
        for (int i = 0; i < array.length; i++) {
            buckets.get(((numBuckets - 1) * array[i]) / max).add(array[i]);
        }

        // Insertion sort each bucket
        for (int i = 0; i < numBuckets; i++) {
            insertionSort(buckets.get(i));
        }

        // Concat buckets
        int arrIndex = 0;
        for (List<Integer> bucket : buckets) {
            for (int num : bucket) {
                array[arrIndex] = num;
                arrIndex++;
            }
        }
        return array;
    }

    public void insertionSort(List<Integer> bucket) {
        for (int i = 1; i < bucket.size(); i++) {
            for (int j = i; j > 0 && bucket.get(j) < bucket.get(j-1); j--) {
                int temp = bucket.get(j);
                bucket.set(j, bucket.get(j-1));
                bucket.set(j-1, temp);
            }
        }
    }

    @Override
    public String algorithmName() {
        return "Bucket sort";
    }

    public static void main(String[] args) {
        SortingAlgorithm bucketSort = new BucketSort();
        SortingTest test = new SortingTest(100, 10, 0, 100);
        test.testAlgorithm(bucketSort);
    }

}
