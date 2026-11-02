package com.example.learnhub.algorithms;

import java.util.Comparator;
import java.util.List;

public final class BinarySearch {
    public static final int NOT_FOUND = -1;

    private BinarySearch() {
    }

    public static <T> int binarySearch(List<T> sorted,
                                       Comparator<T> comparator,
                                       T target) {
        int left = 0;
        int right = sorted.size() - 1;
        while (left <= right) {
            int middle = left + (right - left) / 2;
            int comparison = comparator.compare(sorted.get(middle), target);
            if (comparison < 0) {
                left = middle + 1;
            } else if (comparison > 0) {
                right = middle - 1;
            } else {
                return middle;
            }
        }
        return NOT_FOUND;
    }

    public static <T> int lowerBound(List<T> sorted, Comparator<T> comparator, T target) {
        int left = 0;
        int right = sorted.size();
        while (left < right) {
            int middle = left + (right - left) / 2;
            if (comparator.compare(sorted.get(middle), target) < 0) {
                left = middle + 1;
            } else {
                right = middle;
            }
        }
        return left;
    }

    public static <T> int upperBound(List<T> sorted, Comparator<T> comparator, T target) {
        int left = 0;
        int right = sorted.size();
        while (left < right) {
            int middle = left + (right - left) / 2;
            if (comparator.compare(sorted.get(middle), target) <= 0) {
                left = middle + 1;
            } else {
                right = middle;
            }
        }
        return left;
    }
}
