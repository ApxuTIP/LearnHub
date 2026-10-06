package com.example.learnhub.algorithms;

import java.util.ArrayList;
import java.util.List;

public final class IntervalScheduling {

    private IntervalScheduling() {
    }

    public static class Interval {
        public final int start;
        public final int end;
        public final String label;

        public Interval(int start, int end, String label) {
            this.start = start;
            this.end = end;
            this.label = label;
        }

        @Override
        public String toString() {
            return "[" + start + ".." + end + "] " + label;
        }
    }

    public static List<Interval> selectMax(List<Interval> intervals) {
        List<Interval> sorted = new ArrayList<>(intervals);
        for (int i = 1; i < sorted.size(); i++) {
            Interval current = sorted.get(i);
            int j = i - 1;
            while (j >= 0 && sorted.get(j).end > current.end) {
                sorted.set(j + 1, sorted.get(j));
                j--;
            }
            sorted.set(j + 1, current);
        }

        List<Interval> result = new ArrayList<>();
        int lastEnd = Integer.MIN_VALUE;
        for (Interval interval : sorted) {
            if (interval.start >= lastEnd) {
                result.add(interval);
                lastEnd = interval.end;
            }
        }
        return result;
    }
}