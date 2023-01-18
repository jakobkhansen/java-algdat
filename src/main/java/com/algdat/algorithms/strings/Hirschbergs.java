package com.algdat.algorithms.strings;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Hirschbergs
 */
public class Hirschbergs {

    String s1, s2;

    class Pair {
        public int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Point(" + x + ", " + y + ")";
        }
    }

    public Hirschbergs(String s1, String s2) {
        this.s1 = s1;
        this.s2 = s2;
    }

    public void putCache(HashMap<Integer, HashMap<Integer, Integer>> cache, int i, int j, int value) {
        if (!cache.containsKey(i)) {
            cache.put(i, new HashMap<>());
        }
        cache.get(i).put(j, value);
    }

    public boolean containsCache(HashMap<Integer, HashMap<Integer, Integer>> cache, int i, int j) {
        return cache.containsKey(i) && cache.get(i).containsKey(j);
    }

    public int getCache(HashMap<Integer, HashMap<Integer, Integer>> cache, int i, int j) {
        return cache.get(i).get(j);
    }

    public List<Pair> hirschbergs(int row_start, int row_end, int column_start, int column_end) {

        // I think for these we need to add every node from where we are back to 0,0
        System.out.println();
        System.out.println("row_start " + row_start + " row_end " + row_end + " column_start " + column_start
                + " column_end " + column_end);
        int rowLength = (row_end - row_start);
        int columnLength = (column_end - column_start);
        System.out.println("row length: " + rowLength);
        System.out.println("column length: " + columnLength);
        if (rowLength == 0) {
            System.out.println("Terminated");
            return new ArrayList<>();
        } else if (columnLength == 0) {
            // Delete
            System.out.println("Terminated");
            return new ArrayList<>();
        } else if (rowLength == 1 || columnLength == 1) {
            // Do something
            System.out.println("Terminated");
            return new ArrayList<>();
        }

        int mid = (row_start + row_end) / 2;
        System.out.println("Mid: " + mid);
        int[] scoreL = needlemanwunsch(row_start, mid + 1, column_start, column_end);
        int[] scoreR = needlemanwunsch_reverse(mid, row_end, column_start, column_end);

        // for (int i = 0; i < scoreL.length; i++) {
        // System.out.println("scoreL[" + i + "] = " + scoreL[i]);
        // }
        // System.out.println();
        // for (int i = 0; i < scoreR.length; i++) {
        // System.out.println("scoreR[" + i + "] = " + scoreR[i]);
        // }

        int lowest = Integer.MAX_VALUE;
        int lowest_index = -1;
        for (int i = 0; i < scoreL.length; i++) {
            int score = scoreL[i] + scoreR[i];
            if (score < lowest) {
                lowest = score;
                lowest_index = i;
            }
        }
        lowest_index = column_start + lowest_index;
        Pair newPair = new Pair(mid, lowest_index);
        System.out.println("New pair: " + newPair);
        List<Pair> result = new ArrayList<>();
        List<Pair> left = hirschbergs(row_start, mid, column_start, lowest_index);
        result.addAll(left);

        result.add(new Pair(mid, lowest_index));

        List<Pair> right = hirschbergs(mid, row_end, lowest_index, column_end);
        result.addAll(right);

        return result;
    }

    public int[] needlemanwunsch(int row_start, int row_end, int column_start, int column_end) {
        HashMap<Integer, HashMap<Integer, Integer>> cache = new HashMap<>();
        for (int i = column_start; i < column_end; i++) {
            putCache(cache, 0, i, i - column_start);
        }
        for (int i = row_start + 1; i < row_end; i++) {
            putCache(cache, 1, column_start, getCache(cache, 0, column_start) + 1);
            for (int j = column_start + 1; j < column_end; j++) {

                int subVal = getCache(cache, 0, j - 1);
                subVal -= s1.charAt(i - 1) == s2.charAt(j - 1) ? 1 : 0;

                int delVal = getCache(cache, 0, j);
                int insertVal = getCache(cache, 1, j - 1);

                putCache(cache, 1, j, Math.min(subVal, Math.min(delVal, insertVal)) + 1);
            }
            for (int k = column_start; k < column_end; k++) {
                putCache(cache, 0, k, getCache(cache, 1, k));
            }
        }
        int[] lastRow = new int[column_end - column_start];
        for (int i = 0; i < column_end - column_start; i++) {
            lastRow[i] = getCache(cache, 1, column_start + i);
        }
        return lastRow;
    }

    public int[] needlemanwunsch_reverse(int row_start, int row_end, int column_start, int column_end) {
        HashMap<Integer, HashMap<Integer, Integer>> cache = new HashMap<>();
        for (int i = column_end - 1; i >= column_start; i--) {
            putCache(cache, 0, i, column_end - i - 1);
            // System.out.println("First row " + 0 + " " + i + ": " + getCache(cache, 0,
            // i));
        }
        for (int i = row_end - 1; i >= row_start; i--) {
            putCache(cache, 1, column_end - 1, getCache(cache, 0, column_end - 1) + 1);
            for (int j = column_end - 2; j >= column_start; j--) {

                int subVal = getCache(cache, 0, j + 1);
                subVal -= s1.charAt(i) == s2.charAt(j) ? 1 : 0;

                int delVal = getCache(cache, 0, j);
                int insertVal = getCache(cache, 1, j + 1);

                putCache(cache, 1, j, Math.min(subVal, Math.min(delVal, insertVal)) + 1);
                // System.out.println("put cache: " + 1 + " " + j + ": " + getCache(cache, 1,
                // j));
            }
            for (int k = column_end - 1; k >= column_start; k--) {
                putCache(cache, 0, k, getCache(cache, 1, k));
            }
        }
        int[] lastRow = new int[column_end - column_start];
        for (int i = 0; i < column_end - column_start; i++) {
            lastRow[i] = getCache(cache, 1, column_start + i);
        }
        return lastRow;
    }

    public static void main(String[] args) {
        String s1 = "GATCGTA";
        String s2 = "GACGGGA";
        Hirschbergs hirschbergs = new Hirschbergs(s1, s2);
        hirschbergs.hirschbergs(0, s1.length(), 0, s2.length());
    }
}
