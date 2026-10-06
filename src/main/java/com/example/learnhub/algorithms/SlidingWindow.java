package com.example.learnhub.algorithms;

import java.util.List;

public final class SlidingWindow {

    private SlidingWindow() {
    }

    public static class WindowResult {
        public final int startIndex;
        public final int endIndex;
        public final long sum;

        public WindowResult(int startIndex, int endIndex, long sum) {
            this.startIndex = startIndex;
            this.endIndex = endIndex;
            this.sum = sum;
        }

        @Override
        public String toString() {
            return "окно [" + startIndex + ".." + endIndex + "], сумма = " + sum;
        }
    }

    public static WindowResult maxSumWindow(List<Integer> data, int k) {
        if (k <= 0 || k > data.size()) {
            throw new IllegalArgumentException("Некорректный размер окна: " + k);
        }
        long sum = 0;
        for (int i = 0; i < k; i++) {
            sum += data.get(i);
        }
        long best = sum;
        int bestStart = 0;
        for (int i = k; i < data.size(); i++) {
            sum += data.get(i) - data.get(i - k);
            if (sum > best) {
                best = sum;
                bestStart = i - k + 1;
            }
        }
        return new WindowResult(bestStart, bestStart + k - 1, best);
    }

    public static WindowResult maxSumWindowNaive(List<Integer> data, int k) {
        if (k <= 0 || k > data.size()) {
            throw new IllegalArgumentException("Некорректный размер окна: " + k);
        }
        long best = Long.MIN_VALUE;
        int bestStart = 0;
        for (int i = 0; i + k <= data.size(); i++) {
            long sum = 0;
            for (int j = 0; j < k; j++) {
                sum += data.get(i + j);
            }
            if (sum > best) {
                best = sum;
                bestStart = i;
            }
        }
        return new WindowResult(bestStart, bestStart + k - 1, best);
    }
}