package ru.kmedhurs.n_puzzle.heuristics.strategies;

public class UninformedHeuristic implements Heuristic {

    @Override
    public int calculateHeuristic(int[][] currentState) {
        for (int y = 0; y < currentState.length; y++) {
            for (int x = 0; x < currentState.length; x++) {
                if (currentState[y][x] == 0)
                    continue;
                int posYNeeded = (currentState[y][x] - 1) / currentState.length;
                int posXNeeded = currentState[y][x] - posYNeeded * currentState.length - 1;
                if (posXNeeded - x != 0 || posYNeeded - y != 0)
                    return 1;
            }
        }
        return 0;
    }
}
