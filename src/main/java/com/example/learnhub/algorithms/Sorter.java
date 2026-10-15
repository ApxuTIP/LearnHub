package com.example.learnhub.algorithms;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public final class Sorter {
    private static final int ADAPTIVE_SORT_THRESHOLD = 32;

    private Sorter() {
    }

    public static <T> void mergeSort(List<T> list, Comparator<T> comparator) {
        if (list.size() < 2) {
            return;
        }

        int middleIndex = list.size() / 2;
        List<T> leftHalf = new ArrayList<>(list.subList(0, middleIndex));
        List<T> rightHalf = new ArrayList<>(list.subList(middleIndex, list.size()));

        mergeSort(leftHalf, comparator);
        mergeSort(rightHalf, comparator);
        merge(list, leftHalf, rightHalf, comparator);
    }

    private static <T> void merge(List<T> target, List<T> leftHalf, List<T> rightHalf, Comparator<T> comparator) {
        int leftIndex = 0;
        int rightIndex = 0;
        int targetIndex = 0;
        while (leftIndex < leftHalf.size() && rightIndex < rightHalf.size()) {
            T leftElement = leftHalf.get(leftIndex);
            T rightElement = rightHalf.get(rightIndex);
            if (comparator.compare(leftElement, rightElement) <= 0) {
                target.set(targetIndex, leftElement);
                leftIndex++;
            }

            else {
                target.set(targetIndex, rightElement);
                rightIndex++;
            }
            targetIndex++;
        }

        while (leftIndex < leftHalf.size()) {
            target.set(targetIndex, leftHalf.get(leftIndex));
            leftIndex++;
            targetIndex++;
        }

        while (rightIndex < rightHalf.size()) {
            target.set(targetIndex, rightHalf.get(rightIndex));
            rightIndex++;
            targetIndex++;
        }
    }

    public static <T> void insertionSort(List<T> list, Comparator<T> comparator) {
        for (int currentIndex = 1; currentIndex < list.size(); currentIndex++) {
            T currentElement = list.get(currentIndex);
            int insertPosition = currentIndex - 1;
            while (insertPosition >= 0 && comparator.compare(list.get(insertPosition), currentElement) > 0) {
                list.set(insertPosition + 1, list.get(insertPosition));
                insertPosition--;
            }
            list.set(insertPosition + 1, currentElement);
        }
    }

    public static <T> void adaptiveSort(List<T> list, Comparator<T> comparator) {
        if (list.size() < ADAPTIVE_SORT_THRESHOLD) {
            insertionSort(list, comparator);
        }

        else {
            mergeSort(list, comparator);
        }
    }
}