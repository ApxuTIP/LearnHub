package com.example.learnhub.algorithms;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public final class IntervalScheduling {

    private static final Comparator<Interval> BY_END_ASC =
            (first, second) -> Integer.compare(first.getEnd(), second.getEnd());

    private IntervalScheduling() {
    }

    public static class Interval {

        private final int start;
        private final int end;
        private final String label;

        public Interval(int start, int end, String label) {
            this.start = start;
            this.end = end;
            this.label = label;
        }

        public int getStart() {
            return start;
        }

        public int getEnd() {
            return end;
        }

        public String getLabel() {
            return label;
        }

        @Override
        public String toString() {
            return String.format("[%d..%d] %s", start, end, label);
        }
    }

    public static List<Interval> selectMax(List<Interval> intervals) {
        List<Interval> sorted = new ArrayList<>(intervals);
        Sorting.mergeSort(sorted, BY_END_ASC);

        List<Interval> result = new ArrayList<>();
        int lastEnd = Integer.MIN_VALUE;
        for (Interval interval : sorted) {
            if (interval.getStart() >= lastEnd) {
                result.add(interval);
                lastEnd = interval.getEnd();
            }
        }
        return result;
    }
}