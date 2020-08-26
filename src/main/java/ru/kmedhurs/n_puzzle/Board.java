package ru.kmedhurs.n_puzzle;

import ru.kmedhurs.n_puzzle.exceptions.ImpossibleMoveException;

import java.util.Arrays;

public class Board {

    private final Integer[][] matrix;
    private final StartPoint startPoint;

    public Board(Integer[][] matrix) {
        this.matrix = matrix;
        this.startPoint = findStartPoint();
    }

    public Board deepClone() {
        Integer[][] newMatrix = new Integer[matrix.length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            newMatrix[i] = matrix[i].clone();
        }
        return new Board(newMatrix);
    }

    public Integer[][] getMatrix() {
        return matrix;
    }

    public void moveLeft() throws ImpossibleMoveException {
        if (startPoint.yPos > 0) {
            matrix[startPoint.xPos][startPoint.yPos] = matrix[startPoint.xPos][startPoint.yPos - 1];
            matrix[startPoint.xPos][startPoint.yPos - 1] = 0;
            startPoint.yPos--;
        } else throw new ImpossibleMoveException();
    }

    public void moveRight() throws ImpossibleMoveException {
        if (startPoint.yPos < matrix.length - 1) {
            matrix[startPoint.xPos][startPoint.yPos] = matrix[startPoint.xPos][startPoint.yPos + 1];
            matrix[startPoint.xPos][startPoint.yPos + 1] = 0;
            startPoint.yPos++;
        } else throw new ImpossibleMoveException();
    }

    public void moveUp() throws ImpossibleMoveException {
        if (startPoint.xPos > 0) {
            matrix[startPoint.xPos][startPoint.yPos] = matrix[startPoint.xPos - 1][startPoint.yPos];
            matrix[startPoint.xPos - 1][startPoint.yPos] = 0;
            startPoint.xPos--;
        } else throw new ImpossibleMoveException();
    }

    public void moveDown() throws ImpossibleMoveException {
        if (startPoint.xPos < matrix.length - 1) {
            matrix[startPoint.xPos][startPoint.yPos] = matrix[startPoint.xPos + 1][startPoint.yPos];
            matrix[startPoint.xPos + 1][startPoint.yPos] = 0;
            startPoint.xPos++;
        } else throw new ImpossibleMoveException();
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
        return Arrays.equals(matrix, board.matrix);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(matrix);
    }
}
