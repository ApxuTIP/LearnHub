package com.example.learnhub.services;

import com.example.learnhub.algorithms.TrajectoryCounter;

import java.math.BigInteger;

public class TrajectoryService {
    public BigInteger countNaive(int modules) {
        return TrajectoryCounter.naive(modules);
    }

    public BigInteger countOptimized(int modules) {
        return TrajectoryCounter.countWithMemoization(modules);
    }
}