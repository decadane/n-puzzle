package ru.kmedhurs.n_puzzle.heuristics.strategies;

import ru.kmedhurs.n_puzzle.utils.SnailHelper;

public class UninformedHeuristic implements Heuristic {

    @Override
    public int calculateHeuristic(int[][] currentState) {
        SnailHelper snailHelper = new SnailHelper();
        for (int y = 0; y < currentState.length; y++) {
            for (int x = 0; x < currentState.length; x++) {
                if (currentState[y][x] == 0)
                    continue;
                int[] coords = snailHelper.spiralBinary(currentState.length, currentState[y][x]);
                if (coords[0] != y || coords[1] != x)
                    return 1;
            }
        }
        return 0;
    }
}
