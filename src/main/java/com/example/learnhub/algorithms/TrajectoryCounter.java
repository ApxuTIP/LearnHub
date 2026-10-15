package com.example.learnhub.algorithms;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public final class TrajectoryCounter {
    private TrajectoryCounter() {
    }

    public static BigInteger naive(int modules) {
        if (modules < 0) {
            return BigInteger.ZERO;
        }

        if (modules == 0 || modules == 1) {
            return BigInteger.ONE;
        }
        return naive(modules - 1).add(naive(modules - 2));
    }

    public static BigInteger countWithMemoization(int modules) {
        if (modules < 0) {
            return BigInteger.ZERO;
        }
        Map<Integer, BigInteger> memoizedValues = new HashMap<>();
        memoizedValues.put(0, BigInteger.ONE);
        memoizedValues.put(1, BigInteger.ONE);
        return computeWithMemoization(modules, memoizedValues);
    }

    private static BigInteger computeWithMemoization(int modules, Map<Integer, BigInteger> memoizedValues) {
        BigInteger cachedValue = memoizedValues.get(modules);
        if (cachedValue != null) {
            return cachedValue;
        }
        BigInteger result = computeWithMemoization(modules - 1, memoizedValues)
                .add(computeWithMemoization(modules - 2, memoizedValues));
        memoizedValues.put(modules, result);
        return result;
    }
}