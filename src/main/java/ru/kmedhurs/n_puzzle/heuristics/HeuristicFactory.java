package ru.kmedhurs.n_puzzle.heuristics;

import ru.kmedhurs.n_puzzle.exceptions.InputErrorException;
import ru.kmedhurs.n_puzzle.heuristics.strategies.HammingDistanceHeuristic;
import ru.kmedhurs.n_puzzle.heuristics.strategies.Heuristic;
import ru.kmedhurs.n_puzzle.heuristics.strategies.ManhattanDistanceHeuristic;
import ru.kmedhurs.n_puzzle.heuristics.strategies.UninformedHeuristic;

import java.util.Arrays;

public class HeuristicFactory {

    private HeuristicFactory() {
    }

    public static Heuristic getHeuristicStrategy(String heuristicName) throws InputErrorException {
        if (Arrays.stream(HeuristicType.values()).anyMatch(t -> t.name().equals(heuristicName))) {
            HeuristicType heuristicType = HeuristicType.valueOf(heuristicName);
            switch (heuristicType) {
                case HAM:
                    return new HammingDistanceHeuristic();
                case UNI:
                    return new UninformedHeuristic();
                case MAN:
                    return new ManhattanDistanceHeuristic();
            }
        } else {
            throw new InputErrorException("ERROR: unknown heuristic name " + heuristicName);
        }
        return null;
    }
}
