package ru.kmedhurs.n_puzzle.heuristics.strategies;

public class HammingDistanceHeuristic implements Heuristic {

    @Override
    public int calculateHeuristic(int[][] currentState) {
        int heuristic = 0;
        for (int i = 0; i < currentState.length; i++) {
            for (int j = 0; j < currentState.length; j++) {
                if (currentState[i][j] != i * currentState.length + j + 1)
                    heuristic++;
            }
        }
        return heuristic;
    }
}
