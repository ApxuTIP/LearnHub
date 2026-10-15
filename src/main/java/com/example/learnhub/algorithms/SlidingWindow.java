package com.example.learnhub.algorithms;

import java.util.List;

public final class SlidingWindow {
    private SlidingWindow() {
    }

    public static class WindowResult {

        private final int startIndex;
        private final int endIndex;
        private final long windowSum;

        public WindowResult(int startIndex, int endIndex, long windowSum) {
            this.startIndex = startIndex;
            this.endIndex = endIndex;
            this.windowSum = windowSum;
        }

        public int getStartIndex() {
            return startIndex;
        }

        public int getEndIndex() {
            return endIndex;
        }

        public long getWindowSum() {
            return windowSum;
        }

        @Override
        public String toString() {
            return "окно [" + startIndex + ".." + endIndex
                    + "], сумма = " + windowSum;
        }
    }

    public static WindowResult maxSumWindow(List<Integer> data, int windowSize) {
        if (windowSize <= 0 || windowSize > data.size()) {
            throw new IllegalArgumentException("Некорректный размер окна: " + windowSize);
        }

        long windowSum = 0;
        for (int index = 0; index < windowSize; index++) {
            windowSum += data.get(index);
        }

        long bestWindowSum = windowSum;
        int bestStartIndex = 0;

        for (int index = windowSize; index < data.size(); index++) {
            windowSum += data.get(index) - data.get(index - windowSize);
            if (windowSum > bestWindowSum) {
                bestWindowSum = windowSum;
                bestStartIndex = index - windowSize + 1;
            }
        }

        return new WindowResult(bestStartIndex,
                bestStartIndex + windowSize - 1,
                bestWindowSum);
    }

    public static WindowResult maxSumWindowNaive(List<Integer> data, int windowSize) {
        if (windowSize <= 0 || windowSize > data.size()) {
            throw new IllegalArgumentException("Некорректный размер окна: " + windowSize);
        }

        long bestWindowSum = Long.MIN_VALUE;
        int bestStartIndex = 0;

        for (int startIndex = 0;
             startIndex + windowSize <= data.size();
             startIndex++) {
            long windowSum = 0;
            for (int offset = 0; offset < windowSize; offset++) {
                windowSum += data.get(startIndex + offset);
            }

            if (windowSum > bestWindowSum) {
                bestWindowSum = windowSum;
                bestStartIndex = startIndex;
            }
        }

        return new WindowResult(bestStartIndex,
                bestStartIndex + windowSize - 1,
                bestWindowSum);
    }
}