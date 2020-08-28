package com.algdat.algorithms.strings;

import java.util.HashMap;

public class Horspool {
    public static int containsPattern(String text, String pattern) {
        HashMap<Character, Integer> table = badMatchTable(pattern);
        int n = text.length() - 1;
        int m = pattern.length() - 1;

        int index = 0;
        while (index <= n - m) {
            if (patternMatch(text, pattern, index)) {
                return index;
            }

            index += table.getOrDefault(text.charAt(index + pattern.length() - 1), pattern.length());
        }

        return -1;
    }


    public static HashMap<Character, Integer> badMatchTable(String pattern) {
        HashMap<Character, Integer> table = new HashMap<>();

        for (int i = 0; i < pattern.length() - 1; i++) {
            table.put(pattern.charAt(i), pattern.length() - i - 1);
        }

        return table;
    }

    public static boolean patternMatch(String text, String pattern, int index) {
        for (int i = index+pattern.length() - 1; i >= index; i--) {
            if (text.charAt(i) != pattern.charAt(i - index)) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        String text = "IAMANANTELOPE";
        String pattern = "ANA";
        System.out.println(containsPattern(text, pattern));
    }
}
