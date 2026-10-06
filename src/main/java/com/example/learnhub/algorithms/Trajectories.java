package com.example.learnhub.algorithms;

import java.math.BigInteger;

public final class Trajectories {

    private Trajectories() {
    }

    public static BigInteger naive(int modules) {
        if (modules < 0) {
            return BigInteger.ZERO;
        }
        if (modules == 0) {
            return BigInteger.ONE;
        }
        if (modules == 1) {
            return BigInteger.ONE;
        }
        if (modules == 2) {
            return BigInteger.TWO;
        }
        return naive(modules - 1)
                .add(naive(modules - 2))
                .add(naive(modules - 3));
    }

    public static BigInteger dp(int modules) {
        if (modules < 0) {
            return BigInteger.ZERO;
        }
        if (modules == 0) {
            return BigInteger.ONE;
        }
        if (modules == 1) {
            return BigInteger.ONE;
        }
        if (modules == 2) {
            return BigInteger.TWO;
        }
        BigInteger first = BigInteger.ONE;
        BigInteger second = BigInteger.ONE;
        BigInteger third = BigInteger.TWO;
        for (int i = 3; i <= modules; i++) {
            BigInteger next = first.add(second).add(third);
            first = second;
            second = third;
            third = next;
        }
        return third;
    }
}