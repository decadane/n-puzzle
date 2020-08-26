package ru.kmedhurs.n_puzzle.heuristic.strategies;

public class HammingDistanceHeuristic implements Heuristic {

    @Override
    public int calculateHeuristic(Integer[][] currentState) {
        int heuristic = 0;
        for (int i = 0; i < currentState.length; i++) {
            for (int j = 0; j < currentState.length; j++) {
                if (!currentState[i][j].equals(i * currentState.length + j + 1))
                    heuristic++;
            }
        }
        return heuristic;
    }
}
