package ru.kmedhurs.n_puzzle.heuristics.strategies;

public class ManhattanDistanceHeuristic implements Heuristic {

    @Override
    public int calculateHeuristic(int[][] currentState) {
        int heuristic = 0;
        for (int y = 0; y < currentState.length; y++) {
            for (int x = 0; x < currentState.length; x++) {
                if (currentState[y][x] == 0)
                    continue;
                int posYNeeded = (currentState[y][x] - 1) / currentState.length;
                int posXNeeded = currentState[y][x] - posYNeeded * currentState.length - 1;
                heuristic += (Math.abs(x - posXNeeded)) + (Math.abs(y - posYNeeded));
            }
        }
        return heuristic;
    }
}
