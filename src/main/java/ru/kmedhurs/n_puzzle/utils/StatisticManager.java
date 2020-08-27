package ru.kmedhurs.n_puzzle.utils;

public class StatisticManager {

    private final long startTime;
    private int memoryCount;
    private int stepsCount;
    private int checkedStatesCount;

    public static StatisticManager initStatisticManager() {
        return new StatisticManager();
    }

    public void updateMemoryStatistic(int memoryUsed) {
        memoryCount = Math.max(memoryUsed, memoryCount);
    }

    void updateStepsCount(int steps) {
        stepsCount = steps - 1;
    }

    public void updateCheckedStatesCount() {
        checkedStatesCount++;
    }

    String getStatistic() {
        return "\nTime complexity: " + (System.currentTimeMillis() - startTime) + " milliseconds\n" +
                "Checked states count: " + checkedStatesCount + "\n" +
                "Memory complexity: " + memoryCount + " iterations\n" +
                "Number of moves: " + stepsCount;
    }

    private StatisticManager() {
        this.startTime = System.currentTimeMillis();
    }
}
