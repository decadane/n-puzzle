package ru.kmedhurs.n_puzzle.utils;

public class SnailHelper {

    public int[] spiralBinary(int matrixSize, int findNumber) {
        RingInfo ringInfo = getRingNumber(matrixSize, findNumber);
        if (findNumber <= ringInfo.maxNumber + ringInfo.ringSideLength) {
            return new int[]{ringInfo.ringNumber, ringInfo.ringNumber + findNumber - ringInfo.maxNumber - 1};
        } else if (findNumber <= ringInfo.maxNumber + (2 * ringInfo.ringSideLength) - 1) {
            return new int[]{findNumber - ringInfo.maxNumber - ringInfo.ringSideLength + ringInfo.ringNumber, matrixSize - ringInfo.ringNumber - 1};
        } else if (findNumber <= ringInfo.maxNumber + (3 * ringInfo.ringSideLength) - 2) {
            return new int[]{matrixSize - ringInfo.ringNumber - 1, ringInfo.ringSideLength - (findNumber - ringInfo.maxNumber - (2 * ringInfo.ringSideLength - 1)) - 1 + ringInfo.ringNumber};
        } else {
            return new int[]{ringInfo.ringSideLength - (findNumber - ringInfo.maxNumber - (3 * ringInfo.ringSideLength - 2)) - 1 + ringInfo.ringNumber, ringInfo.ringNumber};
        }
    }

    private RingInfo getRingNumber(int matrixSize, int findNumber) {
        int ringNumber = 0;
        int currentMax = 0;
        while (matrixSize >= 0) {
            int maxNumberInRing = matrixSize * 4 - 4 + currentMax;
            if (maxNumberInRing < findNumber) {
                currentMax = maxNumberInRing;
                matrixSize -= 2;
                ringNumber++;
            } else
                break;
        }
        return new RingInfo(ringNumber, currentMax, matrixSize);
    }

    public boolean isElementOnPosition(int x, int y, int matrixSize, int findNumber) {
        if (findNumber == 0)
            return true;
        int[] coords = spiralBinary(matrixSize, findNumber);
        return coords[0] == y && coords[1] == x;
    }

    private static class RingInfo {

        private final int ringNumber;
        private final int maxNumber;
        private final int ringSideLength;

        private RingInfo(int ringNumber, int maxNumber, int ringSideLength) {
            this.ringNumber = ringNumber;
            this.maxNumber = maxNumber;
            this.ringSideLength = ringSideLength;
        }
    }
}