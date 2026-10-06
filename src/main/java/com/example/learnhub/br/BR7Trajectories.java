package com.example.learnhub.br;

import com.example.learnhub.algorithms.Trajectories;

import java.math.BigInteger;

public class BR7Trajectories {

    public BigInteger countNaive(int modules) {
        return Trajectories.naive(modules);
    }

    public BigInteger countOptimized(int modules) {
        return Trajectories.dp(modules);
    }
}