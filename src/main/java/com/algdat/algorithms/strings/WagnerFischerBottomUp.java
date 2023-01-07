package com.algdat.algorithms.strings;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class WagnerFischerBottomUp {
    enum OperationType {
        SUBSTITUTE,
        INSERT,
        DELETE,
        NONE
    };

    static class EditOperation {
        private OperationType operationType;
        private int start, end;
        ArrayList<Character> chars = new ArrayList<>();

        public EditOperation(OperationType operationType, int start, int end) {
            this.operationType = operationType;
            this.start = start;
            this.end = end;
        }
    };

    public static int findEditDistance(String s1, String s2) {
        int[][] matrix = new int[s1.length() + 1][s2.length() + 1];

        for (int i = 0; i < matrix.length; i++) {
            matrix[i][0] = i;
        }

        for (int j = 0; j < matrix[0].length; j++) {
            matrix[0][j] = j;
        }

        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[i].length; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    matrix[i][j] = matrix[i - 1][j - 1];
                } else {
                    matrix[i][j] = Math.min(matrix[i - 1][j - 1], Math.min(matrix[i - 1][j], matrix[i][j - 1])) + 1;
                }
            }
        }

        System.out.println(printMatrix(matrix));

        getOperations(s1, s2, matrix);

        return matrix[s1.length()][s2.length()];
    }

    public static void getOperations(String s1, String s2, int[][] matrix) {
        int x = matrix.length - 1;
        int y = matrix[x].length - 1;

        List<EditOperation> operations = new ArrayList<>();

        EditOperation currentOperation = null;
        OperationType currentOperationType = OperationType.NONE;
        int lastOperationIndex = -1;

        while (x > 0 && y > 0) {
            int subValue = matrix[x - 1][y - 1];
            int delValue = matrix[x - 1][y];
            int insertValue = matrix[x][y - 1];

            if (subValue <= delValue && subValue <= insertValue) {
                if (subValue != matrix[x][y]) {
                    // System.out
                    // .println("Substitute " + s1.charAt(x - 1) + " with " + s2.charAt(y - 1) + "
                    // at " + (x - 1));

                    // Continue last operation or create new
                    if (currentOperationType == OperationType.SUBSTITUTE && lastOperationIndex == x) {
                        currentOperation.chars.add(0, s2.charAt(y - 1));
                        currentOperation.end++;
                        lastOperationIndex = x - 1;
                    } else {
                        currentOperation = new EditOperation(OperationType.SUBSTITUTE, x - 1, x - 1);
                        currentOperation.chars.add(0, s2.charAt(y - 1));
                        currentOperationType = OperationType.SUBSTITUTE;
                        lastOperationIndex = x - 1;
                        operations.add(0, currentOperation);
                    }
                }
                x--;
                y--;
            } else if (insertValue <= delValue && insertValue <= subValue) {
                // System.out.println("Insert " + s2.charAt(y - 1) + " at " + (x));

                // Continue last operation or create new
                if (currentOperationType == OperationType.INSERT && lastOperationIndex == x) {
                    currentOperation.chars.add(0, s2.charAt(y - 1));
                    currentOperation.end++;
                } else {
                    currentOperation = new EditOperation(OperationType.INSERT, x, x);
                    currentOperation.chars.add(0, s2.charAt(y - 1));
                    currentOperationType = OperationType.INSERT;
                    lastOperationIndex = x;
                    operations.add(0, currentOperation);
                }
                y--;
            } else {
                // System.out.println("Delete " + s1.charAt(x - 1) + " at " + (x - 1));
                if (currentOperationType == OperationType.DELETE && lastOperationIndex == x) {
                    currentOperation.chars.add(0, s1.charAt(x - 1));
                    currentOperation.start--;
                    lastOperationIndex = x - 1;
                } else {
                    currentOperation = new EditOperation(OperationType.DELETE, x - 1, x - 1);
                    currentOperation.chars.add(0, s1.charAt(x - 1));
                    currentOperationType = OperationType.DELETE;
                    lastOperationIndex = x - 1;
                    operations.add(0, currentOperation);
                }
                x--;
            }

        }

        for (EditOperation op : operations) {
            System.out.println("Operation:");
            System.out.println(op.operationType);
            System.out.println("start=" + op.start + ", end=" + op.end);
            for (char c : op.chars) {
                System.out.print(c);
            }
            System.out.println();
        }
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

    public static String printChars(List<Character> chars) {
        String out = "";
        for (char c : chars) {
            out += c;
        }
        return out;
    }

    public static void main(String[] args) {
        String s1 = "DEMOCRAT";
        String s2 = "REPUBLICAN";
        System.out.println("Input:  " + s1);
        System.out.println("Output: " + s2);

        System.out.println(findEditDistance(s1, s2));
    }
}
