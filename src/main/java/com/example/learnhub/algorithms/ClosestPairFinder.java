package com.example.learnhub.algorithms;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class ClosestPairFinder {
    private static final Comparator<Integer> BY_VALUE_ASC = Integer::compare;

    private ClosestPairFinder() {
    }

    public static class Pair {
        private final int firstIndex;
        private final int secondIndex;
        private final int firstValue;
        private final int secondValue;

        public Pair(int firstIndex, int secondIndex, int firstValue, int secondValue) {
            this.firstIndex = firstIndex;
            this.secondIndex = secondIndex;
            this.firstValue = firstValue;
            this.secondValue = secondValue;
        }

        public int getFirstIndex() {
            return firstIndex;
        }

        public int getSecondIndex() {
            return secondIndex;
        }

        public int getFirstValue() {
            return firstValue;
        }

        public int getSecondValue() {
            return secondValue;
        }

        @Override
        public String toString() {
            return "(" + firstValue + " + " + secondValue
                    + " = " + (firstValue + secondValue) + ")"
                    + " [индексы " + firstIndex + ", " + secondIndex + "]";
        }
    }

    public static Pair findExact(List<Integer> data, int target) {
        Map<Integer, Integer> seen = new HashMap<>();
        Pair result = null;
        for (int index = 0; index < data.size() && result == null; index++) {
            int complement = target - data.get(index);
            Integer previousIndex = seen.get(complement);
            if (previousIndex != null) {
                result = new Pair(previousIndex, index,
                        data.get(previousIndex), data.get(index));
            }

            else {
                seen.put(data.get(index), index);
            }
        }
        return result;
    }

    public static Pair findExactNaive(List<Integer> data, int target) {
        Pair result = null;
        for (int firstIndex = 0; firstIndex < data.size() && result == null; firstIndex++) {
            for (int secondIndex = firstIndex + 1;
                 secondIndex < data.size() && result == null;
                 secondIndex++) {
                if (data.get(firstIndex) + data.get(secondIndex) == target) {
                    result = new Pair(firstIndex, secondIndex,
                            data.get(firstIndex), data.get(secondIndex));
                }
            }
        }
        return result;
    }

    public static Pair findClosest(List<Integer> data, int target) {
        int count = data.size();
        if (count < 2) {
            return null;
        }
        List<Integer> sorted = new ArrayList<>(data);
        Sorter.mergeSort(sorted, BY_VALUE_ASC);
        int left = 0;
        int right = count - 1;
        int bestDifference = Integer.MAX_VALUE;
        Pair best = null;

        while (left < right) {
            int sum = sorted.get(left) + sorted.get(right);
            int difference = Math.abs(sum - target);
            if (difference < bestDifference) {
                bestDifference = difference;
                best = new Pair(left, right,
                        sorted.get(left), sorted.get(right));
            }

            if (sum < target) {
                left++;
            }

            else if (sum > target) {
                right--;
            }

            else {
                break;
            }
        }
        return best;
    }

    public static Pair findClosestNaive(List<Integer> data, int target) {
        int count = data.size();
        if (count < 2) {
            return null;
        }

        int bestDifference = Integer.MAX_VALUE;
        Pair best = null;

        for (int firstIndex = 0; firstIndex < count; firstIndex++) {
            for (int secondIndex = firstIndex + 1; secondIndex < count; secondIndex++) {
                int sum = data.get(firstIndex) + data.get(secondIndex);
                int difference = Math.abs(sum - target);
                if (difference < bestDifference) {
                    bestDifference = difference;
                    best = new Pair(firstIndex, secondIndex,
                            data.get(firstIndex), data.get(secondIndex));
                }
            }
        }
        return best;
    }
}