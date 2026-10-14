package com.example.learnhub.algorithms;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public final class Sorting {

    private static final int ADAPTIVE_SORT_THRESHOLD = 32;

    private Sorting() {
    }

    public static <T> void mergeSort(List<T> list, Comparator<T> comparator) {
        if (list.size() < 2) {
            return;
        }
        int middle = list.size() / 2;
        List<T> left = new ArrayList<>(list.subList(0, middle));
        List<T> right = new ArrayList<>(list.subList(middle, list.size()));
        mergeSort(left, comparator);
        mergeSort(right, comparator);
        merge(list, left, right, comparator);
    }

    private static <T> void merge(
            List<T> target,
            List<T> left,
            List<T> right,
            Comparator<T> comparator) {
        int leftIndex = 0;
        int rightIndex = 0;
        int targetIndex = 0;
        while (leftIndex < left.size() && rightIndex < right.size()) {
            if (comparator.compare(left.get(leftIndex), right.get(rightIndex)) <= 0) {
                target.set(targetIndex++, left.get(leftIndex++));
            } else {
                target.set(targetIndex++, right.get(rightIndex++));
            }
        }
        while (leftIndex < left.size()) {
            target.set(targetIndex++, left.get(leftIndex++));
        }
        while (rightIndex < right.size()) {
            target.set(targetIndex++, right.get(rightIndex++));
        }
    }

    public static <T> void insertionSort(List<T> list, Comparator<T> comparator) {
        for (int index = 1; index < list.size(); index++) {
            T current = list.get(index);
            int insertPosition = index - 1;
            while (insertPosition >= 0
                    && comparator.compare(list.get(insertPosition), current) > 0) {
                list.set(insertPosition + 1, list.get(insertPosition));
                insertPosition--;
            }
            list.set(insertPosition + 1, current);
        }
    }

    public static <T> void adaptiveSort(List<T> list, Comparator<T> comparator) {
        if (list.size() < ADAPTIVE_SORT_THRESHOLD) {
            insertionSort(list, comparator);
        } else {
            mergeSort(list, comparator);
        }
    }
}