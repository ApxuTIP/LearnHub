package com.example.learnhub.utils;

public final class Timer {
    private static final long NANOSECONDS_PER_MILLISECOND = 1_000_000L;

    private Timer() {
    }

    public static long measure(String label, Runnable action) {
        long startNanoseconds = System.nanoTime();
        action.run();
        long elapsedNanoseconds = System.nanoTime() - startNanoseconds;
        long elapsedMilliseconds = elapsedNanoseconds / NANOSECONDS_PER_MILLISECOND;
        System.out.println("  [" + label + "] выполнено за " + elapsedMilliseconds + " мс (" + elapsedNanoseconds + " нс)");
        return elapsedMilliseconds;
    }

    public static long measureSilent(Runnable action) {
        long startNanoseconds = System.nanoTime();
        action.run();
        long elapsedNanoseconds = System.nanoTime() - startNanoseconds;
        return elapsedNanoseconds / NANOSECONDS_PER_MILLISECOND;
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