package ru.kmedhurs.n_puzzle.utils;

import ru.kmedhurs.n_puzzle.Node;

import java.util.ArrayDeque;
import java.util.Deque;

public class ResultPrinter {

    public void printResult(Node finishNode) {
        Deque<Node> printStack = new ArrayDeque<>();
        while (finishNode != null) {
            printStack.add(finishNode);
            finishNode = finishNode.getParentNode();
        }
        System.out.println();
        while (printStack.size() != 1) {
            System.out.println(printStack.pollLast());
            System.out.println("\n \u2193 \n");
        }
        System.out.println(printStack.pollLast());
    }
}
