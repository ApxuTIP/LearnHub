package com.example.learnhub.algorithms;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TrajectoryCounterTest {

    @Test
    void naiveMatchesFormulaForSmallValues() {
        assertEquals(BigInteger.ONE, TrajectoryCounter.naive(0));
        assertEquals(BigInteger.ONE, TrajectoryCounter.naive(1));
        assertEquals(BigInteger.TWO, TrajectoryCounter.naive(2));
        assertEquals(BigInteger.valueOf(3), TrajectoryCounter.naive(3));
        assertEquals(BigInteger.valueOf(5), TrajectoryCounter.naive(4));
        assertEquals(BigInteger.valueOf(8), TrajectoryCounter.naive(5));
    }

    @Test
    void memoizedMatchesNaive() {
        for (int modules = 0; modules <= 30; modules++) {
            assertEquals(
                    TrajectoryCounter.naive(modules),
                    TrajectoryCounter.countWithMemoization(modules),
                    "Несовпадение для N = " + modules);
        }
    }

    @Test
    void largeNIsComputedInstantly() {
        BigInteger result = TrajectoryCounter.countWithMemoization(80);
        assertEquals(new BigInteger("37889062373143906"), result);
    }

    @Test
    void negativeModulesReturnsZero() {
        assertEquals(BigInteger.ZERO, TrajectoryCounter.naive(-1));
        assertEquals(BigInteger.ZERO, TrajectoryCounter.countWithMemoization(-1));
    }
}