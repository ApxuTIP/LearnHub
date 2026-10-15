package com.example.learnhub.services;

import com.example.learnhub.algorithms.ClosestPairFinder;
import com.example.learnhub.algorithms.SlidingWindow;

import java.util.List;

public class ActivityAnalyticsService {
    public SlidingWindow.WindowResult busiestWindow(
            List<Integer> dailyActivity,
            int windowSize) {
        return SlidingWindow.maxSumWindow(dailyActivity, windowSize);
    }

    public SlidingWindow.WindowResult busiestWindowNaive(
            List<Integer> dailyActivity,
            int windowSize) {
        return SlidingWindow.maxSumWindowNaive(dailyActivity, windowSize);
    }

    public ClosestPairFinder.Pair findPairExact(
            List<Integer> scores,
            int targetSum) {
        return ClosestPairFinder.findExact(scores, targetSum);
    }

    public ClosestPairFinder.Pair findPairClosest(
            List<Integer> scores,
            int targetSum) {
        return ClosestPairFinder.findClosest(scores, targetSum);
    }

    public ClosestPairFinder.Pair findPairClosestNaive(
            List<Integer> scores,
            int targetSum) {
        return ClosestPairFinder.findClosestNaive(scores, targetSum);
    }
}