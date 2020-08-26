package ru.kmedhurs.n_puzzle;

import ru.kmedhurs.n_puzzle.heuristic.strategies.Heuristic;

import java.util.*;

class PuzzleSolver {

    private final Queue<Node> openList = new PriorityQueue<>(Comparator.reverseOrder());
    private final Set<Node> closedList = new HashSet<>();

    PuzzleSolver(Integer[][] startMatrix, Heuristic heuristic) {
        openList.add(new Node(null, new Board(startMatrix), heuristic, 0));
    }

    Node solvePuzzle() {
        Node currentNode = openList.remove();
        while (!currentNode.isSolved()) {
            List<Node> expandedNode = currentNode.expandNode();
            for (Node node : expandedNode) {
                if (!closedList.contains(node)) {
                    openList.add(node);
                }
            }
            closedList.add(currentNode);
            currentNode = openList.remove();
        }
        return currentNode;
    }
}
