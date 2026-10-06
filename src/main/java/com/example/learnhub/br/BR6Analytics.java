package com.example.learnhub.br;

import com.example.learnhub.algorithms.SlidingWindow;
import com.example.learnhub.algorithms.TwoSum;

import java.util.List;

public class BR6Analytics {

    public SlidingWindow.WindowResult busiestWindow(List<Integer> dailyActivity, int k) {
        return SlidingWindow.maxSumWindow(dailyActivity, k);
    }

    public SlidingWindow.WindowResult busiestWindowNaive(List<Integer> dailyActivity, int k) {
        return SlidingWindow.maxSumWindowNaive(dailyActivity, k);
    }

    public TwoSum.Pair findPairExact(List<Integer> scores, int target) {
        return TwoSum.findExact(scores, target);
    }

    public TwoSum.Pair findPairClosest(List<Integer> scores, int target) {
        return TwoSum.findClosest(scores, target);
    }
}