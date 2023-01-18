package com.algdat.algorithms.strings;

import java.util.HashMap;

public class WagnerFischerTopDown {
    static class Pair {
        public int x;
        public int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int hashCode() {
            return ((x + y) * (x + y + 1) / 2) + y;
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Pair)) {
                return false;
            }
            return this.x == ((Pair) obj).x && ((Pair) obj).y == this.y;
        }
    }

    static HashMap<Pair, Integer> minimalCache = new HashMap<>();

    public static int findEditDistance(String s1, String s2) {

        return findEditDistance(s1, s2, s1.length(), s2.length());
    }

    public static int getCache(Pair pair) {
        if (pair.x == 0) {
            return pair.y;
        }
        if (pair.y == 0) {
            return pair.x;
        }
        return minimalCache.get(pair);
    }

    public static boolean cacheContains(Pair pair) {
        return minimalCache.containsKey(pair) || pair.x == 0 || pair.y == 0;
    }

    public static int findEditDistance(String s1, String s2, int i, int j) {
        Pair pair = new Pair(i, j);
        if (cacheContains(pair)) {
            return getCache(pair);
        }

        if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
            minimalCache.put(pair, findEditDistance(s1, s2, i - 1, j - 1));
            return getCache(pair);
        }

        int substitute = findEditDistance(s1, s2, i - 1, j - 1);
        int add = findEditDistance(s1, s2, i - 1, j);
        int delete = findEditDistance(s1, s2, i, j - 1);

        minimalCache.put(pair, Math.min(substitute, Math.min(add, delete)) + 1);

        return getCache(pair);
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
        String s1 = "Pneumonoultramicroscopicsilicovolcanoconiosis";
        String s2 = "Pneumonoultramicroscopicsilicovolxanoconiosis";

        System.out.println(findEditDistance(s1, s2));
        System.out.println("here " + minimalCache.keySet().size());
    }
}
