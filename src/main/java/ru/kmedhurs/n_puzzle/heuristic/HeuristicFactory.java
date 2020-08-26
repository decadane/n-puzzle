package ru.kmedhurs.n_puzzle.heuristic;

import ru.kmedhurs.n_puzzle.heuristic.strategies.HammingDistanceHeuristic;
import ru.kmedhurs.n_puzzle.heuristic.strategies.Heuristic;
import ru.kmedhurs.n_puzzle.heuristic.strategies.ManhattanDistanceHeuristic;
import ru.kmedhurs.n_puzzle.heuristic.strategies.ManhattanDistanceWithInterferenceHeuristic;

public class HeuristicFactory {

    private HeuristicFactory() {
    }

    public static Heuristic getHeuristicStrategy(String heuristicName) {
        HeuristicType heuristicType = HeuristicType.valueOf(heuristicName);
        switch (heuristicType) {
            case HAM:
                return new HammingDistanceHeuristic();
            case INT:
                return new ManhattanDistanceWithInterferenceHeuristic();
            default:
                return new ManhattanDistanceHeuristic();
        }
    }
}
