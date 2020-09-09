package ru.kmedhurs.n_puzzle.utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SolvingChecker {

    public boolean isMatrixSolvable(int[][] matrix) {
        int[] flatMatrix = Arrays.stream(matrix).flatMapToInt(Arrays::stream).toArray();
        return isSolvable(flatMatrix, matrix.length);
    }

    private boolean isSolvable(int[] flatMatrix, int size) {
        int parity = 0;
        int row = 0;
        int blankRow = 0;

        for (int i = 0; i < flatMatrix.length; i++) {
            if (i % size == 0)
                row++;
            if (flatMatrix[i] == 0) {
                blankRow = row;
                continue;
            }
            for (int j = i + 1; j < flatMatrix.length; j++) {
                if (flatMatrix[i] > flatMatrix[j] && flatMatrix[j] != 0) {
                    parity++;
                }
            }
        }
        return checkParity(blankRow, parity, size);
    }

    private boolean checkParity(int blankRow, int parity, int size) {
        if (size % 2 == 0) {
            if (blankRow % 2 == 0)
                return parity % 2 == 0;
            else
                return parity % 2 != 0;
        } else {
            return parity % 2 != 0;
        }
    }

    public boolean isMatrixValid(int[][] matrix) {
        Map<Integer, Integer> existingPlates = new HashMap<>();
        int[] ints = Arrays.stream(matrix).flatMapToInt(Arrays::stream).toArray();
        Arrays.stream(ints).forEach(i -> existingPlates.merge(i, 1, Integer::sum));
        for (int i = 0; i < matrix.length * matrix.length; i++) {
            if (!existingPlates.containsKey(i) || existingPlates.get(i) != 1)
                return false;
        }
        return true;
    }
}
