package ru.kmedhurs.n_puzzle;

import ru.kmedhurs.n_puzzle.heuristic.HeuristicFactory;
import ru.kmedhurs.n_puzzle.heuristic.strategies.Heuristic;
import ru.kmedhurs.n_puzzle.utils.InputParser;
import ru.kmedhurs.n_puzzle.utils.ResultPrinter;

public class Main {

    public static void main(String[] args) {
        if (args.length < 1 || args.length > 2) {
            System.out.println("ERROR: invalid parameters number");
            return;
        }
        InputParser inputParser = new InputParser();
        try {
            Integer[][] matrix = inputParser.initStartState(args[args.length - 1]);
            Heuristic heuristic = HeuristicFactory.getHeuristicStrategy(args[0]);
            PuzzleSolver puzzleSolver = new PuzzleSolver(matrix, heuristic);
            Node finishedNode = puzzleSolver.solvePuzzle();
            ResultPrinter resultPrinter = new ResultPrinter();
            resultPrinter.printResult(finishedNode);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
