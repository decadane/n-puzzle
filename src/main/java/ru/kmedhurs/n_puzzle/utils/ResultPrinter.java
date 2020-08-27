package ru.kmedhurs.n_puzzle.utils;

import ru.kmedhurs.n_puzzle.model.Node;

import java.util.ArrayDeque;
import java.util.Deque;

public class ResultPrinter {

    private final StatisticManager statisticManager;

    public ResultPrinter(StatisticManager statisticManager) {
        this.statisticManager = statisticManager;
    }

    public void printResult(Node finishNode) {
        Deque<Node> printStack = new ArrayDeque<>();
        while (finishNode != null) {
            printStack.add(finishNode);
            finishNode = finishNode.getParentNode();
        }
        System.out.println();
        statisticManager.updateStepsCount(printStack.size());
        while (printStack.size() != 1) {
            System.out.println(printStack.pollLast());
            System.out.println("\n \u2193 \n");
        }
        System.out.println(printStack.pollLast());
        System.out.println(statisticManager.getStatistic());
    }
}
