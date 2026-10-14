package com.example.learnhub.util;

public final class Timer {

    private static final long NANOS_PER_MILLISECOND = 1_000_000L;

    private Timer() {
    }

    public static long measure(String label, Runnable action) {
        long start = System.nanoTime();
        action.run();
        long elapsedNanos = System.nanoTime() - start;
        long elapsedMillis = elapsedNanos / NANOS_PER_MILLISECOND;
        System.out.println("  [" + label + "] выполнено за " + elapsedMillis
                + " мс (" + elapsedNanos + " нс)");
        return elapsedMillis;
    }

    public static long measureSilent(Runnable action) {
        long start = System.nanoTime();
        action.run();
        long elapsedNanos = System.nanoTime() - start;
        return elapsedNanos / NANOS_PER_MILLISECOND;
    }

    public static void printComparison(String label,
                                       String firstAlgorithm,
                                       long firstMilliseconds,
                                       String secondAlgorithm,
                                       long secondMilliseconds) {
        System.out.println("  [" + label + "] "
                + firstAlgorithm + ": " + firstMilliseconds + " мс, "
                + secondAlgorithm + ": " + secondMilliseconds + " мс");
    }
}