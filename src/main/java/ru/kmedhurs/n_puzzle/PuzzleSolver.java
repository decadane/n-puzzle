package ru.kmedhurs.n_puzzle;

import ru.kmedhurs.n_puzzle.heuristics.strategies.Heuristic;
import ru.kmedhurs.n_puzzle.model.Board;
import ru.kmedhurs.n_puzzle.model.Node;
import ru.kmedhurs.n_puzzle.utils.StatisticManager;

import java.util.*;

class PuzzleSolver {

    private final Queue<Node> openList = new PriorityQueue<>(Comparator.reverseOrder());
    private final Set<Node> closedList = new HashSet<>();

    PuzzleSolver(int[][] startMatrix, Heuristic heuristic) {
        openList.add(new Node(null, new Board(startMatrix), heuristic, 0));
    }

    Node solvePuzzle(StatisticManager statisticManager) {
        Node currentNode = openList.remove();
        while (!currentNode.isSolved()) {
            List<Node> expandedNode = currentNode.expandNode();
            for (Node node : expandedNode) {
                if (!closedList.contains(node)) {
                    openList.add(node);
                }
            }
            statisticManager.updateMemoryStatistic(openList.size());
            closedList.add(currentNode);
            currentNode = openList.remove();
            statisticManager.updateCheckedStatesCount();
        }
        return currentNode;
    }
}
