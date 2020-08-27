package ru.kmedhurs.n_puzzle.model;

import java.util.Arrays;

public class Board {

    private final int[][] matrix;
    private final StartPoint startPoint;

    public Board(int[][] matrix) {
        this.matrix = matrix;
        this.startPoint = findStartPoint();
    }

    Board deepClone() {
        int[][] newMatrix = new int[matrix.length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            newMatrix[i] = matrix[i].clone();
        }
        return new Board(newMatrix);
    }

    int[][] getMatrix() {
        return matrix;
    }

    boolean isLeftPossible() {
        return startPoint.yPos > 0;
    }

    void moveLeft() {
        matrix[startPoint.xPos][startPoint.yPos] = matrix[startPoint.xPos][startPoint.yPos - 1];
        matrix[startPoint.xPos][startPoint.yPos - 1] = 0;
        startPoint.yPos--;
    }

    boolean isRightPossible() {
        return startPoint.yPos < matrix.length - 1;
    }

    void moveRight() {
        matrix[startPoint.xPos][startPoint.yPos] = matrix[startPoint.xPos][startPoint.yPos + 1];
        matrix[startPoint.xPos][startPoint.yPos + 1] = 0;
        startPoint.yPos++;
    }

    boolean isUpPossible() {
        return startPoint.xPos > 0;
    }

    void moveUp() {
        matrix[startPoint.xPos][startPoint.yPos] = matrix[startPoint.xPos - 1][startPoint.yPos];
        matrix[startPoint.xPos - 1][startPoint.yPos] = 0;
        startPoint.xPos--;
    }

    boolean isDownPossible() {
        return startPoint.xPos < matrix.length - 1;
    }

    void moveDown() {
        matrix[startPoint.xPos][startPoint.yPos] = matrix[startPoint.xPos + 1][startPoint.yPos];
        matrix[startPoint.xPos + 1][startPoint.yPos] = 0;
        startPoint.xPos++;
    }

    private StartPoint findStartPoint() {
        StartPoint newStartPoint = null;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (matrix[i][j] == 0) {
                    newStartPoint = new StartPoint(i, j);
                }
            }
        }
        return newStartPoint;
    }

    private static class StartPoint {

        private int xPos;
        private int yPos;

        StartPoint(int xPos, int yPos) {
            this.xPos = xPos;
            this.yPos = yPos;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Board board = (Board) o;
        return Arrays.deepEquals(matrix, board.matrix);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(matrix);
    }
}
