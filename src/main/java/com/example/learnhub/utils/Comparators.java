package com.example.learnhub.utils;

import java.util.Comparator;

public final class Comparators {
    private Comparators() {
    }

    public static class IntegerAscending implements Comparator<Integer> {

        @Override
        public int compare(Integer firstValue, Integer secondValue) {
            return Integer.compare(firstValue, secondValue);
        }
    }
}