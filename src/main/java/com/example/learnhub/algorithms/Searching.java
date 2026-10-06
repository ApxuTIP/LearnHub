package com.example.learnhub.algorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.function.ToIntFunction;

public final class Searching {

    private Searching() {
    }

    public static <T> int binarySearch(List<T> sorted, ToIntFunction<T> key, int target) {
        int low = 0;
        int high = sorted.size() - 1;
        while (low <= high) {
            int middle = low + (high - low) / 2;
            int comparison = Integer.compare(key.applyAsInt(sorted.get(middle)), target);
            if (comparison < 0) {
                low = middle + 1;
            } else if (comparison > 0) {
                high = middle - 1;
            } else {
                return middle;
            }
        }
        return -1;
    }

    public static <T> int lowerBound(List<T> sorted, ToIntFunction<T> key, int target) {
        int low = 0;
        int high = sorted.size();
        while (low < high) {
            int middle = low + (high - low) / 2;
            if (key.applyAsInt(sorted.get(middle)) < target) {
                low = middle + 1;
            } else {
                high = middle;
            }
        }
        return low;
    }

    public static <T> int upperBound(List<T> sorted, ToIntFunction<T> key, int target) {
        int low = 0;
        int high = sorted.size();
        while (low < high) {
            int middle = low + (high - low) / 2;
            if (key.applyAsInt(sorted.get(middle)) <= target) {
                low = middle + 1;
            } else {
                high = middle;
            }
        }
        return low;
    }

    public static <T> List<T> rangeQuery(List<T> sorted,
                                         ToIntFunction<T> key,
                                         int low,
                                         int high) {
        int from = lowerBound(sorted, key, low);
        int to = upperBound(sorted, key, high);
        return new ArrayList<>(sorted.subList(from, to));
    }
}