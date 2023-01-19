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

        @Override
        public String toString() {
            StringBuilder out = new StringBuilder("EditOperation(\n");
            out.append("position: " + this.start + "\n");
            out.append("Type: " + this.operationType + "\n");
            out.append("Chars: ");
            for (char i : this.chars) {
                out.append(i + " ");
            }
            out.append("\n");

            return out.toString();
        }

    };

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

    public List<Pair> getEditPositions() {
        List<Pair> positions = new ArrayList<>();

        positions.add(new Pair(0, 0));
        positions.addAll(hirschbergs_rec(0, s1.length(), 0, s2.length()));
        positions.add(new Pair(s1.length(), s2.length()));

        return positions;
    }

    private List<Pair> hirschbergs_rec(int row_start, int row_end, int column_start, int column_end) {

        // I think for these we need to add every node from where we are back to 0,0
        int rowLength = (row_end - row_start);
        int columnLength = (column_end - column_start);
        if (rowLength == 0) {
            List<Pair> pairs = new ArrayList<>();
            for (int i = columnLength - 1; i > 0; i--) {
                Pair newPair = new Pair(row_start, i + column_start);
                pairs.add(newPair);
            }
            return pairs;
        } else if (columnLength == 0) {
            List<Pair> pairs = new ArrayList<>();
            for (int i = rowLength - 1; i > 0; i--) {
                Pair newPair = new Pair(row_start + i, column_start);
                pairs.add(newPair);
            }
            return pairs;
        } else if (rowLength == 1 || columnLength == 1) {
            return wagner_fischer_small(row_start, row_end, column_start, column_end);
        }
        int mid = (row_start + row_end) / 2;
        int[] scoreL = needlemanwunsch_score(row_start, mid + 1, column_start, column_end);
        int[] scoreR = needlemanwunsch_score_reverse(mid, row_end, column_start, column_end);

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
        List<Pair> result = new ArrayList<>();
        List<Pair> left = hirschbergs_rec(row_start, mid, column_start, lowest_index);
        result.addAll(left);

        result.add(newPair);

        List<Pair> right = hirschbergs_rec(mid, row_end, lowest_index, column_end);
        result.addAll(right);

        return result;
    }

    public List<Pair> wagner_fischer_small(int row_start, int row_end, int column_start, int column_end) {
        HashMap<Integer, HashMap<Integer, Integer>> cache = new HashMap<>();
        for (int i = row_start; i <= row_end; i++) {
            putCache(cache, i, column_start, i - row_start);
        }
        for (int i = column_start; i <= column_end; i++) {
            putCache(cache, row_start, i, i - column_start);
        }
        for (int i = row_start + 1; i <= row_end; i++) {
            for (int j = column_start + 1; j <= column_end; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    putCache(cache, i, j, getCache(cache, i - 1, j - 1));
                } else {
                    int minVal = Math.min(getCache(cache, i - 1, j - 1),
                            Math.min(getCache(cache, i - 1, j), getCache(cache, i, j - 1)));
                    putCache(cache, i, j, minVal + 1);
                }
            }
        }

        List<Pair> pairs = new ArrayList<>();
        Pair current = new Pair(row_end, column_end);

        while (current.x > row_start || current.y > column_start) {
            int x = current.x;
            int y = current.y;

            int subVal = containsCache(cache, x - 1, y - 1) ? getCache(cache, x - 1, y - 1) : Integer.MAX_VALUE;
            int insertVal = containsCache(cache, x, y - 1) ? getCache(cache, x, y - 1) : Integer.MAX_VALUE;
            int delVal = containsCache(cache, x - 1, y) ? getCache(cache, x - 1, y) : Integer.MAX_VALUE;
            Pair nextPair = null;
            if (subVal <= insertVal && subVal <= delVal) {
                nextPair = new Pair(x - 1, y - 1);
            } else if (insertVal <= subVal && insertVal <= delVal) {
                nextPair = new Pair(x, y - 1);
            } else {
                nextPair = new Pair(x - 1, y);
            }
            current = nextPair;
            if (current.x > row_start || current.y > column_start) {

                System.out.println("New pair in wagner: " + current);
                pairs.add(0, current);
            }
        }
        System.out.println("cache size: " + cache.size());
        return pairs;

    }

    public int[] needlemanwunsch_score(int row_start, int row_end, int column_start, int column_end) {
        HashMap<Integer, HashMap<Integer, Integer>> cache = new HashMap<>();
        for (int i = column_start; i <= column_end; i++) {
            putCache(cache, 0, i, i - column_start);
        }
        for (int i = row_start + 1; i < row_end; i++) {
            putCache(cache, 1, column_start, getCache(cache, 0, column_start) + 1);
            for (int j = column_start + 1; j <= column_end; j++) {

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
        int[] lastRow = new int[column_end - column_start + 1];
        for (int i = 0; i <= column_end - column_start; i++) {
            lastRow[i] = getCache(cache, 1, column_start + i);
        }
        return lastRow;
    }

    public int[] needlemanwunsch_score_reverse(int row_start, int row_end, int column_start, int column_end) {
        HashMap<Integer, HashMap<Integer, Integer>> cache = new HashMap<>();
        for (int i = column_end; i >= column_start; i--) {
            putCache(cache, 0, i, column_end - i);
        }
        for (int i = row_end - 1; i >= row_start; i--) {
            putCache(cache, 1, column_end, getCache(cache, 0, column_end) + 1);
            for (int j = column_end - 1; j >= column_start; j--) {

                int subVal = getCache(cache, 0, j + 1);
                subVal -= s1.charAt(i) == s2.charAt(j) ? 1 : 0;

                int delVal = getCache(cache, 0, j);
                int insertVal = getCache(cache, 1, j + 1);

                putCache(cache, 1, j, Math.min(subVal, Math.min(delVal, insertVal)) + 1);
                // System.out.println("put cache: " + 1 + " " + j + ": " + getCache(cache, 1,
                // j));
            }
            for (int k = column_end; k >= column_start; k--) {
                putCache(cache, 0, k, getCache(cache, 1, k));
            }
        }
        int[] lastRow = new int[column_end - column_start + 1];
        for (int i = 0; i <= column_end - column_start; i++) {
            lastRow[i] = getCache(cache, 1, column_start + i);
        }
        return lastRow;
    }

    public List<EditOperation> getOperations() {
        List<Pair> positions = getEditPositions();

        List<EditOperation> operations = new ArrayList<>();

        EditOperation currentOperation = null;
        OperationType currentOperationType = OperationType.NONE;
        int lastOperationIndex = -1;

        for (int i = positions.size() - 1; i > 0; i--) {
            Pair currentPair = positions.get(i);
            int x = currentPair.x;
            int y = currentPair.y;
            Pair nextPair = positions.get(i - 1);
            int nextX = nextPair.x;
            int nextY = nextPair.y;

            if (x - 1 == nextX && y - 1 == nextY) {
                if (s1.charAt(x - 1) != s2.charAt(y - 1)) {
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
            } else if (y - 1 == nextY) {
                // System.out.println("Insert " + s2.charAt(y - 1) + " at " + (x));

                // Continue last operation or create new
                if (currentOperationType == OperationType.INSERT && lastOperationIndex == x) {
                    currentOperation.chars.add(0, s2.charAt(y - 1));
                    currentOperation.end++;
                } else {
                    currentOperation = new EditOperation(OperationType.INSERT, x - 1, x - 1);
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

        return operations;
    }

    public static void main(String[] args) {
        String s1 = "ALGORI";
        String s2 = "LOGARI";
        // String s1 = "ABAB";
        // String s2 = "ABBAB";
        Hirschbergs hirschbergs = new Hirschbergs(s1, s2);
        List<Pair> points = hirschbergs.getEditPositions();
        for (Pair p : points) {
            System.out.println("point in edit: " + p);
        }
        List<EditOperation> operations = hirschbergs.getOperations();
        for (EditOperation operation : operations) {
            System.out.println("Operation: " + operation);
        }
    }
}
