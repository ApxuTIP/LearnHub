package com.example.learnhub.algorithms;

import java.util.Comparator;
import java.util.List;

public final class Searching {

    private Searching() {
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
        return -1;
    }
}