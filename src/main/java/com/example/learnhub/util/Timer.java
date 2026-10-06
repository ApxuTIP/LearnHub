package com.example.learnhub.util;

public final class Timer {

    private Timer() {
    }

    public static long measure(String label, Runnable action) {
        long start = System.nanoTime();
        action.run();
        long elapsedNanos = System.nanoTime() - start;
        long elapsedMillis = elapsedNanos / 1000000;
        System.out.println("  [" + label + "] выполнено за " + elapsedMillis
                + " мс (" + elapsedNanos + " нс)");
        return elapsedMillis;
    }

    public static void printComparison(String label, long firstMs, long secondMs) {
        System.out.println("  [" + label + "] первый вариант: " + firstMs
                + " мс, второй вариант: " + secondMs + " мс");
    }
}