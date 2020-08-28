package com.algdat.algorithms.strings;

public class NaivePatternMatch {
    public static int containsPattern(String text, String pattern) {
        int n = text.length();
        int m = pattern.length();

        for (int i = 0; i <= n - m; i++) {
            if (patternMatch(text, pattern, i)) {
                return i;
            }
        }

        return -1;
    }

    public static boolean patternMatch(String text, String pattern, int index) {
        for (int i = index; i < index+pattern.length(); i++) {
            if (text.charAt(i) != pattern.charAt(i - index)) {
                return false;
            }
        }

        return true;
    }



    public static void main(String[] args) {
        String text = "IAMANANTELOPE";
        String pattern = "IAM";
        System.out.println(containsPattern(text, pattern));
    }
}
