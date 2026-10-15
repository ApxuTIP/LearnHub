package com.example.learnhub.algorithms;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public final class IntervalScheduling {
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
            return "[" + start + ".." + end + "] " + label;
        }
    }

    private static class ByEndAscendingComparator implements Comparator<Interval> {
        @Override
        public int compare(Interval firstInterval, Interval secondInterval) {
            return Integer.compare(firstInterval.getEnd(), secondInterval.getEnd());
        }
    }

    public static List<Interval> selectMax(List<Interval> intervals) {
        List<Interval> sorted = new ArrayList<>(intervals);
        Sorter.mergeSort(sorted, new ByEndAscendingComparator());
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