package com.algdat.algorithms.strings;

public class WagnerFischerBottomUp {
    public static int findEditDistance(String s1, String s2) {
        int[][] matrix = new int[s1.length()+1][s2.length() + 1];

        for (int i = 0; i < matrix.length; i++) {
            matrix[i][0] = i;
        }

        for (int j = 0; j < matrix[0].length; j++) {
            matrix[0][j] = j;
        }

        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix.length; j++) {
                if (s1.charAt(i-1) == s2.charAt(j-1)) {
                    matrix[i][j] = matrix[i-1][j-1];
                } else {
                    matrix[i][j] = 
                        Math.min(matrix[i-1][j-1], Math.min(matrix[i-1][j], matrix[i][j-1])) + 1;
                }
            }
        }


        System.out.println(printMatrix(matrix));



        return matrix[s1.length()][s2.length()];
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
    }
}
