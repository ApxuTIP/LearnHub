package com.example.learnhub.algorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.function.ToIntFunction;

public final class Sorting {

    private Sorting() {
    }

    public static <T> void mergeSort(List<T> list, ToIntFunction<T> key) {
        if (list.size() < 2) {
            return;
        }
        int middle = list.size() / 2;
        List<T> left = new ArrayList<>(list.subList(0, middle));
        List<T> right = new ArrayList<>(list.subList(middle, list.size()));
        mergeSort(left, key);
        mergeSort(right, key);
        merge(list, left, right, key);
    }

    private static <T> void merge(List<T> target, List<T> left, List<T> right, ToIntFunction<T> key) {
        int i = 0;
        int j = 0;
        int k = 0;
        while (i < left.size() && j < right.size()) {
            if (key.applyAsInt(left.get(i)) <= key.applyAsInt(right.get(j))) {
                target.set(k++, left.get(i++));
            } else {
                target.set(k++, right.get(j++));
            }
        }
        while (i < left.size()) {
            target.set(k++, left.get(i++));
        }
        while (j < right.size()) {
            target.set(k++, right.get(j++));
        }
    }

    public static <T> void insertionSort(List<T> list, ToIntFunction<T> key) {
        for (int i = 1; i < list.size(); i++) {
            T current = list.get(i);
            int currentKey = key.applyAsInt(current);
            int j = i - 1;
            while (j >= 0 && key.applyAsInt(list.get(j)) > currentKey) {
                list.set(j + 1, list.get(j));
                j--;
            }
            list.set(j + 1, current);
        }
    }

    public static <T> void adaptiveSort(List<T> list, ToIntFunction<T> key) {
        if (list.size() < 32) {
            insertionSort(list, key);
        } else {
            mergeSort(list, key);
        }
    }
}