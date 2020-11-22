package com.algdat.algorithms.strings;

public class WagnerFischerTopDown {

    static int[][] cache;

    public static int findEditDistance(String s1, String s2) {
        cache = new int[s1.length() + 1][s2.length() + 1];

        for (int i = 0; i < cache.length; i++) {
            cache[i][0] = i;
        }

        for (int i = 0; i < cache[0].length; i++) {
            cache[0][i] = i;
        }

        for (int i = 1; i < cache.length; i++) {
            for (int j = 1; j < cache[i].length; j++) {
                cache[i][j] = -1;
            }
        }


        return findEditDistance(s1, s2, s1.length(), s2.length());
    }

    public static int findEditDistance(String s1, String s2, int i, int j) {
        System.out.println("" + i);
        System.out.println("" + j);
        System.out.println("");
        if (cache[i][j] != -1) {
            return cache[i][j];
        }

        if (s1.charAt(i-1) == s2.charAt(j-1)) {
            cache[i][j] = findEditDistance(s1, s2, i-1, j-1);
            return cache[i][j];
        }

        int substitute = findEditDistance(s1, s2, i-1, j-1);
        int add = findEditDistance(s1, s2, i-1, j);
        int delete = findEditDistance(s1, s2, i, j-1);

        cache[i][j] = Math.min(substitute, Math.min(add, delete)) + 1;

        return cache[i][j];
    }

    public static String printMatrix(int[][] matrix) {
        String ret = "[\n";

        for (int[] arr : matrix) {
            ret += "[ ";

            for (int num : arr) {
                ret += num + " ";
            }

            ret += "]\n";
        }
        return ret + "]";
    }

    public static void main(String[] args) { 
        String s1 = "LOGARI";
        String s2 = "ALGORI";

        System.out.println(findEditDistance(s1, s2));
        System.out.println(printMatrix(cache));
    }
}
