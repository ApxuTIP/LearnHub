package com.example.learnhub.algorithms;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class TwoSum {

    private TwoSum() {
    }

    public static class Pair {
        public final int firstIndex;
        public final int secondIndex;
        public final int firstValue;
        public final int secondValue;

        public Pair(int firstIndex, int secondIndex, int firstValue, int secondValue) {
            this.firstIndex = firstIndex;
            this.secondIndex = secondIndex;
            this.firstValue = firstValue;
            this.secondValue = secondValue;
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
        for (int i = 0; i < data.size(); i++) {
            int complement = target - data.get(i);
            Integer previousIndex = seen.get(complement);
            if (previousIndex != null) {
                return new Pair(previousIndex, i,
                        data.get(previousIndex), data.get(i));
            }
            seen.put(data.get(i), i);
        }
        return null;
    }

    public static Pair findExactNaive(List<Integer> data, int target) {
        for (int i = 0; i < data.size(); i++) {
            for (int j = i + 1; j < data.size(); j++) {
                if (data.get(i) + data.get(j) == target) {
                    return new Pair(i, j, data.get(i), data.get(j));
                }
            }
        }
        return null;
    }

    public static Pair findClosest(List<Integer> data, int target) {
        int count = data.size();
        if (count < 2) {
            return null;
        }

        int[] values = new int[count];
        int[] originalIndices = new int[count];
        for (int i = 0; i < count; i++) {
            values[i] = data.get(i);
            originalIndices[i] = i;
        }

        for (int i = 1; i < count; i++) {
            int currentValue = values[i];
            int currentIndex = originalIndices[i];
            int j = i - 1;
            while (j >= 0 && values[j] > currentValue) {
                values[j + 1] = values[j];
                originalIndices[j + 1] = originalIndices[j];
                j--;
            }
            values[j + 1] = currentValue;
            originalIndices[j + 1] = currentIndex;
        }

        int left = 0;
        int right = count - 1;
        int bestDifference = Integer.MAX_VALUE;
        Pair best = null;

        while (left < right) {
            int sum = values[left] + values[right];
            int difference = Math.abs(sum - target);
            if (difference < bestDifference) {
                bestDifference = difference;
                best = new Pair(originalIndices[left], originalIndices[right],
                        values[left], values[right]);
            }
            if (sum < target) {
                left++;
            } else if (sum > target) {
                right--;
            } else {
                break;
            }
        }
        return best;
    }
}