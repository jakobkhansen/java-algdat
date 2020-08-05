package com.algdat.algorithms.arrays;

public class BinarySearch {
    
    // Returns index that contains the value in a sorted array
    public static int binarySearch(int[] array, int value) {
        int left = 0;
        int right = array.length - 1;

        while (left <= right) {
            int mid = (left+right)/2;

            if (array[mid] < value) {
                left = mid + 1;
            } else if (array[mid] > value) {
                right = mid - 1;
            } else {
                return mid;
            }
        }

        return -1;
    }





    // Usage
    public static void main(String[] args) {
        int[] array = new int[]{1,3,5,8,10,13,20,30,42,61};

        System.out.println(binarySearch(array, 42));
    }
}
