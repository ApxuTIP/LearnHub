package com.example.learnhub.algorithms;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public final class Trajectories {

    private Trajectories() {
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
        Map<Integer, BigInteger> memo = new HashMap<>();
        memo.put(0, BigInteger.ONE);
        memo.put(1, BigInteger.ONE);
        return computeWithMemo(modules, memo);
    }

    private static BigInteger computeWithMemo(int modules, Map<Integer, BigInteger> memo) {
        BigInteger cached = memo.get(modules);
        if (cached != null) {
            return cached;
        }
        BigInteger result = computeWithMemo(modules - 1, memo)
                .add(computeWithMemo(modules - 2, memo));
        memo.put(modules, result);
        return result;
    }
}