package ru.kmedhurs.n_puzzle.model;

import ru.kmedhurs.n_puzzle.heuristics.strategies.Heuristic;
import ru.kmedhurs.n_puzzle.utils.SnailHelper;

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
    private final SnailHelper snailHelper;

    public Node(Node parentNode, Board board, Heuristic heuristic, int g, SnailHelper snailHelper) {
        this.parentNode = parentNode;
        this.board = board;
        this.heuristic = heuristic;
        this.g = g;
        this.snailHelper = snailHelper;
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

    public List<Node> expandNode() {
        List<Node> result = new ArrayList<>();
        if (board.isUpPossible()) {
            Board upBoard = board.deepClone();
            upBoard.moveUp();
            result.add(new Node(this, upBoard, heuristic, g + 1, snailHelper));
        }
        if (board.isLeftPossible()) {
            Board leftBoard = board.deepClone();
            leftBoard.moveLeft();
            result.add(new Node(this, leftBoard, heuristic, g + 1, snailHelper));
        }
        if (board.isDownPossible()) {
            Board downBoard = board.deepClone();
            downBoard.moveDown();
            result.add(new Node(this, downBoard, heuristic, g + 1, snailHelper));
        }
        if (board.isRightPossible()) {
            Board rightBoard = board.deepClone();
            rightBoard.moveRight();
            result.add(new Node(this, rightBoard, heuristic, g + 1, snailHelper));
        }
        return result;
    }

    public boolean isSolved() {
        for (int y = 0; y < board.getMatrix().length; y++) {
            for (int x = 0; x < board.getMatrix().length; x++) {
                if (!snailHelper.isElementOnPosition(x, y, board.getMatrix().length, board.getMatrix()[y][x]))
                    return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return Arrays.stream(board.getMatrix())
                .map(
                        st -> Arrays.stream(st).mapToObj(Integer::toString).collect(Collectors.joining(" "))
                ).collect(Collectors.joining("\n"));
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
