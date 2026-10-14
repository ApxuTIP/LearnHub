package com.example.learnhub.algorithms;

import java.util.List;

public final class SlidingWindow {

    private SlidingWindow() {
    }

    public static class WindowResult {

        private final int startIndex;
        private final int endIndex;
        private final long sum;

        public WindowResult(int startIndex, int endIndex, long sum) {
            this.startIndex = startIndex;
            this.endIndex = endIndex;
            this.sum = sum;
        }

        public int getStartIndex() {
            return startIndex;
        }

        public int getEndIndex() {
            return endIndex;
        }

        public long getSum() {
            return sum;
        }

        @Override
        public String toString() {
            return String.format("окно [%d..%d], сумма = %d", startIndex, endIndex, sum);
        }
    }

    public static WindowResult maxSumWindow(List<Integer> data, int windowSize) {
        if (windowSize <= 0 || windowSize > data.size()) {
            throw new IllegalArgumentException("Некорректный размер окна: " + windowSize);
        }
        long sum = 0;
        for (int index = 0; index < windowSize; index++) {
            sum += data.get(index);
        }
        long best = sum;
        int bestStart = 0;
        for (int index = windowSize; index < data.size(); index++) {
            sum += data.get(index) - data.get(index - windowSize);
            if (sum > best) {
                best = sum;
                bestStart = index - windowSize + 1;
            }
        }
        return new WindowResult(bestStart, bestStart + windowSize - 1, best);
    }

    public static WindowResult maxSumWindowNaive(List<Integer> data, int windowSize) {
        if (windowSize <= 0 || windowSize > data.size()) {
            throw new IllegalArgumentException("Некорректный размер окна: " + windowSize);
        }
        long best = Long.MIN_VALUE;
        int bestStart = 0;
        for (int startIndex = 0; startIndex + windowSize <= data.size(); startIndex++) {
            long sum = 0;
            for (int offset = 0; offset < windowSize; offset++) {
                sum += data.get(startIndex + offset);
            }
            if (sum > best) {
                best = sum;
                bestStart = startIndex;
            }
        }
        return new WindowResult(bestStart, bestStart + windowSize - 1, best);
    }
}