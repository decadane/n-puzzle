package ru.kmedhurs.n_puzzle;

import ru.kmedhurs.n_puzzle.heuristics.HeuristicFactory;
import ru.kmedhurs.n_puzzle.heuristics.strategies.Heuristic;
import ru.kmedhurs.n_puzzle.model.Node;
import ru.kmedhurs.n_puzzle.utils.InputParser;
import ru.kmedhurs.n_puzzle.utils.ResultPrinter;
import ru.kmedhurs.n_puzzle.utils.SolvingChecker;
import ru.kmedhurs.n_puzzle.utils.StatisticManager;

public class Main {

    public static void main(String[] args) {
        if (args.length < 1 || args.length > 2) {
            System.out.println("ERROR: invalid parameters number");
            return;
        }
        InputParser inputParser = new InputParser();
        try {
            int[][] matrix = inputParser.initStartState(args[args.length - 1]);
            Heuristic heuristic = HeuristicFactory.getHeuristicStrategy(args[0]);
            SolvingChecker solvingChecker = new SolvingChecker();
            if (!solvingChecker.isMatrixValid(matrix)) {
                System.out.println("This matrix is incorrect");
                return;
            }
            if (!solvingChecker.isMatrixSolvable(matrix)) {
                System.out.println("This matrix is unsolvable");
                return;
            }
            StatisticManager statisticManager = StatisticManager.initStatisticManager();
            PuzzleSolver puzzleSolver = new PuzzleSolver(matrix, heuristic);
            Node finishedNode = puzzleSolver.solvePuzzle(statisticManager);
            ResultPrinter resultPrinter = new ResultPrinter(statisticManager);
            resultPrinter.printResult(finishedNode);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Usage: [HAM | MAN | INT] [file path]");
        }
    }
}
