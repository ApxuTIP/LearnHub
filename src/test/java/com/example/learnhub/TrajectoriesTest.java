package com.example.learnhub;

import com.example.learnhub.algorithms.Trajectories;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TrajectoriesTest {

    @Test
    void baseCasesAreCorrect() {
        assertEquals(BigInteger.ONE, Trajectories.dp(0));
        assertEquals(BigInteger.ONE, Trajectories.dp(1));
        assertEquals(BigInteger.TWO, Trajectories.dp(2));
    }

    @Test
    void optimizedMatchesNaiveOnSmallInputs() {
        for (int n = 0; n <= 12; n++) {
            assertEquals(Trajectories.naive(n), Trajectories.dp(n),
                    "Расхождение на N = " + n);
        }
    }

    @Test
    void largeInputReturnsPositiveValue() {
        BigInteger value = Trajectories.dp(80);
        assertTrue(value.compareTo(BigInteger.ZERO) > 0);
    }
}