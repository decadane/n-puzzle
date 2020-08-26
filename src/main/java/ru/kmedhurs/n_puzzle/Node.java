package ru.kmedhurs.n_puzzle;

import ru.kmedhurs.n_puzzle.exceptions.ImpossibleMoveException;
import ru.kmedhurs.n_puzzle.heuristic.strategies.Heuristic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Node implements Comparable<Node> {

    private final Node parentNode;
    private final Board board;
    private final Heuristic heuristic;
    private final int g;

    Node(Node parentNode, Board board, Heuristic heuristic, int g) {
        this.parentNode = parentNode;
        this.board = board;
        this.heuristic = heuristic;
        this.g = g;
    }

    @Override
    public int compareTo(Node node) {
        return node.getHeuristicValue() - getHeuristicValue();
    }

    private int getHeuristicValue() {
        return g + heuristic.calculateHeuristic(board.getMatrix());
    }

    public Node getParentNode() {
        return parentNode;
    }

    List<Node> expandNode() {
        List<Node> result = new ArrayList<>();
        try {
            Board upBoard = board.deepClone();
            upBoard.moveUp();
            result.add(new Node(this, upBoard, heuristic, g + 1));
        } catch (ImpossibleMoveException e) {
        }
        try {
            Board leftBoard = board.deepClone();
            leftBoard.moveLeft();
            result.add(new Node(this, leftBoard, heuristic, g + 1));
        } catch (ImpossibleMoveException e) {
        }
        try {
            Board downBoard = board.deepClone();
            downBoard.moveDown();
            result.add(new Node(this, downBoard, heuristic, g + 1));
        } catch (ImpossibleMoveException e) {
        }
        try {
            Board rightBoard = board.deepClone();
            rightBoard.moveRight();
            result.add(new Node(this, rightBoard, heuristic, g + 1));
        } catch (ImpossibleMoveException e) {
        }
        return result;
    }

    boolean isSolved() {
        boolean isSolved = true;
        for (int i = 0; i < board.getMatrix().length; i++) {
            for (int j = 0; j < board.getMatrix().length; j++) {
                isSolved = isNumberCorrect(i, j) && isSolved;
            }
        }
        return isSolved;
    }

    private boolean isNumberCorrect(int i, int j) {
        if (board.getMatrix()[i][j] == i * board.getMatrix().length + j + 1)
            return true;
        return i == board.getMatrix().length - 1 && j == board.getMatrix().length - 1 && board.getMatrix()[i][j] == 0;
    }

    @Override
    public String toString() {
        return Arrays.stream(board.getMatrix()).map(
                st -> Arrays.stream(st).map(str -> Integer.toString(str)
                ).collect(Collectors.joining(" "))).collect(Collectors.joining("\n"));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return board.equals(node.board);
    }

    @Override
    public int hashCode() {
        return Objects.hash(board);
    }
}
