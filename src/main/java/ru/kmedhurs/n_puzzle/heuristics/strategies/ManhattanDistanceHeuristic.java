package ru.kmedhurs.n_puzzle.heuristics.strategies;

import ru.kmedhurs.n_puzzle.utils.SnailHelper;

public class ManhattanDistanceHeuristic implements Heuristic {

    @Override
    public int calculateHeuristic(int[][] currentState) {
        SnailHelper snailHelper = new SnailHelper();
        int heuristic = 0;
        for (int y = 0; y < currentState.length; y++) {
            for (int x = 0; x < currentState.length; x++) {
                if (currentState[y][x] == 0)
                    continue;
                int[] coords = snailHelper.spiralBinary(currentState.length, currentState[y][x]);
                heuristic += (Math.abs(x - coords[1])) + (Math.abs(y - coords[0]));
            }
        }
        return heuristic;
    }
}
